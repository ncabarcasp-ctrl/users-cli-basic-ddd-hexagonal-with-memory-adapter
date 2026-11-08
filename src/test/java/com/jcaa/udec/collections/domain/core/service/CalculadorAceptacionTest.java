package com.jcaa.udec.collections.domain.core.service;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.Encuesta;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.PorcentajeAceptacion;
import com.jcaa.udec.collections.domain.core.valueobject.ResultadoEncuesta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadorAceptacionTest {

    private final CalculadorAceptacion calculador = new CalculadorAceptacion();

    @Test
    void calculaElPorcentajeCombinandoVariasEncuestas() {
        Encuesta primera = Encuesta.registrar(EmisionId.generar(), new ResultadoEncuesta(100, 80, 10, 10));
        Encuesta segunda = Encuesta.registrar(EmisionId.generar(), new ResultadoEncuesta(100, 40, 40, 20));

        PorcentajeAceptacion porcentaje = calculador.calcular(List.of(primera, segunda));

        // (80 + 40) aprobaciones sobre 200 encuestados
        assertEquals(60.0, porcentaje.valor());
    }

    @Test
    void conUnaSolaEncuestaDevuelveSuPorcentajeDeAprobacion() {
        Encuesta encuesta = Encuesta.registrar(EmisionId.generar(), new ResultadoEncuesta(200, 150, 30, 20));

        assertEquals(75.0, calculador.calcular(List.of(encuesta)).valor());
    }

    @Test
    void sinEncuestasNoSePuedeCalcular() {
        assertThrows(DatoInvalidoException.class, () -> calculador.calcular(List.of()));
        assertThrows(DatoInvalidoException.class, () -> calculador.calcular(null));
    }

    @Test
    void elPorcentajeDeAceptacionDebeEstarEntreCeroYCien() {
        assertThrows(DatoInvalidoException.class, () -> new PorcentajeAceptacion(-1));
        assertThrows(DatoInvalidoException.class, () -> new PorcentajeAceptacion(100.5));
    }
}
