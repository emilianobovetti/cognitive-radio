package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.stream.Stream;

public interface ComplexCollection {
	
	public Stream<Complex> stream();
}
