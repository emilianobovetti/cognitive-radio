package it.uniroma3.sdr.signal;

import java.util.Random;

import it.uniroma3.sdr.math.ComplexCollection;
import it.uniroma3.sdr.math.RealGenerator;

public class Noise {

	private ComplexCollection samples;
	
	private Random randomGenerator;
	
	private double standardDeviation;
	
	private RealGenerator awgnGenerator = () -> {
		return randomGenerator.nextGaussian() * this.standardDeviation;
	};
	
	public Noise(int length, double snr) {
		this.samples = new ComplexCollection(length);
		this.randomGenerator = new Random();
		
		double linearSnr = Math.pow(10, (snr / 10));
		this.standardDeviation = Math.sqrt((1/linearSnr) / 2);
		
		this.samples.initialize(awgnGenerator);
	}
}
