package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;

/**
 * Value Object que representa un porcentaje de aceptación entre 0 y 100.
 */
public final class PorcentajeAceptacion {

    private final double valor;

    public PorcentajeAceptacion(double valor) {
        if (Double.isNaN(valor) || valor < 0 || valor > 100) {
            throw new DatoInvalidoException("El porcentaje de aceptación debe estar entre 0 y 100.");
        }
        this.valor = valor;
    }

    public double valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PorcentajeAceptacion)) return false;
        PorcentajeAceptacion that = (PorcentajeAceptacion) o;
        return Double.compare(that.valor, valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor + " %";
    }
}
