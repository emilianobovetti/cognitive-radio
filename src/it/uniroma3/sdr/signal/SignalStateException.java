package it.uniroma3.sdr.signal;

/**
 * Eccezione relativa ad un cattivo stato di un segnale.
 *
 * Created by emiliano on 21/05/15.
 */
public class SignalStateException extends IllegalStateException {

    public SignalStateException(String msg) {
        super(msg);
    }
}
