package it.uniroma3.sdr.signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.Complex;

public class SignalReader {
	
	private String filePath;
	
	private String fileName;
	
	public SignalReader(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	private Stream<Complex> stream() {
		try {
			return Files.lines(Paths.get(this.filePath, this.fileName))
					.sequential()
					.map((x) -> {
						String[] split = x.split("\\t");
						return new CartesianComplex(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
					});
		} catch (NumberFormatException e) {
			// the string does not have the appropriate format // TODO correct full path
			throw new SignalReaderExcetpion("Unable to parse numbers in " + this.filePath + this.fileName, e);
		} catch (IOException e) {
			// failed or interrupted I/O operations // TODO correct full path
			throw new SignalReaderExcetpion("Error occurred while reading " + this.filePath + this.fileName, e);
		}
	}
	
	public Signal readSignal() {
		return new GenericSignal(this.stream());
	}
	
	public DurableSignal readDurableSignal() {
		return new DurableSignal(this.stream());
	}
	
	public List<DurableSignal> readSignals(int signalLength) {
		SignalUtil util = new SignalUtil(this.stream());
		return util.split(signalLength);
	}
	
	private class SignalReaderExcetpion extends RuntimeException {
		
		private static final long serialVersionUID = 1775335840695452813L;
		
		public SignalReaderExcetpion(String msg, Throwable cause) {
			super(msg, cause);
		}
	}
}
