package com.jcaa.udec.collections.domain.core.policy;

import com.jcaa.udec.collections.domain.core.valueobject.NivelAceptacion;
import com.jcaa.udec.collections.domain.core.valueobject.PorcentajeAceptacion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PoliticaAceptacionEstandarTest {

    private final PoliticaAceptacion politica = new PoliticaAceptacionEstandar();

    @Test
    void clasificaComoAltaDesdeElSetentaPorCiento() {
        assertEquals(NivelAceptacion.ALTA, politica.clasificar(new PorcentajeAceptacion(70)));
        assertEquals(NivelAceptacion.ALTA, politica.clasificar(new PorcentajeAceptacion(95.5)));
    }

    @Test
    void clasificaComoMediaEntreCuarentaYSetentaPorCiento() {
        assertEquals(NivelAceptacion.MEDIA, politica.clasificar(new PorcentajeAceptacion(40)));
        assertEquals(NivelAceptacion.MEDIA, politica.clasificar(new PorcentajeAceptacion(69.9)));
    }

    @Test
    void clasificaComoBajaPorDebajoDeCuarentaPorCiento() {
        assertEquals(NivelAceptacion.BAJA, politica.clasificar(new PorcentajeAceptacion(39.9)));
        assertEquals(NivelAceptacion.BAJA, politica.clasificar(new PorcentajeAceptacion(0)));
    }
}
