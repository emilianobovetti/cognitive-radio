package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.Pair;
import it.uniroma3.sdr.collection.complex.ComplexCollection;
import it.uniroma3.sdr.math.complex.Complex;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Interfaccia comune per tutti i tipi di segnali e rumori.
 * Fornisce i metodi di base per elaborarli.
 * 
 * @author emiliano
 *
 */
public abstract class Signal {
	
	private ComplexCollection collection;
	
	private Optional<Double> energy = Optional.empty();
	
	/**
	 * Consente alle sotto classi di inizializzare i campioni
	 * del segnale
	 * 
	 * @param collection	Collezione di campioni del segnale
	 */
	protected void initialize(ComplexCollection collection) {
		this.collection = collection;
	}
	
	/**
	 * @return	Uno stream di campion del segnale
	 */
	public Stream<Complex> stream() {
		return this.collection.stream();
	}
	
	/**
	 * Calcola l'energia di un segnale.
	 * Il risultato viene messo in cache, in questo modo il calcolo
	 * viene effettuato solo la prima volta che il metodo viene chiamato
	 * 
	 * @return	L'energia del segnale
	 */
	public double energy() {
		if (this.energy.isPresent()) {
			return this.energy.get();
		}
		
		Pair<Integer, Double> result = this.stream()
				.map(x -> new Pair<Integer, Double>(1, Math.pow(x.modulus(), 2)))
				.reduce(new Pair<Integer, Double>(0, 0.0),
						(a, b) -> new Pair<Integer, Double>(a.first + b.first, a.second + b.second));
		
		this.energy = Optional.of(result.second / result.first);
		return this.energy.get();
	}
}
