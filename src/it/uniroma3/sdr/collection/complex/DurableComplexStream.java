package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.collection.DurableStreamProxy;
import it.uniroma3.sdr.math.complex.Complex;

import java.util.function.Supplier;
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
	 * @param expectedLength	Dimensione attesa dello stream.
	 *                          Questa dimensione non vincola la dimensione massima
	 */
	public DurableComplexStream(Stream<Complex> stream, int expectedLength) {
		this.collection = new DurableStreamProxy<>(stream, expectedLength);
	}

	/**
	 * Vedi ComplexStream
	 * 
	 * @param generator	Il generatore di numeri complessi
	 * @param maxLength	Lunghezza massima della collezione
	 */
	public DurableComplexStream(Supplier<Complex> generator, int maxLength) {
		this(Stream.generate(generator).limit(maxLength), maxLength);
	}

	public Stream<Complex> stream() {
		return this.collection.stream();
	}
}
