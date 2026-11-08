package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object que representa el identificador único de una Compañía Productora.
 */
public final class ProductoraId {

    private final String valor;

    public ProductoraId(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException("El id de la compañía productora no puede estar vacío.");
        }
        this.valor = valor.trim();
    }

    public static ProductoraId generar() {
        return new ProductoraId(UUID.randomUUID().toString());
    }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoraId)) return false;
        ProductoraId that = (ProductoraId) o;
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
