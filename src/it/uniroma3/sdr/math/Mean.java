package it.uniroma3.sdr.math;

public class Mean {

	public static double evaluate(Double[] array) {
		double total = 0.0;
		for (double d : array) {
			total += d;
		}
		return total / array.length;
	}
}
