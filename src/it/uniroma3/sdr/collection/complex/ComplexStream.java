package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;
import it.uniroma3.sdr.math.complex.ComplexGenerator;

import java.util.stream.Stream;

public class ComplexStream implements ComplexCollection {
	
	private Stream<Complex> stream;

	public ComplexStream(Stream<Complex> stream) {
		this.stream = stream;
	}
	
	public ComplexStream(Stream<Complex> stream, long maxSampleLength) {
		this.stream = stream.limit(maxSampleLength);
	}
	
	public ComplexStream(ComplexGenerator generator, long maxSampleLength) {
		this.stream = Stream.iterate(generator.generate(), (x) -> generator.generate()).limit(maxSampleLength);
	}

	@Override
	public Stream<Complex> stream() {
		return this.stream;
	}
}
