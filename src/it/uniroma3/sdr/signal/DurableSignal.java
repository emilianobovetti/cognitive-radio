package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.collection.complex.ComplexArray;
import it.uniroma3.sdr.collection.complex.DurableComplexStream;
import it.uniroma3.sdr.math.complex.Complex;

public class DurableSignal extends GenericSignal {

	public DurableSignal(Complex[] samples) {
		super(new ComplexArray(samples));
	}

	public DurableSignal(Stream<Complex> stream) {
		super(new DurableComplexStream(stream));
	}
	
	public DurableSignal(Stream<Complex> stream, long maxSampleLength) {
		super(new DurableComplexStream(stream, maxSampleLength));
	}
}
