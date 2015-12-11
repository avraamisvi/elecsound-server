package br.com.elecsound.project;

import java.util.HashMap;

import br.com.elecsound.engine.Player;

import com.jsyn.Synthesizer;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

public abstract class Instrument {
	
	public static int LOOP_PLAY = 1;
	public static int LOOP_NOT_PLAY = 0;
	public static double LOOP_PLAY_TIMESTAMP = 0.3;
	
	private static double[] notes;
	
	static {
		generateNotes();
	}
	
	protected SharedProperties properties;
	
	public Instrument(String id, String name) {
		
		this.properties = new SharedProperties();
		
		this.properties.id = id;
		this.properties.name = name;
		
		this.properties.loopSequence = new int[16];
		
		for (int i = 0; i < 16; i++) {
			this.properties.loopSequence[i] = LOOP_NOT_PLAY;
		}
		
		this.properties.pianoRoll = new HashMap<>();
	}

	public void setInitialLoopSeqIndex(int initialLoopSeqIndex) {
		this.properties.initialLoopSeqIndex = initialLoopSeqIndex;
	}
	
	public int getInitialLoopSeqIndex() {
		return this.properties.initialLoopSeqIndex;
	}
	
	public final void connect(Player player) {
		this.properties.player = player;
		this.init();
		
		Synthesizer synth = this.properties.player.getSynth();
		
		synth.add(getUnitGenerator());
		
		// Connect the oscillator to both channels of the output.
		getOutPutPort().connect( 0, this.properties.player.getLineOut().input, 0 );//TODO ver essa questao da conexão
		getOutPutPort().connect( 0, this.properties.player.getLineOut().input, 1 );
		
		for (PianoRollEntry entry : this.properties.pianoRoll.values()) {
			entry.connect(player);
		}
		
		this.noteOff();
	}
	
	/**
	 * Remove from player
	 */
	public void disconnect() {//TODO ver essa questao da conexão
		if(this.properties.player != null) {
			getOutPutPort().disconnect(0, this.properties.player.getLineOut().input, 0 );
			getOutPutPort().disconnect(0, this.properties.player.getLineOut().input, 1 );
			this.properties.player.getSynth().remove(getUnitGenerator());
			
			for (PianoRollEntry entry : this.properties.pianoRoll.values()) {
				entry.disconnect();
			}		
			
			this.properties = null;
		}
	}
	
	/**
	 * Initializes the instrument.
	 */
	public abstract void init();
	
	public abstract UnitVoice getUnitVoice();
	public abstract UnitGenerator getUnitGenerator();
	public abstract UnitOutputPort getOutPutPort() ;
	
	/**
	 * Should make an instance of the current object, to allow copying it.
	 * 
	 * @return
	 */
	protected abstract Instrument newInstance();
	
	/**
	 * it makes an object copy.
	 * @return
	 */
	public Instrument copy() {
		
		Instrument inst = newInstance();//get implemented instance
		
		inst.properties = this.properties;//os instrumentos compartilham suas propriedades
		
		if(this.properties.player != null)
			inst.connect(this.properties.player);
		
		return inst;
	}
	
	public void setLoop(int index, int state) {
		this.properties.loopSequence[index] = state;
	}	
	
	public void setPianoRollMode(boolean pianoRollMode) {
		this.properties.pianoRollMode = pianoRollMode;
	}
	
	public boolean isPianoRollMode() {
		return this.properties.pianoRollMode;
	}
	
	/**
	 * play a MIDI note.
	 * 
	 * @param note
	 */
	public void noteOn(int note) {
		double freq = Instrument.notes[note];
		getUnitVoice().noteOn(freq, getAmplitude(), new TimeStamp(0));
	}
	
	public void noteOff() {
		getUnitVoice().noteOff(new TimeStamp(this.properties.player.parseTime(0)));
	}
	
	public void play(double start, double end) {
		
		if(this.properties.pianoRollMode) {
			double time = 0;
			for (PianoRollEntry entry : this.properties.pianoRoll.values()) {
				entry.play(start);
				time += entry.duration;
				
				if(time > end)
					break;
			}			
		} else {
			double time = 0;
			
			while(time < end) {//executa um loop ate que o tempo acabe
				for (int i = 0; i < this.getLoopSequence().length; i++) {
					
					if(time < end) {
						int state = this.getLoopSequence()[i];
						if(state == Instrument.LOOP_PLAY) {							
							this.playLoopIndex(i, start + time);//TODO remover o noteoff quando a proxima nota nao for muda
//							getUnitVoice().noteOn(getFrequencyForLoopIndex(i), getAmplitude(), new TimeStamp(this.properties.player.parseTime(start)));
						} 
//						else {
//							getUnitVoice().noteOff(new TimeStamp(this.properties.player.parseTime(start + time)));
//						}
					} else {
						break;
					}
					
					time = time + Instrument.LOOP_PLAY_TIMESTAMP;
				}
			}
		}
	}	
	
	public void play(double frequency, double start, double duration) {
		
		getUnitVoice().noteOn(frequency, getAmplitude(), new TimeStamp(this.properties.player.parseTime(start)));
		getUnitVoice().noteOff(new TimeStamp(this.properties.player.parseTime(start + duration)));
	}	

	public void playNote(int note, double start, double duration) {
		
		getUnitVoice().noteOn(Instrument.notes[note], getAmplitude(), new TimeStamp(this.properties.player.parseTime(start)));
		getUnitVoice().noteOff(new TimeStamp(this.properties.player.parseTime(start + duration)));
	}	
	
	private double getAmplitude() {
		return 0.6;
	}

	public void playLoopIndex(int index, double start) {
		System.out.println("start :" + start + " note:" + (getFrequencyForLoopIndex(index)));
		play(getFrequencyForLoopIndex(index), start, LOOP_PLAY_TIMESTAMP);
	}		
	
	protected double getFrequencyForLoopIndex(int index) {
		return notes[index+this.properties.initialLoopSeqIndex];
	}
	
	public String getId() {
		return this.properties.id;
	}
	
	public String getName() {
		return this.properties.name;
	}
	
	public int[] getLoopSequence() {
		return this.properties.loopSequence;
	}
	
	protected abstract InstrumentConfiguration createConfiguration();
	protected abstract void configure(InstrumentConfiguration config);
	
	public void setConfiguration(InstrumentConfiguration config) {
		this.setInitialLoopSeqIndex(config.getInitialLoopSeqIndex());
		configure(config);
	}
	
	public InstrumentConfiguration getConfiguration() {
		InstrumentConfiguration config = createConfiguration();
		config.setInitialLoopSeqIndex(getInitialLoopSeqIndex());
		return config;
	}
	
	double convertPitchToFrequency( double pitch )
	{
		final double concertA = 440.0;
		return concertA * Math.pow( 2.0, ((pitch - 69) * (1.0 / 12.0)) );
	}
	
	static double freq(double n) {
		double  a = 1.0594630943592953;
		double  f0=8.1757989156;
		double  result = f0 * Math.pow(a, n);
		
//		System.out.println(n + ":" + result);
		return result;
	}

	static void generateNotes() {
		notes = new double[12*12];
		for(int j = 0; j < (12*12); j++) {
			notes[j]=freq(j);
		}		
	}

	 //TODO estudar como otimizar a criação de entradas do pianoRoll para usar um buffer de voices do instrumento
	public void addPianoRollEntry(String id, int note, double time, double duration) {
		this.properties.pianoRoll.put(id, new PianoRollEntry(this, id, note, time, duration));
	}
	
	public void removePianoRollEntry(String id) {
		this.properties.pianoRoll.remove(id).disconnect();
	}	
	
	public HashMap<String, PianoRollEntry> getPianoRoll() {
		return this.properties.pianoRoll;
	}
	
	public void setLoopSequence(int[] loopSequence) {
		this.properties.loopSequence = loopSequence;
	}
	
	public void setPianoRoll(HashMap<String, PianoRollEntry> pianoRoll) {
		this.properties.pianoRoll = pianoRoll;
	}
	
	class SharedProperties {
		public String id;
		public String name;
		public Player player;
		public int[] loopSequence;
		public HashMap<String, PianoRollEntry> pianoRoll;
		public boolean pianoRollMode = false;
		public int initialLoopSeqIndex = 69;		
	}
}
