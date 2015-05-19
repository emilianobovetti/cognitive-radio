package it.uniroma3.sdr.collection.complex;

import static org.junit.Assert.*;
import it.uniroma3.sdr.math.complex.CartesianComplex;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class DurableComplexStreamTest {

	private DurableComplexStream empty;
	
	private DurableComplexStream one;
	
	@Before
	public void setUp() throws Exception {
		empty = new DurableComplexStream(Stream.empty());
		
		one = new DurableComplexStream(Stream.of(new CartesianComplex(1, 0)));
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
	
	@Test
	public void isOneDurableTest() {
		assertEquals(1, one.stream().count());
		assertEquals(1, one.stream().count());
	}
}
