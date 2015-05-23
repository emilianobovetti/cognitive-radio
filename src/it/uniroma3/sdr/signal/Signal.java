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
	
	private ComplexCollection collection = null;
	
	private Optional<Double> energy = Optional.empty();
	
	/**
	 * Consente alle sotto classi di inizializzare i campioni
	 * del segnale
	 * Il segnale puo' essere inizializzato una sola volta
	 * 
	 * @param collection	Collezione di campioni del segnale
	 * @throws SignalStateException	Se il segnale e' gia' stato inizializzato
	 */
	public void initialize(ComplexCollection collection) {
		if (this.collection != null) {
			throw new SignalStateException("Signal has already been initialized");
		}

		this.collection = collection;
	}
	
	/**
	 * @return	Uno stream di campion del segnale
	 * @throws SignalStateException	Se il segnale non e' stato inizializzato
	 */
	public Stream<Complex> stream() {
		if (this.collection == null) {
			throw new SignalStateException("Signal has not been initialized");
		}

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

		// Pair.first = stream length
		// Pair.second = data
		Pair<Integer, Double> result = this.stream()
				.map(x -> new Pair<>(1, x.modulus2()))
				.reduce(new Pair<>(0, 0.0),
						(a, b) -> new Pair<>(a.first + b.first, a.second + b.second));

		if (result.first == 0) {
			throw new SignalStateException("Energy does not exist on empty signal");
		}

		this.energy = Optional.of(result.second / result.first);
		return this.energy.get();
	}

	/**
	 * @return	Stima del rapporto segnale-rumore
	 * 	L'energia del segnale utile viene considerata unitaria
	 */
	public double snr() {
		return 1 / (this.energy() - 1);
	}

	/**
	 * @return Stima del rapporto segnale-rumore in decibel
	 */
	public double snrDb() {
		return 10 * Math.log10(this.snr());
	}
}
