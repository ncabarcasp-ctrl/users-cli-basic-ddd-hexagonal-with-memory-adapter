package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.RadioDatoInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RadioValueObjectsTest {

    @Test
    void generaUnRadioIdNoVacio() {
        RadioId id = RadioId.generar();

        assertNotNull(id.valor());
        assertEquals(id.valor(), id.toString());
    }

    @Test
    void generaIdentificadoresDistintosEnCadaLlamada() {
        assertNotEquals(RadioId.generar(), RadioId.generar());
    }

    @Test
    void rechazaUnRadioIdVacio() {
        assertThrows(RadioDatoInvalidoException.class, () -> new RadioId("  "));
    }

    @Test
    void rechazaUnNombreVacio() {
        assertThrows(RadioDatoInvalidoException.class, () -> new NombreRadio(""));
    }

    @Test
    void rechazaUnNombreDemasiadoLargo() {
        String nombreLargo = "A".repeat(101);

        assertThrows(RadioDatoInvalidoException.class, () -> new NombreRadio(nombreLargo));
    }

    @Test
    void aceptaUnNombreValidoYLoRecorta() {
        NombreRadio nombre = new NombreRadio("  Radio Cartagena Stereo  ");

        assertEquals("Radio Cartagena Stereo", nombre.valor());
    }

    @Test
    void dosNombresIgualesSinImportarMayusculasSonIguales() {
        assertEquals(new NombreRadio("La FM"), new NombreRadio("la fm"));
    }

    @Test
    void rechazaUnaFrecuenciaMenorOIgualACero() {
        assertThrows(RadioDatoInvalidoException.class, () -> new Frecuencia(0));
        assertThrows(RadioDatoInvalidoException.class, () -> new Frecuencia(-98.1));
    }

    @Test
    void aceptaUnaFrecuenciaValida() {
        Frecuencia frecuencia = new Frecuencia(98.1);

        assertEquals(98.1, frecuencia.valor());
    }

    @Test
    void convierteTextoValidoATipoTransmisionSinImportarMayusculas() {
        assertEquals(TipoTransmision.AM, TipoTransmision.desde("am"));
        assertEquals(TipoTransmision.FM, TipoTransmision.desde(" FM "));
    }

    @Test
    void rechazaUnTipoDeTransmisionDistintoDeAmOFm() {
        assertThrows(RadioDatoInvalidoException.class, () -> TipoTransmision.desde("SATELITAL"));
    }

    @Test
    void rechazaUnTipoDeTransmisionVacio() {
        assertThrows(RadioDatoInvalidoException.class, () -> TipoTransmision.desde(null));
    }
}
