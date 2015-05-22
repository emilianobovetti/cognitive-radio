package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.Complex;

import java.util.function.Supplier;

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
	
	private Supplier<Complex> randomGenerator = () -> {
		Supplier<Double> r = () -> {
			if (Math.random() < 0.5) {
				return 1 / this.sqrt2;
			} else {
				return -1 / this.sqrt2;
			}
		};
		return new CartesianComplex(r.get(), r.get());
	};
	
	/**
	 * @param length	Lunghezza del segnale da generare
	 */
	public RandomSignal(int length) {
		super.initialize(new ComplexStream(randomGenerator, length));
	}
}
