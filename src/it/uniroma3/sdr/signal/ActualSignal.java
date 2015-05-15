package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.collection.ComplexArray;
import it.uniroma3.sdr.math.Complex;

public class ActualSignal extends Signal {
	
	public ActualSignal(int maxLength, Stream<Complex> stream) {
		this.initialize(new ComplexArray(maxLength, stream));
	}
	
	public ActualSignal(int length, Complex[] samples) {
		this.initialize(new ComplexArray(length, samples));
	}
}
