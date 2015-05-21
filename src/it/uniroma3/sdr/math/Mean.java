package it.uniroma3.sdr.math;

import it.uniroma3.sdr.collection.Pair;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Classe per il calcolo della media.
 * 
 * @author emiliano
 *
 */
public class Mean {

	/**
	 * @param array	I valori su cui calcolare la media
	 * @return	Media aritmetica dei valori
	 */
	public static double evaluate(Double[] array) {
		double total = 0.0;
		int count = 0;
		for (Double d : array) {
			if (d != null) {
				total += d;
				count++;
			}
		}

		if (count == 0) {
			throw new IllegalArgumentException("Mean does not exist on empty set");
		}

		return total / count;
	}

	/**
	 * @param stream	I valori su cui calcolare la media
	 * @return	Media aritmetica dei valori
	 */
	public static double evaluate(Stream<Double> stream) {
		// result.first = length
		Optional<Pair<Integer, Double>> result = stream
				.map(x -> new Pair<>(1, x))
				.reduce((a, b) -> new Pair<>(a.first + b.first, a.second + b.second));

		result.orElseThrow(() -> new IllegalArgumentException("Mean does not exist on empty set"));
		return result.get().second / result.get().first;
	}
}
