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

class RadioUpdateTest {

    private final RadioMemoryRepository repositorio = new RadioMemoryRepository();
    private final RegistrarRadioService registrarRadioService = new RegistrarRadioService(repositorio);
    private final BuscarRadioService buscarRadioService = new BuscarRadioService(repositorio);
    private final ActualizarRadioService actualizarRadioService =
            new ActualizarRadioService(repositorio, repositorio);

    @Test
    void actualizarUnaRadioExistenteCambiaSusDatos() {
        Radio radio = registrarRadioService.ejecutar(
                new NombreRadio("Radio Reloj"), new Frecuencia(1200), TipoTransmision.AM);

        Radio actualizada = actualizarRadioService.ejecutar(
                radio.id(), new NombreRadio("Radio Reloj Caribe"), new Frecuencia(1250), TipoTransmision.AM);

        assertEquals("Radio Reloj Caribe", actualizada.nombre().valor());
        assertEquals(1250.0, actualizada.frecuencia().valor());
    }

    @Test
    void actualizarUnaRadioExistenteSeReflejaEnBusquedaPosterior() {
        Radio radio = registrarRadioService.ejecutar(
                new NombreRadio("Radio Uno"), new Frecuencia(97.5), TipoTransmision.FM);

        actualizarRadioService.ejecutar(
                radio.id(), new NombreRadio("Radio Uno Cartagena"), new Frecuencia(97.9), TipoTransmision.FM);

        Radio encontrada = buscarRadioService.ejecutar(radio.id());
        assertEquals("Radio Uno Cartagena", encontrada.nombre().valor());
    }

    @Test
    void actualizarUnaRadioInexistenteLanzaExcepcion() {
        assertThrows(RadioNoEncontradoException.class, () ->
                actualizarRadioService.ejecutar(
                        RadioId.generar(), new NombreRadio("X"), new Frecuencia(100), TipoTransmision.FM));
    }
}