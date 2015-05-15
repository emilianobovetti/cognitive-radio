package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.math.Complex;
import it.uniroma3.sdr.math.ComplexCollection;

import java.util.stream.Stream;

public abstract class Signal {
	
	private ComplexCollection collection;
	
	public void initialize(ComplexCollection collection) {
		this.collection = collection;
	}
	
	public int length() {
		return this.collection.length();
	}
	
	public Stream<Complex> stream() {
		return this.collection.stream();
	}
	
	public double energy() {
		return this.stream().map(x -> Math.pow(x.modulus(), 2)).reduce(0.0, (a, b) -> a + b) / this.length();
	}
}
