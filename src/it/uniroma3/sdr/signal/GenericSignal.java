package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.collection.complex.ComplexCollection;
import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.complex.Complex;

/**
 * Gestisce un segnale generico che puo' essere inizializzato
 * con una collezione di complessi.
 * 
 * @author emiliano
 *
 */
public class GenericSignal extends Signal {
	
	/**
	 * @param collection	Campioni del segnale
	 */
	public GenericSignal(ComplexCollection collection) {
		this.initialize(collection);
	}
	
	/**
	 * @param stream	Stream di campioni del segnale
	 */
	public GenericSignal(Stream<Complex> stream) {
		this.initialize(new ComplexStream(stream));
	}
	
	/**
	 * @param stream	Stream di campioni del segnale
	 * @param maxSampleLength	Numero massimo di campioni del segnale
	 */
	public GenericSignal(Stream<Complex> stream, long maxSampleLength) {
		this.initialize(new ComplexStream(stream, maxSampleLength));
	}
}
