package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	public List<DurableSignal> split(int length) {
		Iterator<Complex> iterator = this.stream.iterator();
		List<DurableSignal> signals = new LinkedList<>();
		Complex[] samples = new Complex[length];
		int index = 0;
		while (iterator.hasNext()) {
			if (index >= length) {
				signals.add(new DurableSignal(samples));
				samples = new Complex[length];
				index = 0;
			}
			samples[index] = iterator.next();
			index++;
		}
		signals.add(new DurableSignal(samples));
		return signals;
	}
}
