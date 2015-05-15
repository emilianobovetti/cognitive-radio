package it.uniroma3.sdr.collection;

import it.uniroma3.sdr.math.Complex;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ComplexArray implements ComplexCollection {

	private int length;
	
	private Complex[] collection;
	
	public ComplexArray(int length, Complex[] collection) {
		this.collection = collection;
		this.length = length;
	}
	
	public ComplexArray(int maxLength, Stream<Complex> stream) {
		this.collection = new Complex[maxLength];
		
		int index = 0;
		Iterator<Complex> iterator = stream.iterator();
		while (index < maxLength && iterator.hasNext()) {
			this.collection[index] = iterator.next();
			index++;
		}
		
		stream.close();
		this.length = index;
	}
	
	@Override
	public Stream<Complex> stream() {
		return Arrays.stream(this.collection).filter(x -> x != null);
	}
	
	@Override
	public int length() {
		return this.length;
	}
}
