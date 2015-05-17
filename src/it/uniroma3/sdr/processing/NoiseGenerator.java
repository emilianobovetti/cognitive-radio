package it.uniroma3.sdr.processing;

import java.util.stream.Stream;

import it.uniroma3.sdr.signal.Noise;
import it.uniroma3.sdr.signal.Signal;

public class NoiseGenerator {

	private double signalEnergy;
	
	public NoiseGenerator(double signalEnergy) {
		this.signalEnergy = signalEnergy;
	}
	
	public NoiseGenerator(Signal signal) {
		this(signal.energy());
	}
	
	public double snr() {
		return 1 / (1 - this.signalEnergy);
	}
	
	public Noise generate(int noiseLength) {
		return new Noise(noiseLength, this.snr());
	}
	
	public Stream<Noise> generateStream(int noiseLength) {
		return Stream.iterate(this.generate(noiseLength), (x) -> this.generate(noiseLength));
	}
}
