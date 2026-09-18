package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioDatoInvalidoException;
 
import java.util.Objects;
 
/**
 * Value Object que representa el nombre de una Radio.
 * Protege la invariante de que el nombre no puede estar vacío
 * ni superar una longitud razonable.
 */
public final class NombreRadio {
 
    private static final int LONGITUD_MAXIMA = 100;
 
    private final String valor;
 
    public NombreRadio(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new RadioDatoInvalidoException("El nombre de la radio no puede estar vacío.");
        }
        if (valor.trim().length() > LONGITUD_MAXIMA) {
            throw new RadioDatoInvalidoException(
                    "El nombre de la radio no puede superar " + LONGITUD_MAXIMA + " caracteres.");
        }
        this.valor = valor.trim();
    }
 
    public String valor() {
        return valor;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NombreRadio)) return false;
        NombreRadio that = (NombreRadio) o;
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
