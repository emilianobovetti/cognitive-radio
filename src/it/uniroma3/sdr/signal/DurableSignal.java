package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.collection.complex.ComplexArray;
import it.uniroma3.sdr.collection.complex.DurableComplexStream;
import it.uniroma3.sdr.math.complex.Complex;

/**
 * Gestisce un segnale durevole.
 * 
 * Se viene inizializzato con uno stream, la prima volta che
 * i dati dello stream vengono processati, questi verranno
 * copiati in una coda. Per questo motivo il processo e' piu'
 * lento la prima volta che i dati vengono elaborati, ma consente
 * di riutilizzare i dati dello stream.
 * 
 * Se viene inizializzato con altri tipi di collezioni, non ci
 * sono effetti collaterali.
 * 
 * @author emiliano
 *
 */
public class DurableSignal extends GenericSignal {

	/**
	 * @param samples	I campioni del segnale
	 */
	public DurableSignal(Complex[] samples) {
		super(new ComplexArray(samples));
	}

	/**
	 * @param stream	Stream di dati del segnale
	 */
	public DurableSignal(Stream<Complex> stream) {
		super(new DurableComplexStream(stream));
	}
	
	/**
	 * @param stream	Stream di dati del segnale
	 * @param maxSampleLength	Numero massimo di campioni del segnale
	 */
	public DurableSignal(Stream<Complex> stream, long maxSampleLength) {
		super(new DurableComplexStream(stream, maxSampleLength));
	}
}