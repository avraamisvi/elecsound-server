package br.com.elecsound.project;

import java.util.ArrayList;
import java.util.HashMap;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;
import com.softsynth.shared.time.TimeStamp;

import br.com.elecsound.engine.Player;

public abstract class Instrument {
	
	public static int LOOP_PLAY = 1;
	public static int LOOP_NOT_PLAY = 0;
	public static double LOOP_PLAY_TIMESTAMP = 0.3;
	
	private static double[] notes;
	
	static {
		generate();
	}
	
	private String name;
	protected Player player;
	private String id;
	private boolean pianoRollMode = false;
	private int[] loopSequence;
	
	private HashMap<String, Integer> pianoRoll;
	
	private int initialLoopSeqIndex = 69;
	
	public Instrument(String id, String name) {
		this.id = id;
		this.name = name;
		
		this.loopSequence = new int[16];
		
		for (int i = 0; i < 16; i++) {
			loopSequence[i] = LOOP_NOT_PLAY;
		}
		
		pianoRoll = new HashMap<>();
	}

	public void setInitialLoopSeqIndex(int initialLoopSeqIndex) {
		this.initialLoopSeqIndex = initialLoopSeqIndex;
	}
	
	public int getInitialLoopSeqIndex() {
		return initialLoopSeqIndex;
	}
	
	public final void connect(Player player) {
		this.player = player;
		this.init();
		
		this.player.getSynth().add(getUnitGenerator());
		
		// Connect the oscillator to both channels of the output.
		getOutPutPort().connect( 0, this.player.getLineOut().input, 0 );//TODO ver essa questao da conexão
		getOutPutPort().connect( 0, this.player.getLineOut().input, 1 );
		
		this.noteOff();
	}
	
	/**
	 * Remove from player
	 */
	public void disconnect() {//TODO ver essa questao da conexão
		getOutPutPort().disconnect(0, this.player.getLineOut().input, 0 );
		getOutPutPort().disconnect(0, this.player.getLineOut().input, 1 );
		this.player.getSynth().remove(getUnitGenerator());
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
		
		inst.name = this.name;
		inst.id = this.id;
		inst.pianoRollMode = this.pianoRollMode;
		
		inst.loopSequence = this.loopSequence;//they need to share the same loopsequence
		inst.pianoRoll = this.pianoRoll;//they need to share the same pianoRoll
		
		inst.connect(this.player);
		
		return inst;
	}
	
	public void setLoop(int index, int state) {
		loopSequence[index] = state;
	}	
	
	public void setPianoRollMode(boolean pianoRollMode) {
		this.pianoRollMode = pianoRollMode;
	}
	
	public boolean isPianoRollMode() {
		return pianoRollMode;
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
		getUnitVoice().noteOff(new TimeStamp(0));
	}
	
	public void play(double frequency, double start, double duration) {
		System.out.println("start2 :" + start);
		getUnitVoice().noteOn(frequency, getAmplitude(), new TimeStamp(player.parseTime(start)));
		getUnitVoice().noteOff(new TimeStamp(player.parseTime(start + duration)));
	}	

	private double getAmplitude() {
		return 0.6;
	}

	public void playLoopIndex(int index, double start) {
		System.out.println("start :" + start);
		play(getFrequencyForLoopIndex(index), start, LOOP_PLAY_TIMESTAMP);
	}		
	
	protected double getFrequencyForLoopIndex(int index) {
		return notes[index+this.initialLoopSeqIndex];
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getLoopSequence() {
		return loopSequence;
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

	static void generate() {
		notes = new double[12*12];
		for(int j = 0; j < (12*12); j++) {
			notes[j]=freq(j);
		}		
	}

	public void addPianoRollEntry(String id, int note) {
		pianoRoll.put(id, note);
	}
	
	public void removePianoRollEntry(String id) {
		pianoRoll.remove(id);
	}	
}
