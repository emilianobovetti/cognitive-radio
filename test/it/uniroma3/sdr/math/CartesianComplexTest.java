package it.uniroma3.sdr.math;

import static org.junit.Assert.*;
import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.Complex;

import org.junit.Before;
import org.junit.Test;

public class CartesianComplexTest {
	
	private Complex minusOne = new CartesianComplex(-1, 0);
	
	private Complex minusI = new CartesianComplex(0, -1);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void minusOneModulusTest() {
		assertEquals(minusOne.modulus(), 1, 0);
	}
	
	@Test
	public void minusIModulusTest() {
		assertEquals(minusI.modulus(), 1, 0);
	}
}
