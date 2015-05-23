package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.*;
import java.util.stream.Stream;

/**
 * Fornisce strumenti utili per l'elaborazione di stream di complessi
 * e di segnali.
 * 
 * @author emiliano
 *
 */
public class SignalUtil {

	private Stream<Complex> stream;

	private ArrayDeque<Complex> buffer;

	/**
	 * @param stream	Stream di complessi in ingresso
	 */
	public SignalUtil(Stream<Complex> stream) {
		this.stream = stream;
	}
	
	/**
	 * Costruisce una lista di segnali di lunghezza determinata.
	 * 
	 * @param length	Lunghezza del segnale
	 * @return	Lista di segnali
	 */
	public Collection<Signal> split(int length) {
		Collection<Signal> signals = new ArrayList<>(1000);
		this.buffer = new ArrayDeque<>(length);

		this.stream.forEach(x -> {
			if (this.buffer.size() >= length) {
				signals.add(new DurableSignal(this.buffer));
				this.buffer = new ArrayDeque<>(length);
			} else {
				this.buffer.add(x);
			}
		});

		return signals;
	}
}
