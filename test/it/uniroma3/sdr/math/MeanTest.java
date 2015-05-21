package it.uniroma3.sdr.math;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by emiliano on 21/05/15.
 */
public class MeanTest {

    private Double[] emptyArray;

    private Double[] zeroArray;

    private Double[] oneArray;

    private Stream<Double> emptyStream;

    private Stream<Double> zeroStream;

    private Stream<Double> oneStream;

    @Before
    public void setUp() {
        emptyArray = new Double[5];
        zeroArray = new Double[5];
        oneArray = new Double[5];

        for (int i = 0; i < 5; i++) {
            zeroArray[i] = 0.0;
            oneArray[i] = 1.0;
        }

        emptyStream = Stream.empty();
        zeroStream = Stream.of(0.0, 0.0, 0.0, 0.0, 0.0);
        oneStream = Stream.of(1.0, 1.0, 1.0, 1.0, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateEmptyArrayTest() {
        Mean.evaluate(emptyArray);
    }

    @Test
    public void evaluateZeroArrayTest() {
        assertEquals(0, Mean.evaluate(zeroArray), 0);
    }

    @Test
    public void evaluateOneArrayTest() {
        assertEquals(1, Mean.evaluate(oneArray), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateEmptyStreamTest() {
        Mean.evaluate(emptyStream);
    }

    @Test
    public void evaluateZeroStreamTest() {
        assertEquals(0, Mean.evaluate(zeroStream), 0);
    }

    @Test
    public void evaluateOneStreamTest() {
        assertEquals(1, Mean.evaluate(oneStream), 0);
    }
}