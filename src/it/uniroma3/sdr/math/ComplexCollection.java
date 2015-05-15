package it.uniroma3.sdr.math;

import java.util.stream.Stream;

public interface ComplexCollection {

	public int length();
	
	public Stream<Complex> stream();
}
