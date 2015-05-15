package it.uniroma3.sdr.signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import it.uniroma3.sdr.math.CartesianComplex;

public class SignalReader {

	@SuppressWarnings("resource")
	public static ActualSignal readFromFile(String filePath, String fileName) throws IOException {
		Stream<String> lines = Files.lines(Paths.get(filePath, fileName));
		return new ActualSignal(1000000, lines.map((x) -> {
			String[] split = x.split("\\t");
			return new CartesianComplex(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
		}));
	}
}
