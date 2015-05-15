package it.uniroma3.sdr.processing;

import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import it.uniroma3.sdr.signal.Noise;
import it.uniroma3.sdr.signal.Signal;

public class ThresholdEnergy {

	private int testsNumber;
	
	private int noiseLength;
	
	private double probabilityFalseAlarm;
	
	public ThresholdEnergy(int testsNumber, int noiseLength, double probabilityFalseAlarm) {
		this.testsNumber = testsNumber;
		this.noiseLength = noiseLength;
		this.probabilityFalseAlarm = probabilityFalseAlarm;
	}
	
	public double estimate(Signal signal) {
		Mean mean = new Mean();
		Variance variance = new Variance();
		
		double snr = 1 / (1 - signal.energy());
		double[] noiseEnergies = new double[this.testsNumber];
		for (int i = 0; i < this.testsNumber; i++) {
			Noise noise = new Noise(this.noiseLength, snr);
			noiseEnergies[i] = noise.energy();
		}
		
//		Stream<Double> noiseEnergies = Stream.iterate(new Noise(this.noiseLength, snr), (x) -> new Noise(this.noiseLength, snr))
//				.limit(this.testsNumber).map((x) -> x.energy());
//		return Mean.evaluate(noiseEnergies, this.testsNumber) +
//				Math.sqrt(2.0 * Variance.evaluate(noiseEnergies, this.testsNumber)) *
//					ErfInv.evaluate(1.0 - 2.0 * this.probabilityFalseAlarm);
		
		return mean.evaluate(noiseEnergies, 0, noiseEnergies.length) +
				Math.sqrt(2.0 * variance.evaluate(noiseEnergies)) *
						Erf.erfInv(1.0 - 2.0 * this.probabilityFalseAlarm);
	}
}
