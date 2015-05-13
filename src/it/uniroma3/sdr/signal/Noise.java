package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.math.ComplexCollection;

public class Noise {

	private ComplexCollection samples;
	
	private double variance;
	
	public Noise(int length, double snr) {
		this.samples = new ComplexCollection(length);
	}
}
