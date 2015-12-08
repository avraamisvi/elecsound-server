package br.com.elecsound.project.instruments;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;

import br.com.elecsound.project.Color;
import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.InstrumentConfiguration;
import br.com.elecsound.project.instrument.form.FormFactory;

public class SineOscilator extends Instrument {

	private SineOscillator osc;
	private InstrumentConfiguration config;
	
	public SineOscilator() {
		super("OSC", "OSC");
		
		config = new InstrumentConfiguration();
		config.setForm(FormFactory.loadForm(this.getId()));
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

	@Override
	protected InstrumentConfiguration createConfiguration() {
		return config;
	}

	@Override
	protected void configure(InstrumentConfiguration config) {
		this.config = config;		
	}
}
