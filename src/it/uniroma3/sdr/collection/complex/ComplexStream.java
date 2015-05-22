package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;
import it.uniroma3.sdr.math.complex.ComplexGenerator;

import java.util.stream.Stream;

/**
 * Implementa una collezione di complessi tramite uno stream.
 * 
 * @author emiliano
 *
 */
public class ComplexStream implements ComplexCollection {
	
	private Stream<Complex> stream;

	/**
	 * @param stream	Lo stream di complessi
	 */
	public ComplexStream(Stream<Complex> stream) {
		this.stream = stream;
	}
	
	/**
	 * Costruisce una collezione di complessi a partire da uno stream,
	 * limitando la dimensione della collezione in base al parametro
	 * maxLength
	 * 
	 * @param stream	Lo stream di complessi
	 * @param maxLength	Lunghezza massima della collezione
	 */
	public ComplexStream(Stream<Complex> stream, long maxLength) {
		this.stream = stream.limit(maxLength);
	}
	
	/**
	 * Costruisce una collezione di complessi a partire da un
	 * ComplexGenerator e dalla lunghezza massima della collezione
	 * 
	 * @param generator	Il generatore di numeri complessi
	 * @param maxLength	Lunghezza massima della collezione
	 */
	public ComplexStream(ComplexGenerator generator, long maxLength) {
		this.stream = Stream.generate(() -> generator.generate()).limit(maxLength);
	}

	/**
	 * @return Lo stream di complessi
	 */
	@Override
	public Stream<Complex> stream() {
		return this.stream;
	}
}
