package it.uniroma3.sdr.math;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emiliano on 21/05/15.
 */
public class ErfInvTest {

    @Test
    public void evaluateTest() throws Exception {
        assertEquals(0.2253, ErfInv.evaluate(0.25), 0.00001);
    }
}