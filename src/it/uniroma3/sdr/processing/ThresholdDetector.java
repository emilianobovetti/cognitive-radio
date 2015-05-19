package it.uniroma3.sdr.processing;

import it.uniroma3.sdr.math.ErfInv;
import it.uniroma3.sdr.math.Mean;
import it.uniroma3.sdr.math.Variance;
import it.uniroma3.sdr.signal.Signal;

/**
 * Classe per il calcolo dell'energia di soglia.
 * La soglia permette di distinguere il segnale dal rumore
 * tramite energy detection.
 * 
 * @author emiliano
 *
 */
public class ThresholdDetector {

	private int testsNumber;
	
	private int noiseLength;
	
	private double probabilityFalseAlarm;
	
	/**
	 * @param testsNumber	Numero di rumori da generare per il calcolo
	 * 	della soglia
	 * @param noiseLength	Lunghezza dei rumori generati
	 * @param probabilityFalseAlarm	Probabilita' di falso allarme
	 */
	public ThresholdDetector(int testsNumber, int noiseLength, double probabilityFalseAlarm) {
		this.testsNumber = testsNumber;
		this.noiseLength = noiseLength;
		this.probabilityFalseAlarm = probabilityFalseAlarm;
	}
	
	/**
	 * @param signal	Segnale di partenza. In base
	 * 	all'energia di questo segnale verranno generati i rumori
	 * @return	Stima dell'energia di soglia
	 */
	public double evaluate(Signal signal) {
		NoiseGenerator generator = new NoiseGenerator(signal);
		
		Double[] noisesEnergy = generator.generateStream(this.noiseLength)
				.limit(this.testsNumber).map(x -> x.energy()).toArray(Double[]::new);
		
		double mean = Mean.evaluate(noisesEnergy);
		return mean + Math.sqrt(2.0 * Variance.evaluate(noisesEnergy, mean)) *
					ErfInv.evaluate(1.0 - 2.0 * this.probabilityFalseAlarm);
	}
}
