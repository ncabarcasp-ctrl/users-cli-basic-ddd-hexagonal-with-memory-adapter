package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.adapter.persistence.memory.RadioMemoryRepository;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegistrarRadioServiceTest {

    private final RadioMemoryRepository repositorio = new RadioMemoryRepository();
    private final RegistrarRadioService registrarRadioService = new RegistrarRadioService(repositorio);

    @Test
    void registrarUnaRadioDevuelveLaRadioConIdGenerado() {
        Radio radio = registrarRadioService.ejecutar(
                new NombreRadio("Olímpica Stereo"), new Frecuencia(104.9), TipoTransmision.FM);

        assertNotNull(radio.id());
        assertEquals("Olímpica Stereo", radio.nombre().valor());
        assertEquals(104.9, radio.frecuencia().valor());
        assertEquals(TipoTransmision.FM, radio.tipoTransmision());
    }

    @Test
    void registrarDosRadiosGeneraIdentificadoresDistintos() {
        Radio radio1 = registrarRadioService.ejecutar(
                new NombreRadio("Radio Uno"), new Frecuencia(97.5), TipoTransmision.FM);
        Radio radio2 = registrarRadioService.ejecutar(
                new NombreRadio("Radio Dos"), new Frecuencia(101.3), TipoTransmision.FM);

        assertNotEquals(radio1.id(), radio2.id());
    }
}