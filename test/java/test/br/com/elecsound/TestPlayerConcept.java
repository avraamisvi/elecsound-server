package br.com.elecsound;

import java.io.IOException;

import br.com.elecsound.engine.Player;
import br.com.elecsound.engine.PlayingStatus;
import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.InstrumentItem;
import br.com.elecsound.project.Project;
import br.com.elecsound.project.TrackItem;
import br.com.elecsound.project.TrackLine;
import br.com.elecsound.project.instruments.SineOscilator;

public class TestPlayerConcept {
	
	public static void main(String[] args) throws Exception {
		//testProject();
		testProjectPersist();
	}
	
	public static void testProjectPersist() throws IOException {
		final Player player = new Player();
		Project project = new Project("abacate");
		
		Instrument inst = new SineOscilator();
		inst.setLoop(0, Instrument.LOOP_PLAY);
		inst.setLoop(1, Instrument.LOOP_PLAY);
		inst.setLoop(2, Instrument.LOOP_PLAY);
		inst.setLoop(3, Instrument.LOOP_PLAY);
		inst.setLoop(6, Instrument.LOOP_PLAY);
		inst.setLoop(8, Instrument.LOOP_PLAY);
		inst.setLoop(10, Instrument.LOOP_PLAY);
		inst.setLoop(11, Instrument.LOOP_PLAY);		
		
		InstrumentItem itm = new InstrumentItem("sdad", inst, 0);
		itm.connect(player);
		
		project.addInstrumentItem(itm);
		
		TrackLine line = new TrackLine("asdsa", "asdasdsad");
		TrackItem trk = new TrackItem("123", itm);
		trk.setStart(0);
		trk.setEnd(60);
		line.add(trk);
		
		project.addTrackLine(line);
		
		project.saveToFile("teste.elc");
	}
	
	
	public static void testProject() {
		final Player player = new Player();
		Project project = new Project("abacate");
		
		Instrument inst = new SineOscilator();
		inst.setLoop(0, Instrument.LOOP_PLAY);
		inst.setLoop(1, Instrument.LOOP_PLAY);
		inst.setLoop(2, Instrument.LOOP_PLAY);
		inst.setLoop(3, Instrument.LOOP_PLAY);
		inst.setLoop(6, Instrument.LOOP_PLAY);
		inst.setLoop(8, Instrument.LOOP_PLAY);
		inst.setLoop(10, Instrument.LOOP_PLAY);
		inst.setLoop(11, Instrument.LOOP_PLAY);		
		
		InstrumentItem itm = new InstrumentItem("sdad", inst, 0);
		itm.connect(player);
		
		project.addInstrumentItem(itm);
		
		TrackLine line = new TrackLine("asdsa", "asdasdsad");
		TrackItem trk = new TrackItem("123", itm);
		trk.setStart(0);
		trk.setEnd(60);
		line.add(trk);
		
		project.addTrackLine(line);
		
		player.play(project, new PlayingStatus() {
			@Override
			public void update(int seconds) {
			}
		});
	}
	
	public static void teste1() {
//		final Player player = new Player();
//		
//		Instrument inst = new SineOscilator();
//		Instrument inst2 = new SineOscilator();
//		
//		inst.setLoop(0, Instrument.LOOP_PLAY);
//		inst.setLoop(1, Instrument.LOOP_PLAY);
//		inst.setLoop(2, Instrument.LOOP_PLAY);
//		inst.setLoop(3, Instrument.LOOP_PLAY);
//		inst.setLoop(6, Instrument.LOOP_PLAY);
//		inst.setLoop(8, Instrument.LOOP_PLAY);
//		inst.setLoop(10, Instrument.LOOP_PLAY);
//		inst.setLoop(11, Instrument.LOOP_PLAY);
//		
//		inst2.setLoop(0, Instrument.LOOP_PLAY);
//		inst2.setLoop(1, Instrument.LOOP_PLAY);
//		inst2.setLoop(2, Instrument.LOOP_PLAY);
//		inst2.setLoop(3, Instrument.LOOP_PLAY);
//		inst2.setLoop(4, Instrument.LOOP_PLAY);
//		inst2.setLoop(5, Instrument.LOOP_PLAY);
//		inst2.setLoop(7, Instrument.LOOP_PLAY);
//		inst2.setLoop(9, Instrument.LOOP_PLAY);
//		
//		TrackItem trk = new TrackItem("123",inst);
//		trk.setStart(0);
//		trk.setEnd(60);
//		
//		TrackItem trk2 = new TrackItem("123",inst2);
//		trk2.setStart(2);
//		trk2.setEnd(60);
//		
//		inst.connect(player);
//		inst2.connect(player);
//		player.loadInstrument(inst);
//		player.loadInstrument(inst2);
		
//		trk.play();
//		trk2.play();
		
//		inst.play(220, 0, 6);
		
//		new Thread(){
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(player.getSynth().getCurrentTime());
//				}
//			}
//		}.start();
//		
//		inst.play(440, 0, 8);
//		inst2.play(220, 2, 8);
//		
//		player.getSynth().sleepUntil(5);
//		player.stop();
////		
//		player.getSynth().sleepUntil(1);
//		player.getSynth().start();
//		inst.play(440, 0, 8);
//		inst2.play(220, 9, 8);
//		
//		player.getLineOut().start();//precisa reiniciar o lineout ele reinicia o syth curr time
		
//		player.playinstrument("OSC", 69);
//		player.playinstrument("OSC", 76);		
	}
}
