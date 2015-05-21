package it.uniroma3.sdr.math;

/**
 * Classe per il calcolo della varianza.
 * 
 * @author emiliano
 *
 */
public class Variance {

	/**
	 * @param array	I valori su cui calcolare la varianza
	 * @return	Varianza
	 */
	public static double evaluate(Double[] array) {
		return evaluate(array, Mean.evaluate(array));
	}

	/**
	 * Se la media dell'insieme in ingresso e' gia' nota
	 * e' possibile evitare di calcolarla nuovamente.
	 * 
	 * @param array	I valori su cui calcolare la varianza
	 * @param mean	Media dei valori
	 * @return	Varianza
	 */
	public static double evaluate(Double[] array, double mean) {
		double var = 0.0;
		int count = 0;
		for (Double d : array) {
			if (d != null) {
				var += Math.pow(d - mean, 2);
				count++;
			}
		}

		if (count == 0) {
			throw new IllegalArgumentException("Variance does not exist on empty set");
		}

		return var / count;
	}
}
