package it.uniroma3.sdr.math;

import it.uniroma3.sdr.collection.Pair;

/**
 * Sistema di parsing per numeri reali
 *
 * Created by emiliano on 23/05/15.
 */
public class RealParser {

    /**
     *
     * @param input Stringa contenente il numero da interpretare
     * @return  double
     */
    public static double parse(String input) {
        return parse(input, 0).first;
    }

    /**
     * Data una stringa contenente una seria di numeri reali e un limite
     * massimo di numeri da interpretare, il metodo restituisce un array di
     * double
     *
     * @param input Stringa contenente i numeri da interpretare
     * @param expected  Limite massimo di numeri da interpretare
     * @return  double[]
     */
    public static double[] parseMany(String input, int expected) {
        double[] out = new double[expected];
        int offset = 0;

        for (int i = 0; i < expected; i++) {
            Pair<Double, Integer> result = parse(input, offset);
            out[i] = result.first;
            offset = result.second;
        }

        return out;
    }

    /**
     * Data una stringa ed un offset da cui iniziare ad eseguire il parsing,
     * la funzione restituisce una coppia formata dal primo numero reale che
     * incontra e dall'indice relativo all'ultima cifra del numero all'interno
     * della stringa
     *
     * @param input Stringa da interpretare
     * @param offset    Indice da cui inizare
     * @return  Coppia numero parsato - indice di fine
     */
    private static Pair<Double, Integer> parse(String input, int offset) {
        int length = input.length();
        int index = offset;
        double sign = 0.0;
        double out = 0.0;

        // parsing whitespaces
        whitespace:
        while (true) {
            if (index >= length) throw new NumberFormatException("Reached end of string while parsing");

            switch (input.charAt(index)) {
                // whitespaces
                case ' ':case '\t':case '\r':case '\n':
                    break;
                // go to the next while
                case '+':case '-':case '.':case ',':
                case '0':case '1':case '2':case '3':case '4':
                case '5':case '6':case '7':case '8':case '9':
                    break whitespace;
                // unexpected symbol
                default:
                    throw new NumberFormatException("Unexpected symbol '" + input.charAt(index) + "' while seeking number");
            }

            index++;
        }
        // parsing sign
        sign:
        while (true) {
            if (index >= length) throw new NumberFormatException("Reached end of string while parsing");

            switch (input.charAt(index)) {
                // found + sign
                case '+':
                    if (sign == 0.0) sign = 1.0;
                    else throw new NumberFormatException("Unexpected + sign");
                    break;
                // found - sign
                case '-':
                    if (sign == 0.0) sign = -1.0;
                    else throw new NumberFormatException("Unexpected - sign");
                    break;
                // found number
                // go to the next while
                case '.':case ',':
                case '0':case '1':case '2':case '3':case '4':
                case '5':case '6':case '7':case '8':case '9':
                    break sign;
                // unexpected symbol
                default:
                    throw new NumberFormatException("Unexpected symbol '" + input.charAt(index) + "' while parsing sign");
            }

            index++;
        }

        if (sign == 0.0) sign = 1.0;

        // parsing integer part
        integer:
        while (true) {
            if (index >= length) return new Pair<>(out * sign, index);

            switch (input.charAt(index)) {
                case '0': out *= 10.0; break;
                case '1': out *= 10.0; out += 1.0; break;
                case '2': out *= 10.0; out += 2.0; break;
                case '3': out *= 10.0; out += 3.0; break;
                case '4': out *= 10.0; out += 4.0; break;
                case '5': out *= 10.0; out += 5.0; break;
                case '6': out *= 10.0; out += 6.0; break;
                case '7': out *= 10.0; out += 7.0; break;
                case '8': out *= 10.0; out += 8.0; break;
                case '9': out *= 10.0; out += 9.0; break;
                case '.':case ',':
                    index++;
                    break integer;
                default:
                    return new Pair<>(out * sign, index);
            }

            index++;
        }

        double decimal = 0.1;
        // parsing fractional part
        fractional:
        while (true) {
            if (index >= length) return new Pair<>(out * sign, index);

            switch (input.charAt(index)) {
                case '0': break;
                case '1': out += 1.0 * decimal; break;
                case '2': out += 2.0 * decimal; break;
                case '3': out += 3.0 * decimal; break;
                case '4': out += 4.0 * decimal; break;
                case '5': out += 5.0 * decimal; break;
                case '6': out += 6.0 * decimal; break;
                case '7': out += 7.0 * decimal; break;
                case '8': out += 8.0 * decimal; break;
                case '9': out += 9.0 * decimal; break;
                case 'E':case'e':
                    index++;
                    break fractional;
                default:
                    return new Pair<>(out * sign, index);
            }

            decimal /= 10.0;
            index++;
        }

        //parsing exponential sign
        double exponentSign = 1.0;
        switch (input.charAt(index)) {
            case '0':case '1':case '2':case '3':case '4':
            case '5':case '6':case '7':case '8':case '9':
                break;
            case '+':
                exponentSign = 1.0;
                index++;
                break;
            case '-':
                exponentSign = -1.0;
                index++;
                break;
            default:
                throw new NumberFormatException("Unexpected symbol '" + input.charAt(index) + "' while parsing exponent");
        }

        // parsing esponential value
        int exponent = 0;
        while (true) {
            if (index >= length) return new Pair<>(out * sign * Math.pow(10, exponent * exponentSign), index);

            switch (input.charAt(index)) {
                case '0': exponent *= 10.0; break;
                case '1': exponent *= 10.0; exponent += 1.0; break;
                case '2': exponent *= 10.0; exponent += 2.0; break;
                case '3': exponent *= 10.0; exponent += 3.0; break;
                case '4': exponent *= 10.0; exponent += 4.0; break;
                case '5': exponent *= 10.0; exponent += 5.0; break;
                case '6': exponent *= 10.0; exponent += 6.0; break;
                case '7': exponent *= 10.0; exponent += 7.0; break;
                case '8': exponent *= 10.0; exponent += 8.0; break;
                case '9': exponent *= 10.0; exponent += 9.0; break;
                default:
                    return new Pair<>(out * sign * Math.pow(10, exponent * exponentSign), index);
            }

            index++;
        }
    }
}
