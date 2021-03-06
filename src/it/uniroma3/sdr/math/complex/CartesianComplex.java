package it.uniroma3.sdr.math.complex;

import it.uniroma3.sdr.math.Real;

public class CartesianComplex implements Complex {

	private double real;
	
	private double imaginary;
	
	/**
	 * Costruisce un complesso in forma cartesiana
	 * 
	 * @param real	Parte reale
	 * @param imaginary	Parte immaginaria
	 */
	public CartesianComplex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	@Override
	public double modulus() {
		return Math.sqrt(this.modulus2());
	}

	@Override
	public double modulus2() {
		return Math.pow(this.real, 2) + Math.pow(this.imaginary, 2);
	}

	@Override
	public CartesianComplex conjugate() {
		return new CartesianComplex(this.real, - this.imaginary);
	}
	
	@Override
	public CartesianComplex add(Complex c) {
		CartesianComplex that = c.toCartesian();
		return new CartesianComplex(this.real + that.real, this.imaginary + that.imaginary);
	}
	
	@Override
	public CartesianComplex sub(Complex c) {
		CartesianComplex that = c.toCartesian();
		return new CartesianComplex(this.real - that.real, this.imaginary - that.imaginary);
	}
	
	@Override
	public CartesianComplex mult(Complex c) {
		CartesianComplex that = c.toCartesian();
		return new CartesianComplex(
				this.real * that.real - this.imaginary * that.imaginary,
				this.imaginary * that.real + this.real * that.imaginary
		);
	}
	
	@Override
	public CartesianComplex div(Complex c) {
		CartesianComplex that = c.toCartesian();

		if (Real.isZero(that.real) && Real.isZero(that.imaginary)) {
			throw new ArithmeticException("Division by 0");
		}
		
		double denom = Math.pow(that.real, 2) + Math.pow(that.imaginary, 2);
		return new CartesianComplex(
				(this.real * that.real + this.imaginary + that.imaginary) / denom,
				(this.imaginary * that.real - this.real * that.imaginary) / denom
		);
	}
	
	@Override
	public PolarComplex toPolar() {
		if (Real.isZero(this.real)) {
			return new PolarComplex(this.imaginary, Math.PI / 2);
		}
		
		double argument = Math.atan(this.imaginary / this.real);
		if (this.real < 0) {
			argument += Math.PI;
		}
		
		return new PolarComplex(this.modulus(), argument);
	}
	
	@Override
	public CartesianComplex toCartesian() {
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(imaginary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean equals(CartesianComplex that) {
		return Real.equals(this.real, that.real) &&
				Real.equals(this.imaginary, that.imaginary);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( ! (obj instanceof Complex))
			return false;
		
		if (getClass() == obj.getClass()) {
			return this.equals((CartesianComplex) obj);
		}
		
		return this.equals(((Complex) obj).toCartesian());
	}
	
	@Override
	public String toString() {
		return "Re = " + String.valueOf(this.real) + " Im = " + String.valueOf(this.imaginary);
	}
}
