package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.Pair;
import it.uniroma3.sdr.collection.complex.ComplexCollection;
import it.uniroma3.sdr.math.complex.Complex;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class Signal {
	
	private ComplexCollection collection;
	
	private Optional<Double> energy = Optional.empty();
	
	public void initialize(ComplexCollection collection) {
		this.collection = collection;
	}
	
	public Stream<Complex> stream() {
		return this.collection.stream();
	}
	
	public double energy() {
		if (this.energy.isPresent()) {
			return this.energy.get();
		}
		
		Pair<Integer, Double> result = this.stream()
				.map(x -> new Pair<Integer, Double>(1, Math.pow(x.modulus(), 2)))
				.reduce(new Pair<Integer, Double>(0, 0.0),
						(a, b) -> new Pair<Integer, Double>(a.first + b.first, a.second + b.second));
		
		this.energy = Optional.of(result.second / result.first);
		return this.energy.get();
	}
}
