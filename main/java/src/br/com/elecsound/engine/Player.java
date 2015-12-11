package br.com.elecsound.engine;

import java.util.HashMap;

import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.InstrumentItem;
import br.com.elecsound.project.Project;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;

public class Player {
	
	private Synthesizer synth;
	private LineOut lineOut;
	
	private HashMap<String, InstrumentItem> instruments;

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
	
	public void loadInstrumentItem(InstrumentItem instrument) {
		instrument.connect(this);
		this.instruments.put(instrument.getId(), instrument);
	}
	
	public void unloadInstrumentItem(String id) {
		this.instruments.remove(id).disconnect();
	}	
	
	public void playInstrument(String instrument, int note) {
		this.instruments.get(instrument).getInstrument().noteOn(note);
	}
	
	public void stopInstrument(String instrument) {
		this.instruments.get(instrument).getInstrument().noteOff();
	}
	
	public void play(Project project, PlayingStatus playingStatus) {
		stop();
		
		project.generate();
		
		play(playingStatus);
	}
	
	private void play(PlayingStatus playingStatus) {
		synth.start();
		lineOut.start();
	}
	
	public void stop() {
		
		if(synth.isRunning()) {
			synth.stop();
//			lineOut.stop();
		}
	}
	
	public double parseTime(double time) {
		double ret = synth.getCurrentTime() + time;
		System.out.println("time:"+time+" ret:"+ret);
		System.out.println("synth.getCurrentTime():" + synth.getCurrentTime());
		return ret;
	}	
}
