package it.uniroma3.sdr.processing;

import it.uniroma3.sdr.collection.Pair;
import it.uniroma3.sdr.signal.Signal;

import java.util.List;
import java.util.stream.Stream;

/**
 * Classe per valutare l'energia di soglia.
 * 
 * @author emiliano
 *
 */
public class ThresholdTester {

	private double threshold;
	
	/**
	 * @param threshold	Energia di soglia
	 */
	public ThresholdTester(double threshold) {
		this.threshold = threshold;
	}
	
	/**
	 * Valuta la soglia su uno stream di segnali utili.
	 * 
	 * @param signals	Stream di segnali
	 * @return	Percentuale di successo [0, 1].
	 * 	La soglia deve essere inferiore all'energia di un segnale
	 */
	public double evaluateOnSignals(Stream<Signal> signals) {
		// Pair.first = stream length
		Pair<Integer, Integer> result = signals
				.map(x -> new Pair<>(1, (this.threshold < x.energy()) ? 1 : 0))
				.reduce(new Pair<>(0, 0),
						(a, b) -> new Pair<>(a.first + b.first, a.second + b.second));
		
		return (double) result.second / result.first;
	}
	
	/**
	 * Valuta la soglia su una lista di segnali utili.
	 * 
	 * @param signals	Lista di segnali
	 * @return	Percentuale di successo [0, 1].
	 * 	La soglia deve essere inferiore all'energia di un segnale
	 */
	public double evaluateOnSignals(List<? extends Signal> signals) {
		int count = 0;
		int hit = 0;
		for (Signal s : signals) {
			if (this.threshold < s.energy()) {
				hit++;
			}
			count++;
		}
		return (double) hit / count;
	}
	
	/**
	 * Valuta la soglia su rumori generati casualmente.
	 * 
	 * @param signal		Segnale di partenza per costruire i rumori.
	 * @param testsNumber	Numero di rumori da generare
	 * @param noiseLength	Lunghezza di un singolo rumore
	 * @return	Percentuale di successo [0, 1].
	 * 	La soglia deve essere superiore all'energia di un rumore
	 */
	public double evaluateOnNoises(Signal signal, int testsNumber, int noiseLength) {
		NoiseGenerator generator = new NoiseGenerator(signal);

		// Pair.first = stream length TODO
		Pair<Integer, Integer> result = generator.generateStream(noiseLength)
				.limit(testsNumber)
				.map(x -> new Pair<>(1, (this.threshold > x.energy()) ? 1 : 0))
				.reduce(new Pair<>(0, 0),
						(a, b) -> new Pair<>(a.first + b.first, a.second + b.second));
		
		return (double) result.second / result.first;
	}
}
