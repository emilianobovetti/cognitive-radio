package it.uniroma3.sdr.math;

import java.util.stream.Stream;

public class Variance {

	public static double evaluate(Stream<Double> stream) {
		return evaluate(stream, Mean.evaluate(stream));
	}
	
	public static double evaluate(Stream<Double> stream, double mean) {
		return stream.reduce(0.0, (a, b) -> a + Math.pow(mean - b, 2));
	}
}
