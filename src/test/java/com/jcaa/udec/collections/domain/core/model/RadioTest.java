package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RadioTest {

    @Test
    void registraUnaRadioConIdGenerado() {
        Radio radio = Radio.registrar(
                new NombreRadio("Olímpica Stereo"), new Frecuencia(104.9), TipoTransmision.FM);

        assertNotNull(radio.id());
        assertEquals("Olímpica Stereo", radio.nombre().valor());
        assertEquals(104.9, radio.frecuencia().valor());
        assertEquals(TipoTransmision.FM, radio.tipoTransmision());
    }

    @Test
    void actualizarDatosCambiaLosAtributosSinCambiarElId() {
        Radio radio = Radio.registrar(new NombreRadio("Blu Radio"), new Frecuencia(90.5), TipoTransmision.FM);
        RadioId idOriginal = radio.id();

        radio.actualizarDatos(new NombreRadio("Blu Radio Cartagena"), new Frecuencia(91.1), TipoTransmision.FM);

        assertEquals(idOriginal, radio.id());
        assertEquals("Blu Radio Cartagena", radio.nombre().valor());
        assertEquals(91.1, radio.frecuencia().valor());
    }

    @Test
    void dosRadiosSonIgualesSiTienenElMismoIdAunqueCambienSusDatos() {
        Radio radio1 = Radio.registrar(new NombreRadio("RTVC"), new Frecuencia(88.9), TipoTransmision.FM);
        Radio radio2 = new Radio(radio1.id(), new NombreRadio("Otro nombre"), new Frecuencia(1050), TipoTransmision.AM);

        assertEquals(radio1, radio2);
    }

    @Test
    void dosRadiosConLosMismosDatosPeroDistintoIdSonDistintas() {
        Radio radio1 = Radio.registrar(new NombreRadio("La FM"), new Frecuencia(103.1), TipoTransmision.FM);
        Radio radio2 = Radio.registrar(new NombreRadio("La FM"), new Frecuencia(103.1), TipoTransmision.FM);

        assertNotEquals(radio1, radio2);
    }
}
