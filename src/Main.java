import it.uniroma3.sdr.processing.ThresholdEnergy;
import it.uniroma3.sdr.signal.Signal;
import it.uniroma3.sdr.signal.ActualSignal;
import it.uniroma3.sdr.signal.SignalReader;

import java.io.IOException;
import java.util.List;


@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) throws IOException {
		String filePath = "/home/emiliano/Scrivania/Sequenze_SDR_2015/Sequenza_1/";
		String fileName = "output_1.dat";
		
		long start = System.currentTimeMillis();
		SignalReader reader = new SignalReader(filePath, fileName);
		
//		Signal s = reader.readSignal();
//		ThresholdEnergy te = new ThresholdEnergy(1000, 1000, 0.001);
//		System.out.println(te.estimate(s)); // 1.3804277937152254
		
		List<Signal> signals = reader.readSignals(1000);
		for (Signal s : signals) {
			s.print();
		}
		
		long end = System.currentTimeMillis();
		double elapsed = (end - start) / 1000.0;
		System.out.println(elapsed);
	}
}
