package it.uniroma3.sdr.processing;

import java.util.stream.Stream;

import it.uniroma3.sdr.math.ErfInv;
import it.uniroma3.sdr.math.Mean;
import it.uniroma3.sdr.math.Variance;
import it.uniroma3.sdr.signal.Noise;
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
	
	public double getSnr(Signal signal) {
		return 1 / (1 - signal.energy());
	}
	
	public Stream<Noise> noisesStream(Signal signal) {
		double snr = this.getSnr(signal);
		return Stream.iterate(new Noise(this.noiseLength, snr), (x) -> new Noise(this.noiseLength, snr));
	}
	
	public double evaluate(Signal signal) {
		Double[] noiseEnergies = this.noisesStream(signal)
				.limit(this.testsNumber).map(x -> x.energy()).toArray(Double[]::new);
		
		double mean = Mean.evaluate(noiseEnergies);
		return mean + Math.sqrt(2.0 * Variance.evaluate(noiseEnergies, mean)) *
					ErfInv.evaluate(1.0 - 2.0 * this.probabilityFalseAlarm);
	}
}
