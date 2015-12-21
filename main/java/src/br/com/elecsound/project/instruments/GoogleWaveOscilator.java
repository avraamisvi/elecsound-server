package br.com.elecsound.project.instruments;

import br.com.elecsound.project.Instrument;
import br.com.elecsound.project.InstrumentConfiguration;
import br.com.elecsound.project.instrument.form.FormFactory;

import com.jsyn.examples.GoogleWaveOscillator;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitVoice;

public class GoogleWaveOscilator extends Instrument {

	private GoogleWaveOscillator osc;//GoogleWaveOscillator
	private InstrumentConfiguration config;
	
	public GoogleWaveOscilator() {
		super("GWOSC", "GOWSC");
		
		config = new InstrumentConfiguration();
		config.setForm(FormFactory.loadForm(this.getId()));
	}
	
	@Override
	public void init() {
		osc = new GoogleWaveOscillator();
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
		return new GoogleWaveOscilator();
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
