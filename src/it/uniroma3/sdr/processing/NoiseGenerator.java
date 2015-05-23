package it.uniroma3.sdr.processing;

import java.util.ArrayList;
import java.util.List;
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

	private Signal signal;

	/**
	 * @param signal	Segnale in ingresso sul quale generare il rumore
	 */
	public NoiseGenerator(Signal signal) {
		this.signal = signal;
	}

	
	/**
	 * @param noiseLength	Lunghezza del rumore da generare
	 * @return	Rumore generato con i parametri forniti.
	 */
	public Noise generate(int noiseLength) {
		return new Noise(noiseLength, this.signal.snr());
	}
	
	/**
	 * @param noiseLength	Lunghezza dei rumori da generare
	 * @return	Uno stream *infinito* di rumori
	 */
	public Stream<Noise> generateStream(int noiseLength) {
		return Stream.generate(() -> this.generate(noiseLength));
	}
}
