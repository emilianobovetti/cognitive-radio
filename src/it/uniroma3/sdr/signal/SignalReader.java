package it.uniroma3.sdr.signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import it.uniroma3.sdr.math.CartesianComplex;
import it.uniroma3.sdr.math.Complex;

public class SignalReader {

	private static Function<String, CartesianComplex> lineParser = (x) -> {
		String[] split = x.split("\\t");
		return new CartesianComplex(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
	};
	
	public static Stream<String> getLinesStream(String filePath, String fileName) throws IOException {
		return Files.lines(Paths.get(filePath, fileName));
	}
	
	public static ActualSignal readSignalFromFile(String filePath, String fileName, int maxLength) throws IOException {
		return new ActualSignal(maxLength, getLinesStream(filePath, fileName).map(lineParser));
	}
	
	public static List<ActualSignal> readSignalsFromFile(String filePath, String fileName, int signalLength) throws IOException {
		int index = 0;
		List<ActualSignal> signals = new LinkedList<ActualSignal>();
		CartesianComplex[] samples = new CartesianComplex[signalLength];
		Iterator<CartesianComplex> iterator = getLinesStream(filePath, fileName).map(lineParser).iterator();
		while (index < signalLength && iterator.hasNext()) {
			samples[index] = iterator.next();
			index++;
			
			if (index == signalLength) {
				signals.add(new ActualSignal(signalLength, Arrays.stream(samples)));
				index = 0;
			}
		}
		
		return signals;
	}
}
