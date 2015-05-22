package it.uniroma3.sdr.processing;

import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.signal.GenericSignal;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by emiliano on 21/05/15.
 */
public class NoiseGeneratorTest {

    // Generatore di rumore con snr = 1
    private NoiseGenerator noiseGenerator = new NoiseGenerator(new GenericSignal(Stream.of(new CartesianComplex(1, 0))));

    @Test
    public void noiseLengthTest() {
        assertEquals(10, noiseGenerator.generate(10).stream().count());
    }
}