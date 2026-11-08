package com.jcaa.udec.collections.domain.core.exception;

/**
 * Excepción de dominio: se lanza cuando un programa se quedaría sin conductor,
 * sin operador o sin productor.
 */
public class ProgramaIncompletoException extends RuntimeException {

    public ProgramaIncompletoException(String mensaje) {
        super(mensaje);
    }
}
