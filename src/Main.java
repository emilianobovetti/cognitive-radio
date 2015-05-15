import it.uniroma3.sdr.processing.EnergyThreshold;
import it.uniroma3.sdr.processing.ThresholdTester;
import it.uniroma3.sdr.signal.Signal;
import it.uniroma3.sdr.signal.ActualSignal;
import it.uniroma3.sdr.signal.SignalReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) throws IOException {
		String filePath = "/home/emiliano/Scrivania/Sequenze_SDR_2015/Sequenza_1/";
		String fileName = "output_1.dat";
		
		long start = System.currentTimeMillis();
		SignalReader reader = new SignalReader(filePath, fileName);
		
		
		Signal s = reader.readSignal();
		EnergyThreshold et = new EnergyThreshold(1000, 1000, 0.001);
		System.out.println(et.evaluate(s)); // 1.3804277937152254
		
		
		/*
		Stream<Signal> signals = reader.readSignals(1000);
		ThresholdTester tt = new ThresholdTester(1.3804277937152254);
		System.out.println(tt.evaluateSignals(signals));
		*/
		
		long end = System.currentTimeMillis();
		double elapsed = (end - start) / 1000.0;
		System.out.println("Elapsed = " + elapsed);
	}
}
