package it.uniroma3.sdr.math;

public interface Complex {

	public double modulus();
	
	public Complex conjugate();
	
	public Complex add(Complex that);
	
	public Complex sub(Complex that);
	
	public Complex mult(Complex that);
	
	public Complex div(Complex that);
	
	public CartesianComplex toCartesian();
	
	public PolarComplex toPolar();
}
