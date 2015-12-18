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
	
	private HashMap<String, Instrument> instrumentsInSynth;
	private boolean running = false;
	
	public Player() {
		
		instruments = new HashMap<>();
		
		synth = JSyn.createSynthesizer();
		synth.add( lineOut = new LineOut() );
		synth.start();
		lineOut.start();
	}
//	
//	public void addInstrument(Instrument instrument) {
//		instrumentsInSynth.put(instrument.getId(), instrument);
//		
//		criar um metodo para adicionar instrumentos e encapsular o synth completmante?
//	}
//	
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
		this.instruments.get(instrument).getInstrument().stopInstrument();
	}
	
	public void play(Project project, PlayingStatus playingStatus) {
		stop();
		
		project.generate();
		
		play(playingStatus);
	}
	
	private void play(final PlayingStatus playingStatus) {
		
		running = true;
		final double statTime = this.synth.getCurrentTime();
		
		synth.start();
		lineOut.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {				
				while(Player.this.isRunning()) {
					playingStatus.update(Player.this.getSynth().getCurrentTime() - statTime);
					try {
						Thread.sleep(50);//sem isso ele nao envia pro cliente, TODO rever
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("finished thread playingStatus");
			}
		}).start();
	}
	
	public void start() {
		synth.start();
		lineOut.start();		
	}
	
	public void stop() {
		running = false;
//		if(synth.isRunning()) {
			synth.stop();
			synth.clear();
//		}
	}
	
	public boolean isRunning() {
		return running ;
	}
	
	public double parseTime(double time) {
		double ret = synth.getCurrentTime() + time;
//		System.out.println("time:"+time+" ret:"+ret);
//		System.out.println("synth.getCurrentTime():" + synth.getCurrentTime());
		return ret;
	}	
}
