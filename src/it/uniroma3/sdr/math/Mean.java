package it.uniroma3.sdr.math;

import java.util.stream.Stream;

public class Mean {

	public static double evaluate(Stream<Double> stream, int length) {
		return stream.reduce(0.0, (a, b) -> a + b) / length;
	}
}
