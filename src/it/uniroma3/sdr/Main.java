package it.uniroma3.sdr;

import it.uniroma3.sdr.math.Real;
import it.uniroma3.sdr.math.RealParser;
import it.uniroma3.sdr.system.EnergyDetector;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		benchmark(() -> EnergyDetector.getInstance().run());
	}

	public static void benchmark(Runnable r) {
		long start = System.nanoTime();

		r.run();

		long end = System.nanoTime();
		double elapsed = (end - start) / 1000000000.0;

		// log
		EnergyDetector detector = EnergyDetector.getInstance();
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		detector.log(stackTraceElements[2] + " elapsed in " + String.format("%.12f", elapsed) + " seconds");
	}
}
