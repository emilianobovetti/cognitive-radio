package it.uniroma3.sdr.collection.complex;

import it.uniroma3.sdr.math.complex.Complex;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Implementa una collezione di complessi generica.
 *
 * Created by emiliano on 24/05/15.
 */
public class GenericComplexCollection implements ComplexCollection {

    private Collection<Complex> collection;

    public GenericComplexCollection(Collection<Complex> collection) {
        this.collection = collection;
    }

    @Override
    public Stream<Complex> stream() {
        return this.collection.stream();
    }
}
