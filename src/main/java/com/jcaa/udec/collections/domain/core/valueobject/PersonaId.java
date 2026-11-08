package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object que representa el identificador único de una Persona (conductor, operador o productor).
 */
public final class PersonaId {

    private final String valor;

    public PersonaId(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException("El id de una Persona (conductor, operador o productor) no puede estar vacío.");
        }
        this.valor = valor.trim();
    }

    public static PersonaId generar() {
        return new PersonaId(UUID.randomUUID().toString());
    }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaId)) return false;
        PersonaId that = (PersonaId) o;
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
