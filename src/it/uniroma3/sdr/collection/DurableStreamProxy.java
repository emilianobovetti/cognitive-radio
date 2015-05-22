package it.uniroma3.sdr.collection;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Gestisce una collezione durevole generata da uno stream.
 *
 * I dati presenti negli stream non possono essere riutilizzati una volta
 * elaborati. Tramite questo proxy e' possibile salvare i dati dello
 * stream in una coda nel momento stesso in cui vengono elaborati.
 * In questo modo e' possibile riutilizzarli piu' volte.
 *
 * Dal momento che i dati devono essere copiati in una coda mentre vengono
 * processati, questo sistema risulta piu' lento rispetto al semplice Stream
 * pertanto verra' usato solo se i dati dovranno essere effettivamente riutilizzati.
 *
 * Created by emiliano on 22/05/15.
 */
public class DurableStreamProxy<T> {

    private Stream<T> stream;

    private Queue<T> collection;

	/**
	 * Crea un nuovo stream durevole a partire da un normale Stream
	 *
	 * @param stream	Stream in input
	 */
	// STABLE!
    public DurableStreamProxy(Stream<T> stream) {
        this.collection = new ArrayDeque<>();

		this.stream = stream.map(x -> {
			//Runnable r = () -> this.collection.add(x);	r.run();
			this.collection.add(x);
			return x;
		});
    }

	/*
	// TESTING
	private static final int BUFFER_LENGTH = 100;
	private T[] buffer;
	private int bufferIndex = 0;
	public DurableStreamProxy(Stream<T> stream) {
		this.collection = new ArrayDeque<>(1000);
		this.buffer = (T[]) new Object[BUFFER_LENGTH];

		Runnable emptyBuffer = () -> this.collection.addAll(Arrays.asList(this.buffer));

		this.stream = stream.map(x -> {
			this.buffer[this.bufferIndex] = x;
			this.bufferIndex++;

			if (this.bufferIndex >= BUFFER_LENGTH) {
				emptyBuffer.run();
				this.buffer = (T[]) new Object[BUFFER_LENGTH];
				this.bufferIndex = 0;
			}

			return x;
		});
	}
	*/

	/**
	 * @return	Stream<T>
	 */
	public Stream<T> stream() {
		if (this.collection.isEmpty()) {
			return this.stream;
		} else {
			return this.collection.stream();
		}
	}
}