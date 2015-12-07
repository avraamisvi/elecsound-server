package br.com.elecsound.project.instruments;

import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;

import br.com.elecsound.project.Instrument;

public class SineOscilator extends Instrument {

	private SineOscillator osc;

	public SineOscilator() {
		super("OSC");
	}
	
	@Override
	public void init() {
		this.player.getSynth().add(osc = new SineOscillator() );
		
		// Connect the oscillator to both channels of the output.
		osc.output.connect( 0, this.player.getLineOut().input, 0 );
		osc.output.connect( 0, this.player.getLineOut().input, 1 );		
	}

	@Override
	public UnitVoice getUnit() {
		return osc;
	}

}
