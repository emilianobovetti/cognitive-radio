package it.uniroma3.sdr.signal;

import java.util.stream.Stream;

import it.uniroma3.sdr.math.Complex;
import it.uniroma3.sdr.math.ComplexArray;

public class ActualSignal extends Signal {
	
	public ActualSignal(int maxLength, Stream<Complex> stream) {
		this.initialize(new ComplexArray(maxLength, stream));
	}
}
