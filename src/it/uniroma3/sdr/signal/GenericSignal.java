package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.collection.complex.ComplexCollection;
import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.complex.Complex;

public class GenericSignal extends Signal {
	
	public GenericSignal(ComplexCollection collection) {
		this.initialize(collection);
	}
	
	public GenericSignal(Stream<Complex> stream) {
		this.initialize(new ComplexStream(stream));
	}
	
	public GenericSignal(Stream<Complex> stream, long maxSampleLength) {
		this.initialize(new ComplexStream(stream, maxSampleLength));
	}
}
