package br.com.elecsound.project;

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
	
	public Instrument(String id, String name) {
		this.id = id;
		this.name = name;
		
		this.loopSequence = new int[16];
		
		for (int i = 0; i < 16; i++) {
			loopSequence[i] = LOOP_NOT_PLAY;
		}
	}

	public final void build(Player player) {
		this.player = player;
		this.init();
		this.noteOff();
	}
	
	public abstract void init();
	public abstract UnitVoice getUnit();
	protected abstract Instrument makeCopy();
	
	/**
	 * it makes an object copy.
	 * @return
	 */
	public Instrument copy() {
		Instrument inst = makeCopy();
		
		inst.name = this.name;
		inst.name = this.player;
		inst.name = this.id;
		inst.name = this.pianoRollMode = false;
		inst.name = this.loopSequence;			
		
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
	
	public void noteOn(int note) {
		double freq = Instrument.notes[note];
		getUnit().noteOn(freq, getAmplitude(), new TimeStamp(0));
	}
	
	public void noteOff() {
		getUnit().noteOff(new TimeStamp(0));
	}
	
	public void play(double frequency, double start, double duration) {
		System.out.println("start2 :" + start);
		getUnit().noteOn(frequency, getAmplitude(), new TimeStamp(player.parseTime(start)));
		getUnit().noteOff(new TimeStamp(player.parseTime(start + duration)));
	}	

	private double getAmplitude() {
		return 0.6;
	}

	public void playLoopIndex(int index, double start) {
		System.out.println("start :" + start);
		play(getFrequencyForLoopIndex(index), start, LOOP_PLAY_TIMESTAMP);
	}		
	
	protected double getFrequencyForLoopIndex(int index) {
		return notes[index+69];
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
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
}
