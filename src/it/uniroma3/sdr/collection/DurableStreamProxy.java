package it.uniroma3.sdr.collection;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Created by emiliano on 22/05/15.
 */
public class DurableStreamProxy<T> {

    private Stream<T> stream;

    private Queue<T> collection;

    public DurableStreamProxy(Stream<T> stream) {
        this.collection = new ArrayDeque<>();

		this.stream = stream.map(x -> {
			//Runnable r = () -> this.collection.add(x);	r.run();
			this.collection.add(x);
			return x;
		});
    }

	/**
	 * @return	Lo stream di complessi
	 */
	public Stream<T> stream() {
		if (this.collection.isEmpty()) {
			return this.stream;
		} else {
			return this.collection.stream();
		}
	}
}
