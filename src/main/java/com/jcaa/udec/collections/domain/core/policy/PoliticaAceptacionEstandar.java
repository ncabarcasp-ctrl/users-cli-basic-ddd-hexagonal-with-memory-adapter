package com.jcaa.udec.collections.domain.core.policy;

import com.jcaa.udec.collections.domain.core.valueobject.NivelAceptacion;
import com.jcaa.udec.collections.domain.core.valueobject.PorcentajeAceptacion;

/**
 * Variante estándar de la política: 70 % o más es ALTA, 40 % o más es MEDIA
 * y por debajo de 40 % es BAJA. Los umbrales son un ejemplo; en un caso real
 * los definiría el experto del negocio.
 */
public class PoliticaAceptacionEstandar implements PoliticaAceptacion {

    private static final double UMBRAL_ALTA = 70;
    private static final double UMBRAL_MEDIA = 40;

    @Override
    public NivelAceptacion clasificar(PorcentajeAceptacion porcentaje) {
        if (porcentaje.valor() >= UMBRAL_ALTA) {
            return NivelAceptacion.ALTA;
        }
        if (porcentaje.valor() >= UMBRAL_MEDIA) {
            return NivelAceptacion.MEDIA;
        }
        return NivelAceptacion.BAJA;
    }
}
