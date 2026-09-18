package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.adapter.persistence.memory.RadioMemoryRepository;
import com.jcaa.udec.collections.domain.core.exception.RadioNoEncontradoException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RadioReadTest {

    private final RadioMemoryRepository repositorio = new RadioMemoryRepository();
    private final RegistrarRadioService registrarRadioService = new RegistrarRadioService(repositorio);
    private final BuscarRadioService buscarRadioService = new BuscarRadioService(repositorio);
    private final ListarRadiosService listarRadiosService = new ListarRadiosService(repositorio);

    @Test
    void buscarUnaRadioRegistradaLaEncuentra() {
        Radio radio = registrarRadioService.ejecutar(
                new NombreRadio("La FM"), new Frecuencia(103.1), TipoTransmision.FM);
        Radio encontrada = buscarRadioService.ejecutar(radio.id());
        assertEquals(radio, encontrada);
    }

    @Test
    void buscarUnaRadioInexistenteLanzaExcepcion() {
        assertThrows(RadioNoEncontradoException.class, () ->
                buscarRadioService.ejecutar(RadioId.generar()));
    }

    @Test
    void listarDevuelveTodasLasRadiosRegistradas() {
        registrarRadioService.ejecutar(new NombreRadio("Radio Nacional"), new Frecuencia(1120), TipoTransmision.AM);
        registrarRadioService.ejecutar(new NombreRadio("La Mega"), new Frecuencia(90.9), TipoTransmision.FM);
        assertEquals(2, listarRadiosService.ejecutar().size());
    }

    @Test
    void listarSinRadiosRegistradasDevuelveListaVacia() {
        assertTrue(listarRadiosService.ejecutar().isEmpty());
    }
}