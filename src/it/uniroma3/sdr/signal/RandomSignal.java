package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.RealGenerator;
import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.ComplexGenerator;

/**
 * Gestisce un segnale utile generato in modo pseudo-casuale
 * 
 * I dati vengono gestiti da uno stream.
 * 
 * @author emiliano
 *
 */
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
	
	/**
	 * @param length	Lunghezza del segnale da generare
	 */
	public RandomSignal(int length) {
		this.initialize(new ComplexStream(randomGenerator, length));
	}
}
