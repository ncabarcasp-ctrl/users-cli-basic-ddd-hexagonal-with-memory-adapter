package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioDatoInvalidoException;
 
/**
 * Value Object que representa el tipo de transmisión de una Radio.
 * Protege la invariante de que solo se aceptan los valores AM o FM.
 */
public enum TipoTransmision {
 
    AM,
    FM;
 
    public static TipoTransmision desde(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new RadioDatoInvalidoException("El tipo de transmisión no puede estar vacío.");
        }
        try {
            return TipoTransmision.valueOf(valor.trim().toUpperCase());
        } catch (IllegalArgumentException excepcionOriginal) {
            throw new RadioDatoInvalidoException("El tipo de transmisión solo puede ser AM o FM.");
        }
    }
}
