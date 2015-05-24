package it.uniroma3.sdr.signal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

import it.uniroma3.sdr.math.RealParser;
import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.Complex;

/**
 * Lettore di segnali da file.
 * Il file in input deve essere formattato nel seguente modo:
 * - Un campione su ogni linea
 * - <Re>\t<Im>
 * 
 * @author emiliano
 *
 */
public class SignalReader {

	private String filePath;
	
	private String fileName;
	
	/**
	 * @param filePath	Percorso del file
	 * @param fileName	Nome del file
	 */
	public SignalReader(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	/**
	 * @return	Il file di input sotto forma di stream di complessi
	 */
	private Stream<Complex> stream() {
		try {
			return Files.lines(Paths.get(this.filePath, this.fileName))
					.map(x -> {
						double[] split = RealParser.parseMany(x, 2);
						return new CartesianComplex(split[0], split[1]);
						//String[] split = x.split("\t");	// don't use regexp!
						//return new CartesianComplex(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
					});
		} catch (NumberFormatException e) {
			throw new SignalReaderException("Unable to parse numbers in " + this.filePath + this.fileName, e);
		} catch (IOException e) {
			throw new SignalReaderException("Error occurred while reading " + this.filePath + this.fileName, e);
		}
	}
	
	/**
	 * @return	Segnale generico letto dal file di input
	 */
	public Signal readSignal() {
		return new GenericSignal(this.stream());
	}
	
	/**
	 * @return	Segnale durevole letto dal file di input
	 */
	public DurableSignal readEagerDurableSignal() {
		return new DurableSignal(this.stream().toArray(Complex[]::new));
	}

	public DurableSignal readLazyDurableSignal() {
		return new DurableSignal(this.stream(), 10_000);
	}
	
	/**
	 * Restituisce una lista di segnali di lunghezza determinata
	 * letti dal file di input
	 * 
	 * @param signalLength	Lunghezza dei segnali.
	 * 	L'ultimo segnale viene costruito con i campioni rimanenti,
	 * 	per cui e' probabile che abbia lunghezza minore
	 * @return	Lista di segnali letti dal file di input
	 */
	public Collection<Signal> readSignals(int signalLength) {
		SignalUtil util = new SignalUtil(this.stream());
		return util.split(signalLength);
	}
	
	/**
	 * Eccezione lanciata in caso di problemi derivanti dalla lettura da file.
	 * Un eccezione di tipo SignalReaderException puo' essere dovuta
	 * ad un problema di I/O o ad un problema di formattazione del file.
	 * 
	 * @author emiliano
	 *
	 */
	private class SignalReaderException extends RuntimeException {
		
		private static final long serialVersionUID = 1775335840695452813L;
		
		public SignalReaderException(String msg, Throwable cause) {
			super(msg, cause);
		}
	}
}
