package it.uniroma3.sdr.collection;

import it.uniroma3.sdr.math.Complex;

import java.util.stream.Stream;

public interface ComplexCollection {
	
	public Stream<Complex> stream();
}
