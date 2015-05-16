package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;
import it.uniroma3.sdr.math.complex.ComplexGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DurableComplexStream implements ComplexCollection {

	private Stream<Complex> stream;
	
	private List<Complex> list;
	
	public DurableComplexStream(Stream<Complex> stream) {
		this.list = new ArrayList<Complex>();
		this.stream = stream.map(x -> {
			this.list.add(x);
			return x;
		});
	}
	
	public DurableComplexStream(Stream<Complex> stream, long maxSampleLength) {
		this(stream.limit(maxSampleLength));
	}
	
	public DurableComplexStream(ComplexGenerator generator, long maxSampleLength) {
		this(Stream.iterate(generator.generate(), (x) -> generator.generate()).limit(maxSampleLength));
	}
	
	@Override
	public Stream<Complex> stream() {
		if (this.list.isEmpty()) {
			return this.stream;
		} else {
			return this.list.stream();
		}
	}
}
