package it.uniroma3.sdr;

import it.uniroma3.sdr.system.EnergyDetector;

public class Main {
	
	public static void main(String[] args) {
		EnergyDetector energyDetector = EnergyDetector.getInstance();
		
		long start = System.currentTimeMillis();
		
		energyDetector.run();
		
		long end = System.currentTimeMillis();
		double elapsed = (end - start) / 1000.0;
		System.out.println("Elapsed = " + elapsed);
	}
}
