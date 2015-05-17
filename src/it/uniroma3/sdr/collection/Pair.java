package it.uniroma3.sdr.collection;

/**
 * Memorizza una coppia di valori.
 * 
 * @author emiliano
 *
 * @param <A> Il tipo del primo valore della coppia
 * @param <B> Il tipo del secondo valore
 */
public class Pair<A, B> {

	public A first;
	
	public B second;

	/**
	 * Costruisce una nuova coppia.
	 * 
	 * @param first		Primo elemento
	 * @param second	Secondo elemento
	 */
	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
}
