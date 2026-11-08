package com.jcaa.udec.collections.domain.core.exception;

/**
 * Excepción de dominio: se lanza cuando una persona quedaría asignada a programas
 * de más de una compañía productora.
 */
public class PersonalEnVariasProductorasException extends RuntimeException {

    public PersonalEnVariasProductorasException(String mensaje) {
        super(mensaje);
    }
}
