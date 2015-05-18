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
		for (double d : array) {
			total += d;
		}
		return total / array.length;
	}
}
