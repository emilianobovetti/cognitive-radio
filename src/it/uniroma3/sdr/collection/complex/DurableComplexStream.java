package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.collection.DurableStreamProxy;
import it.uniroma3.sdr.math.complex.Complex;
import it.uniroma3.sdr.math.complex.ComplexGenerator;

import java.util.stream.Stream;

/**
 * Gestisce una collezione durevole di complessi generata da uno stream.
 * 
 * I dati presenti negli stream non possono essere riutilizzati una volta
 * elaborati. Tramite questa collezione e' possibile salvare i dati dello
 * stream in una coda nel momento stesso in cui vengono elaborati.
 * In questo modo e' possibile riutilizzarli piu' volte.
 * 
 * Dal momento che i dati devono essere copiati in una coda mentre vengono
 * processati, questa collezione risulta piu' lenta rispetto alla semplice
 * ComplexStream, pertanto verra' usata solo se i dati dovranno essere
 * effettivamente riutilizzati.
 * 
 * @author emiliano
 *
 */
public class DurableComplexStream implements ComplexCollection {

	private DurableStreamProxy<Complex> collection;
	
	/**
	 * @param stream	Lo stream di complessi
	 */
	public DurableComplexStream(Stream<Complex> stream) {
		this.collection = new DurableStreamProxy<>(stream);
	}

	/**
	 * @param stream	Lo stream di complessi
	 * @param maxLength	Lunghezza massima della collezione
	 */
	public DurableComplexStream(Stream<Complex> stream, long maxLength) {
		this(stream.limit(maxLength));
	}
	
	/**
	 * Vedi ComplexStream
	 * 
	 * @param generator	Il generatore di numeri complessi
	 * @param maxLength	Lunghezza massima della collezione
	 */
	public DurableComplexStream(ComplexGenerator generator, long maxLength) {
		this(Stream.iterate(generator.generate(), (x) -> generator.generate()).limit(maxLength));
	}

	public Stream<Complex> stream() {
		return this.collection.stream();
	}
}
