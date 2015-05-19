package it.uniroma3.sdr.math.complex;

import java.util.function.BiFunction;

/**
 * Interfaccia per un numero complesso c.
 * 
 * Ogni operazione restituisce un nuovo numero complesso
 * che rappresenta il risultato dell'operazione.
 * 
 * @author emiliano
 *
 */
public interface Complex {

	/**
	 * Definisce una soglia di tolleranza per effettuare il confronto
	 * tra i numeri in virgola mobile
	 * 
	 * Due numeri in virgola mobile nelle operazioni tra complessi vengono
	 * definiti uguali se il modulo della loro differenza e' strettamente
	 * minore a questa soglia
	 */
	static double TOLERANCE = 0.00001;
	
	/**
	 * Definisce un metodo di confronto tra numeri in virgola mobile
	 * tramite la soglia TOLERANCE.
	 */
	static BiFunction<Double, Double, Boolean> COMPARE = (a, b) -> Math.abs(a - b) < Complex.TOLERANCE;
	
	/**
	 * @return	Modulo di c
	 */
	public double modulus();
	
	/**
	 * @return	Complesso coniugato di c
	 */
	public Complex conjugate();
	
	/**
	 * @param that	Complesso c2
	 * @return	c1 + c2
	 */
	public Complex add(Complex that);
	
	/**
	 * @param that	Complesso c2
	 * @return	c1 - c2
	 */
	public Complex sub(Complex that);
	
	/**
	 * @param that	Complesso c2
	 * @return	c1 * c2
	 */
	public Complex mult(Complex that);
	
	/**
	 * @param that	Complesso c2
	 * @return	c1 / c2
	 */
	public Complex div(Complex that);
	
	/**
	 * @return	c in forma cartesiana
	 */
	public CartesianComplex toCartesian();
	
	/**
	 * @return	c in forma polare
	 */
	public PolarComplex toPolar();
}
