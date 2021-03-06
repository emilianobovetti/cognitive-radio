package it.uniroma3.sdr.collection;

import java.util.ArrayDeque;
import java.util.Collection;
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

    private Collection<T> collection;

	/**
	 * Crea un nuovo stream durevole a partire da un normale Stream
	 *
	 * @param stream	Stream in input
	 * @param expectedLength	Dimensione attesa della collezione
	 */
    public DurableStreamProxy(Stream<T> stream, int expectedLength) {
        this.collection = new ArrayDeque<>(expectedLength);

		this.stream = stream.peek(this.collection::add);
    }

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
