package com.jcaa.udec.collections.domain.core.specification;

import com.jcaa.udec.collections.domain.core.model.Programa;

/**
 * Regla: el programa es de resumen y tiene al menos un programa de origen.
 * Sirve, por ejemplo, para filtrar solo los programas de resumen.
 */
public class ProgramaResumenConOrigen implements Especificacion<Programa> {

    @Override
    public boolean esSatisfechaPor(Programa programa) {
        return programa.esResumen() && !programa.origenes().isEmpty();
    }
}
