package br.com.elecsound.project.instruments;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;

import br.com.elecsound.project.Instrument;

public class SineOscilator extends Instrument {

	private SineOscillator osc;

	public SineOscilator() {
		super("OSC", "OSC");
	}
	
	@Override
	public void init() {
		osc = new SineOscillator();
	}
	
	@Override
	public UnitOutputPort getOutPutPort() {
		return osc.output;
	}

	@Override
	public UnitVoice getUnitVoice() {
		return osc;
	}

	@Override
	public UnitGenerator getUnitGenerator() {
		return osc;
	}
	
	@Override
	protected Instrument newInstance() {
		return new SineOscilator();
	}

}
