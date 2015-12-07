package br.com.elecsound.engine;

import java.util.ArrayList;
import java.util.HashMap;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;

import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.Project;

public class Player {
	
	private Synthesizer synth;
	private LineOut lineOut;
	private HashMap<String, Instrument> instruments;

	public Player() {
		
		instruments = new HashMap<>();
		
		synth = JSyn.createSynthesizer();
		synth.add( lineOut = new LineOut() );
		synth.start();
		lineOut.start();
	}
	
	public Synthesizer getSynth() {
		return synth;
	}
	
	public LineOut getLineOut() {
		return lineOut;
	}
	
	public void loadInstrument(Instrument instrument) {
		instrument.build(this);
		
		this.instruments.put(instrument.getId(), instrument);
	}
	
	public void unloadInstrument(Instrument instrument) {
		
	}	
	
	public void playinstrument(String instrument, int note) {
		this.instruments.get(instrument).noteOn(note);
	}
	
	public void stopinstrument(String instrument) {
		this.instruments.get(instrument).noteOff();
	}
	
	public void play(Project project, PlayingStatus playingStatus) {
		
	}
	
	public void stop() {
		
	}
	
	public double parseTime(double time) {
		double ret = synth.getCurrentTime() + time;
//		System.out.println("time:"+time+" ret:"+ret);
		return time;
	}	
}
