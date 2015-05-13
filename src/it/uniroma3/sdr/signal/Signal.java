package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.math.ComplexCollection;
import it.uniroma3.sdr.math.RealGenerator;

public class Signal {

	private ComplexCollection samples;

	private double sqrt2 = Math.sqrt(2);
	
	private RealGenerator randomGenerator = () -> {
		if (Math.random() < 0.5) {
			return 1 / this.sqrt2;
		} else {
			return -1 / this.sqrt2;
		}
	};
	
	public Signal(int length) {
		this.samples = new ComplexCollection(length);
		this.samples.initialize(this.randomGenerator);
	}
}
