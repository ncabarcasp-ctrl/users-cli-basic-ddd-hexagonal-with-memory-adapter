package com.jcaa.udec.collections.domain.core.valueobject;

import java.util.Objects;
 
/**
 * Value Object que representa la frecuencia de transmisión de una Radio
 * (por ejemplo, 98.1 para FM o 1050 para AM).
 * Protege la invariante de que la frecuencia debe ser un número positivo.
 */
public final class Frecuencia {
 
    private final double valor;
 
    public Frecuencia(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("La frecuencia debe ser un valor mayor que cero.");
        }
        this.valor = valor;
    }
 
    public double valor() {
        return valor;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frecuencia)) return false;
        Frecuencia that = (Frecuencia) o;
        return Double.compare(that.valor, valor) == 0;
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
 
    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
