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
	 * @return	Modulo di c
	 */
	double modulus();

	/**
	 * @return	Modulo^2 di c
	 */
	double modulus2();

	/**
	 * @return	Complesso coniugato di c
	 */
	Complex conjugate();

	/**
	 * @param that	Complesso c2
	 * @return	c1 + c2
	 */
	Complex add(Complex that);

	/**
	 * @param that	Complesso c2
	 * @return	c1 - c2
	 */
	Complex sub(Complex that);

	/**
	 * @param that	Complesso c2
	 * @return	c1 * c2
	 */
	Complex mult(Complex that);

	/**
	 * @param that	Complesso c2
	 * @return	c1 / c2
	 */
	Complex div(Complex that);

	/**
	 * @return	c in forma cartesiana
	 */
	CartesianComplex toCartesian();

	/**
	 * @return	c in forma polare
	 */
	PolarComplex toPolar();
}
