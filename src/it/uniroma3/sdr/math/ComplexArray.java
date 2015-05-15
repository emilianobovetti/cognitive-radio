package it.uniroma3.sdr.math;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ComplexArray implements ComplexCollection {

	private int length;
	
	private Complex[] collection;
	
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
