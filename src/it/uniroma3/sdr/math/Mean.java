package it.uniroma3.sdr.math;

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
			throw new IllegalArgumentException("Mean does not exists on empty set");
		}

		return total / count;
	}
}
