package it.uniroma3.sdr.signal;

import it.uniroma3.sdr.collection.complex.ComplexArray;
import it.uniroma3.sdr.math.complex.CartesianComplex;
import it.uniroma3.sdr.math.complex.Complex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emiliano on 21/05/15.
 */
public class SignalTest {

    class SignalMock extends Signal {}

    private Signal notInitializedSignal;

    private Signal emptySignal;

    private Signal oneSignal;

    @Before
    public void setUp() throws Exception {
        notInitializedSignal = new SignalMock();

        emptySignal = new SignalMock();
        emptySignal.initialize(new ComplexArray(new Complex[1]));

        Complex[] one = new Complex[1];
        one[0] = new CartesianComplex(1, 0);
        oneSignal = new SignalMock();
        oneSignal.initialize(new ComplexArray(one));
    }

    @Test(expected = SignalStateException.class)
    public void notInitializedStreamTest() {
        notInitializedSignal.stream();
    }

    @Test(expected = SignalStateException.class)
    public void notInitializedEnergyTest() {
        notInitializedSignal.energy();
    }

    @Test(expected = SignalStateException.class)
    public void emptySignalInitializeTest() {
       emptySignal.initialize(new ComplexArray(new Complex[1]));
    }

    @Test
    public void emptySignalStreamTest() {
        assertEquals(0, emptySignal.stream().count());
    }

    @Test(expected = SignalStateException.class)
    public void emptySignalEnergyTest() {
        emptySignal.energy();
    }

    @Test(expected = SignalStateException.class)
    public void oneSignalInitializeTest() {
        oneSignal.initialize(new ComplexArray(new Complex[1]));
    }

    @Test
    public void oneSignalStreamTest() {
        assertEquals(1, oneSignal.stream().count());
    }

    @Test
    public void oneSignalEnergyTest() {
        assertEquals(1, oneSignal.energy(), 0);
    }
}