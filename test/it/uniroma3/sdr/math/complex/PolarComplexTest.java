package it.uniroma3.sdr.math.complex;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PolarComplexTest {

	private Complex zero;
	
	private Complex one;
	
	@Before
	public void setUp() throws Exception {
		zero = new PolarComplex(0, 0);
		one = new PolarComplex(1, 0);
	}

	@Test
	public void zeroModulusTest() {
		assertEquals(0, zero.modulus(), 0);
	}
	
	@Test
	public void oneModulusTest() {
		assertEquals(1, one.modulus(), 0);
	}
	
	@Test
	public void zeroConjugateTest() {
		assertEquals(zero, zero.conjugate());
	}
	
	@Test
	public void oneConjugateTest() {
		assertEquals(one, one.conjugate());
	}
	
	@Test
	public void zeroPlusZeroTest() {
		assertEquals(zero, zero.add(zero));
	}
	
	@Test
	public void zeroPlusOneTest() {
		assertEquals(one, zero.add(one));
	}
	
	@Test
	public void onePlusZeroTest() {
		assertEquals(one, one.add(zero));
	}
	
	@Test
	public void zeroSubZeroTest() {
		assertEquals(zero, zero.sub(zero));
	}
	
	@Test
	public void zeroSubOneTest() {
		assertEquals(new CartesianComplex(-1, 0), zero.sub(one));
	}
	
	@Test
	public void oneSubZeroTest() {
		assertEquals(one, one.sub(zero));
	}
	
	@Test
	public void oneSubOneTest() {
		assertEquals(zero, one.sub(one));
	}
	
	@Test
	public void zeroMultZeroTest() {
		assertEquals(zero, zero.mult(zero));
	}
	
	@Test
	public void zeroMultOneTest() {
		assertEquals(zero, zero.mult(one));
	}
	
	@Test
	public void oneMultZeroTest() {
		assertEquals(zero, one.mult(zero));
	}
	
	@Test
	public void oneMultOneTest() {
		assertEquals(one, one.mult(one));
	}
	
	@Test(expected = ArithmeticException.class)
	public void zeroDivZeroTest() {
		zero.div(zero);
	}
	
	@Test
	public void zeroDivOneTest() {
		assertEquals(zero, zero.div(one));
	}
	
	@Test(expected = ArithmeticException.class)
	public void oneDivZeroTest() {
		one.div(zero);
	}
	
	@Test
	public void oneDivOneTest() {
		assertEquals(one, one.div(one));
	}
	
	@Test
	public void zeroToPolar() {
		assertEquals(0, zero.toPolar().modulus(), 0);
	}
	
	@Test
	public void zeroEqualsZeroTest() {
		assertTrue(zero.equals(zero));
	}
	
	@Test
	public void oneEqualsOneTest() {
		assertTrue(one.equals(one));
	}
	
	@Test
	public void zeroEqualsOneTest() {
		assertFalse(zero.equals(one));
	}
	
	@Test
	public void oneEqualsZeroTest() {
		assertFalse(one.equals(zero));
	}

	@Test
	public void zeroToCartesianTest() {
		assertEquals(new CartesianComplex(0, 0), zero.toCartesian());
	}

	@Test
	public void oneToCartesianTest() {
		assertEquals(new CartesianComplex(1, 0), one.toCartesian());
	}
}

