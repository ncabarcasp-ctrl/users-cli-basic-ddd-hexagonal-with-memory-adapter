package com.jcaa.udec.collections.domain.core.service;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.Encuesta;
import com.jcaa.udec.collections.domain.core.valueobject.PorcentajeAceptacion;

import java.util.List;

/**
 * Servicio de dominio: combina los resultados de varias encuestas para calcular
 * el porcentaje de aprobación. No guarda estado ni consulta bases de datos:
 * recibe las encuestas ya cargadas.
 */
public class CalculadorAceptacion {

    public PorcentajeAceptacion calcular(List<Encuesta> encuestas) {
        if (encuestas == null || encuestas.isEmpty()) {
            throw new DatoInvalidoException("Se necesita al menos una encuesta para calcular la aceptación.");
        }
        long aprobaciones = 0;
        long encuestados = 0;
        for (Encuesta encuesta : encuestas) {
            aprobaciones += encuesta.resultado().aprobaciones();
            encuestados += encuesta.resultado().encuestados();
        }
        return new PorcentajeAceptacion(aprobaciones * 100.0 / encuestados);
    }
}
