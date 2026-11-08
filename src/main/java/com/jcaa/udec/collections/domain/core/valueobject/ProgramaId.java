package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object que representa el identificador único de un Programa.
 */
public final class ProgramaId {

    private final String valor;

    public ProgramaId(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException("El id de un Programa no puede estar vacío.");
        }
        this.valor = valor.trim();
    }

    public static ProgramaId generar() {
        return new ProgramaId(UUID.randomUUID().toString());
    }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgramaId)) return false;
        ProgramaId that = (ProgramaId) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
