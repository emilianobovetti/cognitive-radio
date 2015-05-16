package it.uniroma3.sdr.math;

public class Variance {
	
	public static double evaluate(Double[] array) {
		return evaluate(array, Mean.evaluate(array));
	}
	
	public static double evaluate(Double[] array, double mean) {
		double var = 0.0;
		for (double d : array) {
			var += Math.pow(d - mean, 2);
		}
		return var / array.length;
	}
}
