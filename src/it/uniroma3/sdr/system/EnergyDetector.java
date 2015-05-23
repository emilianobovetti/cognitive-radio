package it.uniroma3.sdr.system;

import java.util.List;

import it.uniroma3.sdr.processing.ThresholdDetector;
import it.uniroma3.sdr.processing.ThresholdTester;
import it.uniroma3.sdr.signal.DurableSignal;
import it.uniroma3.sdr.signal.Signal;
import it.uniroma3.sdr.signal.SignalReader;
import it.uniroma3.sdr.signal.SignalUtil;

/**
 * Singleton che rappresenta il sistema.
 * 
 * Legge il file di configurazione inizializzando le proprieta' di sistema
 * ed esegue l'elaborazione dei segnali.
 * 
 * @author emiliano
 *
 */
public class EnergyDetector {
	
	private static EnergyDetector instance;
	
	private String signalsDirectory = System.getProperty("signals.root.directory");
	
	private int noiseTestNumber = Settings.getIntegerProperty("noise.test.number");
	
	private int noiseSampleLength = Settings.getIntegerProperty("noise.sample.length");
	
	private double pfa = Settings.getDoubleProperty("probability.false.alarm");
	
	private Signal currentSignal;

	private double currentThreshold;
	
	private double detectionPercentage;
	
	private EnergyDetector() {}
	
	public static EnergyDetector getInstance() {
		if (instance == null) {
			Settings.readSettings();
			instance = new EnergyDetector();
		}
		return instance;
	}
	
	/**
	 * Esegue i test tutti i segnali indicati nel file di configurazione
	 */
	public void run() {
		String[] signals = Settings.getArrayProperty("signal.files.name");
		
		for (String s : signals) {
			this.log("Processing signal '" + s + "'");
			
			this.findThreshold(s);
			
			this.log("Signal energy = " + String.format("%.12f", this.currentSignal.energy()));
			this.log("Signal snr in db = " + String.format("%.12f", this.currentSignal.snrDb()));
			this.log("Threshold = " + String.format("%.12f", this.currentThreshold));
			
			this.testThreshold();
			
			this.log("Detection Percentage = " + String.format("%.3f", this.detectionPercentage));
			this.log("");
		}
	}

	/**
	 * @param s	Stringa da loggare
	 */
	public void log(String s) {
		System.out.println(s);
	}

	/**
	 * Dato il nome di un file contenente i campioni di un segnale, calcola
	 * l'energia di soglia
	 * 
	 * @param signalFileName	Nome del file
	 */
	public void findThreshold(String signalFileName) {
		SignalReader reader = new SignalReader(this.signalsDirectory, signalFileName);
		this.currentSignal = reader.readLazyDurableSignal();	// i dati del segnale verrano riutilizzati
																// nel metodo SignalUtil.split()

		ThresholdDetector detector = new ThresholdDetector(this.noiseTestNumber, this.noiseSampleLength, this.pfa);
		this.currentThreshold = detector.evaluate(this.currentSignal);
	}
	
	/**
	 * Testa l'energia di soglia ricavata sul segnale e calcola la percentuale di successo
	 */
	public void testThreshold() {
		ThresholdTester tester = new ThresholdTester(this.currentThreshold);
		
		SignalUtil util = new SignalUtil(this.currentSignal.stream());
		List<DurableSignal> signals = util.split(1000);
		
		this.detectionPercentage = tester.evaluateOnSignals(signals);
	}
}
