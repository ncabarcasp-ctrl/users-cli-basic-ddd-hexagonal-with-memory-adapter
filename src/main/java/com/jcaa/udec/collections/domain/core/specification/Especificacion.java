package com.jcaa.udec.collections.domain.core.specification;

/**
 * Patrón Specification: convierte una regla de negocio en un objeto que responde
 * verdadero o falso para un candidato. Se pueden combinar con y, o, no.
 *
 * @param <T> tipo de objeto sobre el que se evalúa la regla
 */
public interface Especificacion<T> {

    boolean esSatisfechaPor(T candidato);

    default Especificacion<T> y(Especificacion<T> otra) {
        return candidato -> this.esSatisfechaPor(candidato) && otra.esSatisfechaPor(candidato);
    }

    default Especificacion<T> o(Especificacion<T> otra) {
        return candidato -> this.esSatisfechaPor(candidato) || otra.esSatisfechaPor(candidato);
    }

    default Especificacion<T> no() {
        return candidato -> !this.esSatisfechaPor(candidato);
    }
}
