package it.uniroma3.sdr.math;

import java.util.concurrent.Callable;

public class ComplexCollection {

	private int length;
	
	private double[] real;
	
	private double[] imaginary;
	
	public ComplexCollection(int length) {
		this.length = length;
	}
	
	public void initialize(RealGenerator generator) {
		this.real = new double[this.length];
		this.imaginary = new double[this.length];
		
		for (int i = 0; i < this.length; i++) {
			real[i] = generator.generate();
			imaginary[i] = generator.generate();
		}
	}
}
