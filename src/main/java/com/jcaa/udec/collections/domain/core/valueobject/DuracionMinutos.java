package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;

/**
 * Value Object que representa la duración de una emisión, en minutos.
 * Protege la invariante de que la duración debe ser mayor que cero.
 */
public final class DuracionMinutos {

    private final int minutos;

    public DuracionMinutos(int minutos) {
        if (minutos <= 0) {
            throw new DatoInvalidoException("La duración de la emisión debe ser mayor que cero minutos.");
        }
        this.minutos = minutos;
    }

    public int minutos() {
        return minutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DuracionMinutos)) return false;
        DuracionMinutos that = (DuracionMinutos) o;
        return minutos == that.minutos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minutos);
    }

    @Override
    public String toString() {
        return minutos + " min";
    }
}
