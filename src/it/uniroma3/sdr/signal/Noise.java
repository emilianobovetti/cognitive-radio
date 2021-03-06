package it.uniroma3.sdr.signal;

import java.util.Random;
import java.util.function.Supplier;

import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.complex.CartesianComplex;

/**
 * Gestisce un rumore generato in modo pseudo-casuale.
 * 
 * I dati dei campioni del rumore vengono gestiti tramite
 * uno stream, i dati non possono essere quindi elaborati
 * piu' volte, ma in compenso non vengono caricati in memoria
 * e non e' necessario inizializzare una collezione.
 * 
 * @author emiliano
 *
 */
public class Noise extends Signal {
	
	/**
	 * @param length	Lunghezza del rumore
	 * @param snr	Rapporto segnale-rumore
	 */
	public Noise(int length, double snr) {
		Random random = new Random();
		
		double standardDeviation = Math.sqrt((1 / snr) / 2);
		
		super.initialize(new ComplexStream(() -> {
			Supplier<Double> r = () -> random.nextGaussian() * standardDeviation;
			return new CartesianComplex(r.get(), r.get());
		}, length));
	}
}
