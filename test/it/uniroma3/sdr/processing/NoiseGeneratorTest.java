package it.uniroma3.sdr.processing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emiliano on 21/05/15.
 */
public class NoiseGeneratorTest {

    // Generatore di rumore con snr = 1
    private NoiseGenerator noiseGenerator = new NoiseGenerator(2);

    @Test
    public void snrTest() {
        assertEquals(1, noiseGenerator.snr(), 0);
    }

    @Test
    public void noiseLengthTest() {
        assertEquals(10, noiseGenerator.generate(10).stream().count());
    }
}