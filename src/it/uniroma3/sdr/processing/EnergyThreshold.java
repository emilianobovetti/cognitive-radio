package it.uniroma3.sdr.processing;

import it.uniroma3.sdr.math.ErfInv;
import it.uniroma3.sdr.math.Mean;
import it.uniroma3.sdr.math.Variance;
import it.uniroma3.sdr.signal.Signal;

public class EnergyThreshold {

	private int testsNumber;
	
	private int noiseLength;
	
	private double probabilityFalseAlarm;
	
	public EnergyThreshold(int testsNumber, int noiseLength, double probabilityFalseAlarm) {
		this.testsNumber = testsNumber;
		this.noiseLength = noiseLength;
		this.probabilityFalseAlarm = probabilityFalseAlarm;
	}
	
	public double evaluate(Signal signal) {
		NoiseGenerator generator = new NoiseGenerator(signal);
		
		Double[] noisesEnergy = generator.generateStream(this.noiseLength)
				.limit(this.testsNumber).map(x -> x.energy()).toArray(Double[]::new);
		
		double mean = Mean.evaluate(noisesEnergy);
		return mean + Math.sqrt(2.0 * Variance.evaluate(noisesEnergy, mean)) *
					ErfInv.evaluate(1.0 - 2.0 * this.probabilityFalseAlarm);
	}
}
