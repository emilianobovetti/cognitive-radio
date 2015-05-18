package it.uniroma3.sdr;
import it.uniroma3.sdr.processing.EnergyThreshold;
import it.uniroma3.sdr.processing.ThresholdTester;
import it.uniroma3.sdr.signal.DurableSignal;
import it.uniroma3.sdr.signal.Signal;
import it.uniroma3.sdr.signal.GenericSignal;
import it.uniroma3.sdr.signal.SignalReader;
import it.uniroma3.sdr.signal.SignalUtil;
import it.uniroma3.sdr.system.Settings;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) throws IOException {
		//Settings.readSettings();
		//System.out.println(System.getProperty(""));
		
		String filePath = "/home/emiliano/Scrivania/Sequenze_SDR_2015/Sequenza_1/";
		String fileName = "output_1.dat";
		
		long start = System.currentTimeMillis();
		SignalReader reader = new SignalReader(filePath, fileName);
		
		
		DurableSignal signal = reader.readDurableSignal();
		EnergyThreshold et = new EnergyThreshold(1000, 1000, 0.001);
		double threshold = et.evaluate(signal);
		System.out.println("Signal energy = " + signal.energy());
		System.out.println("Threshold = " + threshold);
			// 1.3804277937152254 with apache commons
			// 1.3580800950367273 without
		
		ThresholdTester tt = new ThresholdTester(threshold);
		
		SignalUtil util = new SignalUtil(signal.stream());
		List<DurableSignal> signals = util.split(1000);
		double evalOnSignals = tt.evaluateOnSignals(signals);
		System.out.println("Evaluation on signals = " + evalOnSignals * 100 + "%");
		
		double evalOnNoises = tt.evaluateOnNoises(signal, 1000, 1000);
		System.out.println("Evaluation on noises = " + evalOnNoises * 100 + "%");
		
		long end = System.currentTimeMillis();
		double elapsed = (end - start) / 1000.0;
		System.out.println("Elapsed = " + elapsed);
		
	}
}
