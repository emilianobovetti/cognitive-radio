package it.uniroma3.sdr.math.complex;

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
