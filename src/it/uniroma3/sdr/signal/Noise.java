package it.uniroma3.sdr.signal;

import java.util.Random;

import it.uniroma3.sdr.collection.ComplexStream;
import it.uniroma3.sdr.math.CartesianComplex;
import it.uniroma3.sdr.math.ComplexGenerator;
import it.uniroma3.sdr.math.RealGenerator;

public class Noise extends Signal {
	
	private Random randomGenerator;
	
	private double standardDeviation;
	
	private ComplexGenerator awgnGenerator = () -> {
		RealGenerator r = () -> {
			return randomGenerator.nextGaussian() * this.standardDeviation;
		};
		return new CartesianComplex(r.generate(), r.generate());
	};
	
	public Noise(int length, double snr) {
		this.randomGenerator = new Random();
		
		double linearSnr = Math.pow(10, (snr / 10));
		this.standardDeviation = Math.sqrt((1/linearSnr) / 2);
		
		this.initialize(new ComplexStream(awgnGenerator, length));
	}
}
