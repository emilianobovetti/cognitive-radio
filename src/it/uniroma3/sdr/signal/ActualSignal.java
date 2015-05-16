package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.collection.complex.ComplexArray;
import it.uniroma3.sdr.collection.complex.ComplexStream;
import it.uniroma3.sdr.math.complex.Complex;

public class ActualSignal extends Signal {
	
	public ActualSignal(Complex[] samples) {
		this.initialize(new ComplexArray(samples));
	}
	
	public ActualSignal(Stream<Complex> stream) {
		this.initialize(new ComplexStream(stream));
	}
	
	public ActualSignal(Stream<Complex> stream, long maxSampleLength) {
		this.initialize(new ComplexStream(stream, maxSampleLength));
	}
}
