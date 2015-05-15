package it.uniroma3.sdr.signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import it.uniroma3.sdr.math.CartesianComplex;
import it.uniroma3.sdr.math.Complex;

public class SignalReader {
	
	private Stream<Complex> complexStream;
	
	public SignalReader(String filePath, String fileName) throws IOException {
		this.complexStream = Files.lines(Paths.get(filePath, fileName)).map((x) -> {
			String[] split = x.split("\\t");
			return new CartesianComplex(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
		});
	}
	
	public Signal readSignal() {
		return new ActualSignal(this.complexStream);
	}
	
	public List<Signal> readSignals(int signalLength) {
		Iterator<Complex> iterator = this.complexStream.iterator();
		List<Signal> signals = new LinkedList<>();
		Complex[] samples = new Complex[signalLength];
		int index = 0;
		while (iterator.hasNext()) {
			if (index >= signalLength) {
				signals.add(new ActualSignal(samples));
				samples = new Complex[signalLength];
				index = 0;
			}
			samples[index] = iterator.next();
		}
		return signals;
	}
}
