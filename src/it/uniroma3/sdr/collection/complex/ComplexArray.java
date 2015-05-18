package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Implementa una collezione di complessi memorizzata tramite
 * un array.
 * 
 * @author emiliano
 *
 */
public class ComplexArray implements ComplexCollection {
	
	private Complex[] collection;
	
	/**
	 * @param collection	L'array di complessi
	 */
	public ComplexArray(Complex[] collection) {
		this.collection = collection;
	}
	
	/**
	 * @return Lo stream di complessi escludendo i valori
	 * 	'null' eventualmente presenti nell'array.
	 */
	@Override
	public Stream<Complex> stream() {
		return Arrays.stream(this.collection).filter(x -> x != null);
	}
}
