package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.event.EmisionRegistrada;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.DuracionMinutos;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmisionTest {

    private final ProgramaId programa = ProgramaId.generar();
    private final RadioId radio = RadioId.generar();

    private Emision emisionNormal() {
        return Emision.programar(programa, radio, LocalDate.of(2026, 10, 5),
                LocalTime.of(6, 0), new DuracionMinutos(120), false);
    }

    @Test
    void programarUnaEmisionGuardaSusDatosYLasReferenciasPorIdentificador() {
        Emision emision = emisionNormal();

        assertNotNull(emision.id());
        assertEquals(programa, emision.programaId());
        assertEquals(radio, emision.radioId());
        assertEquals(LocalDate.of(2026, 10, 5), emision.fecha());
        assertEquals(LocalTime.of(6, 0), emision.hora());
        assertEquals(120, emision.duracion().minutos());
        assertFalse(emision.esRepeticion());
    }

    @Test
    void programarUnaEmisionGeneraElEventoEmisionRegistrada() {
        Emision emision = emisionNormal();

        assertEquals(1, emision.eventosPendientes().size());
        assertTrue(emision.eventosPendientes().get(0) instanceof EmisionRegistrada);
        EmisionRegistrada evento = (EmisionRegistrada) emision.eventosPendientes().get(0);
        assertEquals(emision.id(), evento.emisionId());
        assertEquals(programa, evento.programaId());
        assertEquals(radio, evento.radioId());
    }

    @Test
    void sePuedeMarcarUnaEmisionComoRepeticion() {
        Emision emision = emisionNormal();

        emision.marcarComoRepeticion();

        assertTrue(emision.esRepeticion());
    }

    @Test
    void noPermiteProgramarUnaEmisionSinSusDatosObligatorios() {
        assertThrows(DatoInvalidoException.class, () -> Emision.programar(
                null, radio, LocalDate.of(2026, 10, 5), LocalTime.of(6, 0), new DuracionMinutos(60), false));
        assertThrows(DatoInvalidoException.class, () -> Emision.programar(
                programa, radio, null, LocalTime.of(6, 0), new DuracionMinutos(60), false));
    }

    @Test
    void laDuracionDebeSerMayorQueCero() {
        assertThrows(DatoInvalidoException.class, () -> new DuracionMinutos(0));
        assertThrows(DatoInvalidoException.class, () -> new DuracionMinutos(-30));
    }
}
