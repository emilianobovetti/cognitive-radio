package it.uniroma3.sdr;

import it.uniroma3.sdr.system.EnergyDetector;

public class Main {
	
	public static void main(String[] args) {
		benchmark(() -> EnergyDetector.getInstance().run());
	}

	public static void benchmark(Runnable r) {
		long start = System.nanoTime();

		r.run();

		long end = System.nanoTime();
		double elapsed = (end - start) / 1000000000.0;
		EnergyDetector.getInstance().log("Elapsed = " + elapsed + " seconds");
	}
}
