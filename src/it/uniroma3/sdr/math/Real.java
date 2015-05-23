package it.uniroma3.sdr.math;

/**
 * Gestione di numeri reali.
 *
 * Created by emiliano on 23/05/15.
 */
public class Real {

	/**
	 * Definisce una soglia di tolleranza per effettuare il confronto
	 * tra i numeri in virgola mobile
	 *
	 * Due numeri in virgola mobile nelle operazioni tra complessi vengono
	 * definiti uguali se il modulo della loro differenza e' strettamente
	 * minore a questa soglia
	 */
	public static final double TOLERANCE = 0.00001;


	/**
	 * Definisce un metodo di confronto tra numeri in virgola mobile
	 * tramite la soglia TOLERANCE.
	 *
	 * @param a	double a
	 * @param b	double b
	 * @return	true se la differenza tra i due numeri e' inferiore della
	 * 	soglia
	 */
	public static boolean equals(double a, double b) {
		return Math.abs(a - b) < TOLERANCE;
	}

	/**
	 * @param a	double a
	 * @return	true se a e' inferiore della soglia
	 */
	public static boolean isZero(double a) {
		return Math.abs(a) < TOLERANCE;
	}
}
