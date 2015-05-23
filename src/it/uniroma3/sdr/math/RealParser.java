package it.uniroma3.sdr.math;

/**
 * Sistema di parsing per numeri reali.
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
        double[] result = new double[1];
        parse(input, result);
        return result[0];
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
        double[] result = new double[expected];
        parse(input, result);
        return result;
    }

    /**
     * Data una stringa su cui eseguire il parsing ed un array di double
     * nel quale memorizzare i risultati, la funzione esegue il parsing
     * di tutti i numeri reali della stringa fino a riempire l'array
     *
     * @param input Stringa da interpretare
     * @param array Array dove salvare i dati
     */
    private static void parse(String input, double[] array) {
        int length = input.length();
        int index = 0;

        arrayFill:
        for (int outIndex = 0; outIndex < array.length; outIndex++) {
            double out = 0.0;

            // parsing whitespaces
            whitespace:
            while (true) {
                if (index >= length) throw new NumberFormatException("Reached end of string while parsing");

                switch (input.charAt(index)) {
                    // go to the next while
                    case '+':case '-':case '.':case ',':
                    case '0':case '1':case '2':case '3':case '4':
                    case '5':case '6':case '7':case '8':case '9':
                        break whitespace;
                    // whitespaces
                    case ' ':case '\t':case '\r':case '\n':
                        break;
                    // unexpected symbol
                    default:
                        throw new NumberFormatException("Unexpected symbol '" + input.charAt(index) + "' while seeking number");
                }

                index++;
            }

            // parsing sign
            double sign = 1.0;
            switch (input.charAt(index)) {
                // found + sign
                case '+':
                    index++;
                    break;
                // found - sign
                case '-':
                    index++;
                    sign = -1.0;
                    break;
                // found number
                // go to the next while
                case '.':case ',':
                case '0':case '1':case '2':case '3':case '4':
                case '5':case '6':case '7':case '8':case '9':
                    break;
                // unexpected symbol
                default:
                    throw new NumberFormatException("Unexpected symbol '" + input.charAt(index) + "' while parsing sign");
            }

            // parsing integer part
            integer:
            while (true) {
                if (index >= length) {
                    array[outIndex] = out * sign;
                    continue arrayFill;
                }

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
                        array[outIndex] = out * sign;
                        continue arrayFill;
                }

                index++;
            }

            // parsing fractional part
            double decimal = 0.1;
            fractional:
            while (true) {
                if (index >= length) {
                    array[outIndex] = out * sign;
                    continue arrayFill;
                }

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
                    case 'E':case 'e':
                        index++;
                        break fractional;
                    default:
                        array[outIndex] = out * sign;
                        continue arrayFill;
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
                    index++;
                    break;
                case '-':
                    exponentSign = -1.0;
                    index++;
                    break;
                default:
                    throw new NumberFormatException("Unexpected symbol '" + input.charAt(index) + "' while parsing exponent");
            }

            // parsing exponential value
            int exponent = 0;
            while (true) {
                if (index >= length) {
                    array[outIndex] = out * sign * Math.pow(10, exponent * exponentSign);
                    continue arrayFill;
                }

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
                        array[outIndex] = out * sign * Math.pow(10, exponent * exponentSign);
                        continue arrayFill;
                }

                index++;
            }
        }
    }
}
