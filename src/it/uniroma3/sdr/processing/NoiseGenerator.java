package it.uniroma3.sdr.processing;

import java.util.stream.Stream;

import it.uniroma3.sdr.signal.Noise;
import it.uniroma3.sdr.signal.Signal;

/**
 * Generatori di rumori pseudo casuali
 * 
 * @author emiliano
 *
 */
public class NoiseGenerator {

	private double signalEnergy;
	
	/**
	 * @param signalEnergy	Energia del segnale immerso nel rumore
	 */
	public NoiseGenerator(double signalEnergy) {
		this.signalEnergy = signalEnergy;
	}
	
	/**
	 * @param signal	Segnale sul quale generare il rumore
	 */
	public NoiseGenerator(Signal signal) {
		this(signal.energy());
	}
	
	/**
	 * @return	Stima del rapporto segnale-rumore
	 * 	L'energia del segnale utile viene considerata unitaria
	 */
	public double snr() {
		return 1 / (this.signalEnergy - 1);
	}
	
	/**
	 * @return Stima del rapporto segnale-rumore in decibel
	 */
	public double snrDb() {
		return 10 * Math.log10(this.snr());
	}
	
	/**
	 * @param noiseLength	Lunghezza del rumore da generare
	 * @return	Rumore generato con i parametri forniti.
	 */
	public Noise generate(int noiseLength) {
		return new Noise(noiseLength, this.snr());
	}
	
	/**
	 * @param noiseLength	Lunghezza dei rumori da generare
	 * @return	Uno stream *infinito* di rumori
	 */
	public Stream<Noise> generateStream(int noiseLength) {
		// TODO parallel stream ?
		return Stream.iterate(this.generate(noiseLength), (x) -> this.generate(noiseLength)).parallel();
	}
}
