package it.uniroma3.sdr.signal;

import java.util.Random;

import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.RealGenerator;
import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.ComplexGenerator;

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
	
	private Random randomGenerator;
	
	private double standardDeviation;
	
	private ComplexGenerator awgnGenerator = () -> {
		RealGenerator r = () -> {
			return this.randomGenerator.nextGaussian() * this.standardDeviation;
		};
		return new CartesianComplex(r.generate(), r.generate());
	};
	
	/**
	 * @param length	Lunghezza del rumore
	 * @param snr	Rapporto segnale-rumore
	 */
	public Noise(int length, double snr) {
		this.randomGenerator = new Random();
		
		double linearSnr = Math.pow(10, (snr / 10));
		this.standardDeviation = Math.sqrt((1/linearSnr) / 2);
		
		this.initialize(new ComplexStream(awgnGenerator, length));
	}
}
