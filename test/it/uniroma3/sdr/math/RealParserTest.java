package it.uniroma3.sdr.math;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Real Parser test case
 *
 * Created by emiliano on 23/05/15.
 */
public class RealParserTest {

    @Test
    public void parseZeroTest() {
        assertEquals(0, RealParser.parse("0"), 0);
        assertEquals(0, RealParser.parse(" 0 "), 0);
    }

    @Test
    public void parseOneTest() {
        assertEquals(1, RealParser.parse("1"), 0);
        assertEquals(1, RealParser.parse(" 1 "), 0);
    }

    @Test
    public void parseMinusZeroTest() {
        assertEquals(0, RealParser.parse("-0'"), 0);
        assertEquals(0, RealParser.parse(" -0 "), 0);
    }

    @Test
    public void parseMinusOneTest() {
        assertEquals(-1, RealParser.parse("-1"), 0);
        assertEquals(-1, RealParser.parse(" -1 "), 0);
    }

    @Test
    public void parseTenTest() {
        assertEquals(10, RealParser.parse("10"), 0);
        assertEquals(10, RealParser.parse(" 10 "), 0);
    }

    @Test
    public void parseMinusTenTest() {
        assertEquals(-10, RealParser.parse("-10"), 0);
        assertEquals(-10, RealParser.parse(" -10 "), 0);
    }

    @Test
    public void parseZeroPointOneTest() {
        assertEquals(0.1, RealParser.parse("0.1"), 0);
        assertEquals(0.1, RealParser.parse("0,1"), 0);
        assertEquals(0.1, RealParser.parse(" 0.1 "), 0);
        assertEquals(0.1, RealParser.parse(" 0,1 "), 0);
    }

    @Test
    public void parseMinusZeroPointOneTest() {
        assertEquals(-0.1, RealParser.parse("-0.1"), 0);
        assertEquals(-0.1, RealParser.parse("-0,1"), 0);
        assertEquals(-0.1, RealParser.parse(" -0.1 "), 0);
        assertEquals(-0.1, RealParser.parse(" -0,1 "), 0);
    }

    @Test
    public void parseOneHundredTest() {
        assertEquals(100, RealParser.parse("100"), 0);
        assertEquals(100, RealParser.parse(" 100 "), 0);
    }

    @Test
    public void parseZeroPointZeroOneTest() {
        assertEquals(0.01, RealParser.parse("0.01"), 0);
        assertEquals(0.01, RealParser.parse("0,01"), 0);
        assertEquals(0.01, RealParser.parse(" 0.01 "), 0);
        assertEquals(0.01, RealParser.parse(" 0,01 "), 0);
    }

    @Test
    public void parseMinusZeroPointZeroOneTest() {
        assertEquals(-0.01, RealParser.parse("-0.01"), 0);
        assertEquals(-0.01, RealParser.parse("-0,01"), 0);
        assertEquals(-0.01, RealParser.parse(" -0.01 "), 0);
        assertEquals(-0.01, RealParser.parse(" -0,01 "), 0);
    }

    @Test
    public void parsePointOneTest() {
        assertEquals(0.1, RealParser.parse(".1"), 0);
        assertEquals(0.1, RealParser.parse(" .1 "), 0);
        assertEquals(0.1, RealParser.parse(",1"), 0);
        assertEquals(0.1, RealParser.parse(" ,1 "), 0);
    }

    @Test
    public void parseExponentTest() {
        assertEquals(Double.parseDouble("6.6813e-005"), RealParser.parse("6.6813e-005"), 1.0e-15);
        assertEquals(Double.parseDouble("-6.1199e-005"), RealParser.parse("-6.1199e-005"), 0);
        assertEquals(Double.parseDouble("9.6188e-005"), RealParser.parse("9.6188e-005"), 0);
        assertEquals(Double.parseDouble("-6.4566e-005"), RealParser.parse("-6.4566e-005"), 1.0e-15);
    }
}