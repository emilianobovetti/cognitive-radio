package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.ComplexCollection;
import it.uniroma3.sdr.collection.Pair;
import it.uniroma3.sdr.math.Complex;

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
						(a, b) -> new Pair<Integer, Double>(a.getFirst() + b.getFirst(), a.getSecond() + b.getSecond()));
		
		this.energy = Optional.of(result.getSecond() / result.getFirst());
		return this.energy.get();
	}
}
