package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.stream.Stream;

/**
 * Rappresenta una collezione di numeri complessi.
 * 
 * Tramite questa interfaccia e' possibile elaborare i dati
 * presenti nella collezione stramite uno stream di complessi,
 * astraendo il tipo di implementazione che la collezione prevede.
 * 
 * @author emiliano
 *
 */
public interface ComplexCollection {
	
	Stream<Complex> stream();
}
