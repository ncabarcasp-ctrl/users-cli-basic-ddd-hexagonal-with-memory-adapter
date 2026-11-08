package com.jcaa.udec.collections.domain.core.policy;

import com.jcaa.udec.collections.domain.core.valueobject.NivelAceptacion;
import com.jcaa.udec.collections.domain.core.valueobject.PorcentajeAceptacion;

/**
 * Política de dominio (patrón Strategy): decide cómo se clasifica el nivel de
 * aceptación de un programa. Cada variante de la regla es una clase distinta.
 */
public interface PoliticaAceptacion {

    NivelAceptacion clasificar(PorcentajeAceptacion porcentaje);
}
