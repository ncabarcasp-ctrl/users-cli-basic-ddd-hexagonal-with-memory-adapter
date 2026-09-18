package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.adapter.persistence.memory.RadioMemoryRepository;
import com.jcaa.udec.collections.domain.core.exception.RadioNoEncontradoException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RadioDeleteTest {

    private final RadioMemoryRepository repositorio = new RadioMemoryRepository();
    private final RegistrarRadioService registrarRadioService = new RegistrarRadioService(repositorio);
    private final BuscarRadioService buscarRadioService = new BuscarRadioService(repositorio);
    private final EliminarRadioService eliminarRadioService =
            new EliminarRadioService(repositorio, repositorio);

    @Test
    void eliminarUnaRadioExistenteHaceQueYaNoSeEncuentre() {
        Radio radio = registrarRadioService.ejecutar(
                new NombreRadio("Tropicana"), new Frecuencia(93.9), TipoTransmision.FM);

        eliminarRadioService.ejecutar(radio.id());

        assertThrows(RadioNoEncontradoException.class, () ->
                buscarRadioService.ejecutar(radio.id()));
    }

    @Test
    void eliminarUnaRadioInexistenteLanzaExcepcion() {
        assertThrows(RadioNoEncontradoException.class, () ->
                eliminarRadioService.ejecutar(RadioId.generar()));
    }
}