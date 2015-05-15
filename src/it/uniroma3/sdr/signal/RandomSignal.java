package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.ComplexStream;
import it.uniroma3.sdr.math.CartesianComplex;
import it.uniroma3.sdr.math.ComplexGenerator;
import it.uniroma3.sdr.math.RealGenerator;

public class RandomSignal extends Signal {
	
	private double sqrt2 = Math.sqrt(2);
	
	private ComplexGenerator randomGenerator = () -> {
		RealGenerator r = () -> {
			if (Math.random() < 0.5) {
				return 1 / this.sqrt2;
			} else {
				return -1 / this.sqrt2;
			}
		};
		return new CartesianComplex(r.generate(), r.generate());
	};
	
	public RandomSignal(int length) {
		this.initialize(new ComplexStream(randomGenerator, length));
	}
}
