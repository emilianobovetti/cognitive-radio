package it.uniroma3.sdr.math;

import java.util.stream.Stream;

public class ComplexStream implements ComplexCollection {

	private int length;
	
	private Stream<Complex> stream;

	public ComplexStream(int length, ComplexGenerator generator) {
		this.length = length;
		this.stream = Stream.iterate(generator.generate(), (x) -> generator.generate()).limit(this.length);
	}
	
	@Override
	public int length() {
		return this.length;
	}

	@Override
	public Stream<Complex> stream() {
		return this.stream;
	}
}
