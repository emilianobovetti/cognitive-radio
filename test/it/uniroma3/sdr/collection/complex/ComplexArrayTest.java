package it.uniroma3.sdr.collection.complex;

import static org.junit.Assert.*;

import java.util.Optional;

import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.Complex;

import org.junit.Before;
import org.junit.Test;

public class ComplexArrayTest {

	private ComplexCollection empty;
	
	private ComplexCollection one;
	
	@Before
	public void setUp() throws Exception {
		empty = new ComplexArray(new Complex[1]);
		
		Complex[] o = new Complex[10];
		o[0] = new CartesianComplex(1, 0);
		one = new ComplexArray(o);
	}

	@Test
	public void emptyCollectionDimensionTest() {
		assertEquals(0, empty.stream().count());
	}

	@Test
	public void emptyCollectionReduceTest() {
		assertEquals(Optional.empty(), empty.stream().reduce((a, b) -> a.add(b)));
	}
	
	@Test
	public void oneCollectionDimensionTest() {
		assertEquals(1, one.stream().count());
	}
	
	@Test
	public void oneCollectionReduceTest() {
		assertEquals(Optional.of(new CartesianComplex(1, 0)), one.stream().reduce((a, b) -> a.add(b)));
	}
}
