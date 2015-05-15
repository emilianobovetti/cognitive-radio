package it.uniroma3.sdr.processing;

import it.uniroma3.sdr.collection.Pair;
import it.uniroma3.sdr.signal.Signal;

import java.util.List;
import java.util.stream.Stream;

public class ThresholdTester {

	private double threshold;
	
	public ThresholdTester(double threshold) {
		this.threshold = threshold;
	}
	
	public double evaluateSignals(Stream<Signal> signals) {
		Pair<Integer, Integer> result = signals.map(s -> {
			if (this.threshold < s.energy()) {
				return new Pair<Integer, Integer>(1, 1);
			} else  {
				return new Pair<Integer, Integer>(1, 0);
			}
		}).reduce(new Pair<Integer, Integer>(0, 0),
				(a, b) -> new Pair<Integer, Integer>(a.first + b.first, a.second + b.second)
		);
		
		return result.second / result.first;
	}
	
	public double evaluateSignals(List<Signal> signals) {
		int count = 0;
		int hit = 0;
		for (Signal s : signals) {
			if (this.threshold < s.energy()) {
				hit++;
			}
			count++;
		}
		return hit / count;
	}
}
