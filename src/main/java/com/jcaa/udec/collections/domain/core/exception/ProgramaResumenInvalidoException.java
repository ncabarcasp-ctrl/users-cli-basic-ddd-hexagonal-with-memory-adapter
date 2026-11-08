package com.jcaa.udec.collections.domain.core.exception;

/**
 * Excepción de dominio: se lanza cuando los programas de origen de un programa
 * no cumplen las reglas (resumen sin origen, programa normal con origen, o un
 * programa que se pone a sí mismo como origen).
 */
public class ProgramaResumenInvalidoException extends RuntimeException {

    public ProgramaResumenInvalidoException(String mensaje) {
        super(mensaje);
    }
}
