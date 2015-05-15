package it.uniroma3.sdr.signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import it.uniroma3.sdr.math.CartesianComplex;

public class SignalReader {

	private static Function<String, CartesianComplex> lineParser = (x) -> {
		String[] split = x.split("\\t");
		return new CartesianComplex(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
	};
	
	private Stream<String> fileStream;
	
	public SignalReader(String filePath, String fileName) throws IOException {
		this.fileStream = Files.lines(Paths.get(filePath, fileName));
	}
	
	public ActualSignal readSignal() {
		return new ActualSignal(this.fileStream.map(lineParser));
	}
	
	public static List<ActualSignal> readMultipleSignals(int signalLength) {
		return null;
//		int index = 0;
//		List<ActualSignal> signals = new LinkedList<ActualSignal>();
//		CartesianComplex[] samples = new CartesianComplex[signalLength];
//		Iterator<CartesianComplex> iterator = getLinesStream(filePath, fileName).map(lineParser).iterator();
//		while (index < signalLength && iterator.hasNext()) {
//			samples[index] = iterator.next();
//			index++;
//			
//			if (index == signalLength) {
//				signals.add(new ActualSignal(signalLength, Arrays.stream(samples)));
//				index = 0;
//			}
//		}
//		
//		if (index > 0) {
//			//signals.add()
//		}
//		
//		return signals;
	}
}
