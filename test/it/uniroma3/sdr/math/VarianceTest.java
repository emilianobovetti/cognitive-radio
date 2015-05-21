package it.uniroma3.sdr.math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emiliano on 21/05/15.
 */
public class VarianceTest {

    private Double[] empty;

    private Double[] zero;

    private Double[] one;

    @Before
    public void setUp() {
        empty = new Double[10];
        zero = new Double[10];
        one = new Double[10];

        for (int i = 0; i < 10; i++) {
            zero[i] = 0.0;
            one[i] = 1.0;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateEmptyTest() {
        Variance.evaluate(empty);
    }

    @Test
    public void evaluateZeroTest() {
        assertEquals(0, Variance.evaluate(zero), 0);
    }

    @Test
    public void evaluateOneTest() {
        assertEquals(0, Variance.evaluate(one), 0);
    }
}