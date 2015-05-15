package it.uniroma3.sdr.math;

import java.util.stream.Stream;

public class Variance {

	public static double evaluate(Stream<Double> stream, int length) {
		double mean = Mean.evaluate(stream, length);
		return stream.reduce(0.0, (a, b) -> a + Math.pow(mean - b, 2)) / length;
	}
}
