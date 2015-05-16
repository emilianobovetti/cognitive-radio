package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.Arrays;
import java.util.stream.Stream;

public class ComplexArray implements ComplexCollection {
	
	private Complex[] collection;
	
	public ComplexArray(Complex[] collection) {
		this.collection = collection;
	}
	
	@Override
	public Stream<Complex> stream() {
		return Arrays.stream(this.collection).filter(x -> x != null);
	}
}
