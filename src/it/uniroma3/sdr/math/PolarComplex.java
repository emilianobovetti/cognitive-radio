package it.uniroma3.sdr.math;

public class PolarComplex implements Complex {

	private double modulus;		// r | rho
	
	private double argument;	// phi | theta | phase | arg(z)
	
	public PolarComplex(double modulus, double argument) {
		this.modulus = modulus;
		this.argument = argument;
	}
	
	@Override
	public double modulus() {
		return this.modulus;
	}
	
	@Override
	public PolarComplex conjugate() {
		return new PolarComplex(this.modulus, - this.argument);
	}

	@Override
	public CartesianComplex add(Complex that) {
		return this.toCartesian().add(that.toCartesian());
	}

	@Override
	public CartesianComplex sub(Complex that) {
		return this.toCartesian().sub(that.toCartesian());
	}

	@Override
	public PolarComplex mult(Complex c) {
		PolarComplex that = c.toPolar();
		return new PolarComplex(
				this.modulus * that.modulus,
				this.argument + that.argument
		);
	}
	
	@Override
	public PolarComplex div(Complex c) {
		PolarComplex that = c.toPolar();
		return new PolarComplex(
				this.modulus / that.modulus,
				this.argument - that.argument
		);
	}

	@Override
	public PolarComplex toPolar() {
		return this;
	}
	
	@Override
	public CartesianComplex toCartesian() {
		return new CartesianComplex(
				this.modulus * Math.cos(this.argument),
				this.modulus * Math.sin(this.argument)
		);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(argument);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(modulus);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean equals(PolarComplex that) {
		if (Double.doubleToLongBits(argument) != Double
				.doubleToLongBits(that.argument))
			return false;
		if (Double.doubleToLongBits(modulus) != Double
				.doubleToLongBits(that.modulus))
			return false;
		return true;
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
			return this.equals((PolarComplex) obj);
		}
	
		return this.toCartesian().equals((Complex) obj);
	}
	
	@Override
	public String toString() {
		return "Mod = " + String.valueOf(this.modulus) + " Arg = " + String.valueOf(this.argument);
	}
}
