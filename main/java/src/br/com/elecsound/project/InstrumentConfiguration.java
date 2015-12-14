package br.com.elecsound.project;

import java.util.HashMap;

import br.com.elecsound.project.instrument.form.Form;

/**
 * Represents the internal settings of the instrument.
 * 
 * @author abraao
 *
 */
public class InstrumentConfiguration {
	
	private Color color;
	private int initialLoopSeqIndex;
	private double speed;
	private Form form;
	private HashMap<String, String> fields;
	
	public InstrumentConfiguration() {
		fields = new HashMap<String, String>();
		this.setColor(Color.GRAY);
	}

	public int getInitialLoopSeqIndex() {
		return initialLoopSeqIndex;
	}

	public void setInitialLoopSeqIndex(int initialLoopSeqIndex) {
		this.initialLoopSeqIndex = initialLoopSeqIndex;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public HashMap<String, String> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, String> fields) {
		this.fields = fields;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}
