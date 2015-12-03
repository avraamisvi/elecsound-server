import java.io.File;
import java.io.IOException;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

public class Testes {
	
	public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException, IOException {
	    ShortMessage myMsg = new ShortMessage();
	    int velocity, pressure, bend, reverb;
	    
	    // Play the note Middle C (60) moderately loud
	    // (velocity = 93)on channel 4 (zero-based).
	    myMsg.setMessage(ShortMessage.NOTE_ON, 0, 60, 93); 
	    Synthesizer synth = MidiSystem.getSynthesizer();
	    synth.open();
	    
	    velocity = pressure = bend = reverb = 64;
	    
	    File snd = new File("soundbank-deluxe.gm");
	    
	    Soundbank sbnk = MidiSystem.getSoundbank(snd);
	    
	    Instrument[] instr = sbnk.getInstruments();
	    final MidiChannel[] mc = synth.getChannels();
	    
	    synth.loadInstrument(instr[0]);
	    
	    mc[0].noteOn(60, 64);
	    
	    JFrame frame = new JFrame();//fazer esperar
	    frame.setVisible(true);
	    
	    /*Receiver synthRcvr = synth.getReceiver();
	    synthRcvr.send(myMsg, -1); // -1 means no time stamp*/
	    
	}
}
