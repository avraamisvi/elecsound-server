package br.com.elecsound.project;

import br.com.elecsound.engine.Player;

public class PianoRollEntry {
	
	public Instrument instrument; //Criar os subInstruments para serem usados dentro do pianoRollEntry?
	public String id;
	public int note;
	public double when;
	public double duration;
	
	public PianoRollEntry(Instrument instrument, String id, int note, double when, double duration) {
		super();
		this.id = id;
		this.note = note;
		this.when = when;
		this.duration = duration;
		this.instrument = instrument.copy();
	}

	public void play(double start, double end) {
		System.out.println("piano note: " + note + " when: " + (when) + " duration:" + duration);
		double total = when + duration;
		
		if(total > end) {
			duration = end - start;
		}
		
		instrument.playNote(note, when, duration);
	}
	
	public void stop() {
		instrument.noteOff();//TODO change this
	}	
	
	public void disconnect() {
		instrument.disconnect();
	}
	
	public void connect(Player player) {
		instrument.properties.player = player;
		instrument.init();
		
		instrument.properties.player.getSynth().add(instrument.getUnitGenerator());
		
		// Connect the oscillator to both channels of the output.
		instrument.getOutPutPort().connect( 0, instrument.properties.player.getLineOut().input, 0 );//TODO ver essa questao da conexão
		instrument.getOutPutPort().connect( 0, instrument.properties.player.getLineOut().input, 1 );
		
		instrument.noteOff();
	}	
}
