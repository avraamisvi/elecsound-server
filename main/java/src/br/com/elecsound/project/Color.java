package br.com.elecsound.project;

public class Color{
	public int r;
	public int g;
	public int b;
	
	public Color(int r, int g, int b) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public static Color GRAY = new Color(100,100,100);
}