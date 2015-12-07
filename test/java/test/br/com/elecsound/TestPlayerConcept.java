package br.com.elecsound;

import br.com.elecsound.engine.Player;
import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.TrackItem;
import br.com.elecsound.project.instruments.SineOscilator;

public class TestPlayerConcept {
	
	public static void main(String[] args) {
		Player player = new Player();
		
		Instrument inst = new SineOscilator();
		Instrument inst2 = new SineOscilator();
		
		inst.setLoop(0, Instrument.LOOP_PLAY);
		inst.setLoop(1, Instrument.LOOP_PLAY);
		inst.setLoop(2, Instrument.LOOP_PLAY);
		inst.setLoop(3, Instrument.LOOP_PLAY);
		inst.setLoop(6, Instrument.LOOP_PLAY);
		inst.setLoop(8, Instrument.LOOP_PLAY);
		inst.setLoop(10, Instrument.LOOP_PLAY);
		inst.setLoop(11, Instrument.LOOP_PLAY);
		
		inst2.setLoop(0, Instrument.LOOP_PLAY);
		inst2.setLoop(1, Instrument.LOOP_PLAY);
		inst2.setLoop(2, Instrument.LOOP_PLAY);
		inst2.setLoop(3, Instrument.LOOP_PLAY);
		inst2.setLoop(4, Instrument.LOOP_PLAY);
		inst2.setLoop(5, Instrument.LOOP_PLAY);
		inst2.setLoop(7, Instrument.LOOP_PLAY);
		inst2.setLoop(9, Instrument.LOOP_PLAY);
		
		TrackItem trk = new TrackItem("",inst);
		trk.setStart(0);
		trk.setEnd(60);
		
		TrackItem trk2 = new TrackItem("",inst2);
		trk2.setStart(2);
		trk2.setEnd(60);
		
		player.loadInstrument(inst);
		player.loadInstrument(inst2);
		
		//trk.play();
		//trk2.play();
		
		inst.play(440, 0, 8);
		inst.play(220, 0, 6);
		
//		player.playinstrument("OSC", 69);
//		player.playinstrument("OSC", 76);
		
	}
}
