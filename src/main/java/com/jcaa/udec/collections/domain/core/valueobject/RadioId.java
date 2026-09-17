 package com.jcaa.udec.collections.domain.core.valueobject;
 
import com.jcaa.udec.collections.domain.core.exception.RadioDatoInvalidoException;
 
import java.util.Objects;
import java.util.UUID;
 
/**
 * Value Object que representa el identificador único de una Radio.
 * Protege la invariante de que un id nunca puede ser nulo ni estar vacío.
 */
public final class RadioId {
 
    private final String valor;
 
    public RadioId(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new RadioDatoInvalidoException("El id de la radio no puede estar vacío.");
        }
        this.valor = valor.trim();
    }
 
    public static RadioId generar() {
        return new RadioId(UUID.randomUUID().toString());
    }
 
    public String valor() {
        return valor;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RadioId)) return false;
        RadioId radioId = (RadioId) o;
        return valor.equals(radioId.valor);
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
