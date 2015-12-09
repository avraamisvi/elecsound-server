package br.com.elecsound;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.com.elecsound.engine.Player;
import br.com.elecsound.engine.PlayerManager;
import br.com.elecsound.engine.PlayingStatus;
import br.com.elecsound.messages.OpenProjectMessage;
import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.InstrumentItem;
import br.com.elecsound.project.Project;
import br.com.elecsound.project.ProjectFileParser;
import br.com.elecsound.project.ProjectManager;
import br.com.elecsound.project.TrackItem;
import br.com.elecsound.project.TrackLine;
import br.com.elecsound.project.instruments.SineOscilator;

public class TestPlayerConcept {
	
	public static void main(String[] args) throws Exception {
		testProject();
//		testProjectPersist();		
//		testProjectLoad();
		System.out.println("fim");
	}
	
	public static void testProjectLoad() throws FileNotFoundException {
		Manager manager = new Manager(null);//inicializando tudo
		
		OpenProjectMessage msg = new OpenProjectMessage();
		msg.setFileName("teste.elc");
		
		ProjectManager.openProject(msg);
		PlayerManager.play(ProjectManager.getProject(), 0, new PlayingStatus() {
			@Override
			public void update(int seconds) {
			}
		});
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
		
		InstrumentItem itm = new InstrumentItem("InstrumentItem1", inst, 0);
		itm.connect(player);
		
		Instrument inst2 = new SineOscilator();
		InstrumentItem itm2 = new InstrumentItem("InstrumentItem2", inst2, 0);
		itm2.connect(player);
		
		inst2.setPianoRollMode(true);
		inst2.addPianoRollEntry("1", 67, 1, 1);
		inst2.addPianoRollEntry("2", 68, 2, 1.5);
		inst2.addPianoRollEntry("3", 70, 3, 2);
		inst2.addPianoRollEntry("4", 74, 4, 1.5);
		
		project.addInstrumentItem(itm);
		project.addInstrumentItem(itm2);
		
		TrackLine line = new TrackLine("TrackLine", "asdasdsad");
		TrackItem trk = new TrackItem("TrackItem", itm2);
		trk.setStart(0);
		trk.setEnd(60);
		line.add(trk);
		
		TrackLine line2 = new TrackLine("TrackLine2", "asdasdsad");
		TrackItem trk2 = new TrackItem("TrackItem2", itm);
		trk2.setStart(0);
		trk2.setEnd(60);
		line.add(trk2);
		
		project.addTrackLine(line);
		project.addTrackLine(line2);
		
		ProjectFileParser.saveToFile(project, "teste.elc");
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
		
		InstrumentItem itm = new InstrumentItem("InstrumentItem1", inst, 0);
		itm.connect(player);
		itm.getInstrument().setInitialLoopSeqIndex(90);
		
		Instrument inst2 = new SineOscilator();
		InstrumentItem itm2 = new InstrumentItem("InstrumentItem2", inst2, 0);
		itm2.connect(player);
		
		inst2.setPianoRollMode(true);
		inst2.addPianoRollEntry("1", 67, 0, 0.6);
		inst2.addPianoRollEntry("2", 68, 0.3, 0.3);
		inst2.addPianoRollEntry("3", 70, 0.6, 0.3);
		inst2.addPianoRollEntry("4", 87, 0.9, 0.6);
		inst2.addPianoRollEntry("5", 88, 0.12, 0.3);
		inst2.addPianoRollEntry("6", 90, 0.15, 0.3);
		
		project.addInstrumentItem(itm);
		project.addInstrumentItem(itm2);
		
		TrackLine line = new TrackLine("TrackLine", "asdasdsad");
		TrackItem trk = new TrackItem("TrackItem", itm2);
		trk.setStart(0);
		trk.setEnd(60);
		line.add(trk);
		
		TrackLine line2 = new TrackLine("TrackLine2", "asdasdsad");
		TrackItem trk2 = new TrackItem("TrackItem2", itm);
		trk2.setStart(0);
		trk2.setEnd(60);
		line.add(trk2);
		
		project.addTrackLine(line);
		project.addTrackLine(line2);
		
		itm.getInstrument().noteOn(70);
		player.play(project, new PlayingStatus() {
			@Override
			public void update(int seconds) {
			}
		});
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itm.getInstrument().noteOff();
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
