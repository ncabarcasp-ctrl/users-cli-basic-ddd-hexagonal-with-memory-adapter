package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;

/**
 * Value Object que representa el nombre de un Programa.
 * Protege la invariante de que el nombre no puede estar vacío ni superar
 * una longitud razonable.
 */
public final class NombrePrograma {

    private static final int LONGITUD_MAXIMA = 100;

    private final String valor;

    public NombrePrograma(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException("El nombre del programa no puede estar vacío.");
        }
        if (valor.trim().length() > LONGITUD_MAXIMA) {
            throw new DatoInvalidoException(
                    "El nombre del programa no puede superar " + LONGITUD_MAXIMA + " caracteres.");
        }
        this.valor = valor.trim();
    }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NombrePrograma)) return false;
        NombrePrograma that = (NombrePrograma) o;
        return valor.equalsIgnoreCase(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor.toLowerCase());
    }

    @Override
    public String toString() {
        return valor;
    }
}
