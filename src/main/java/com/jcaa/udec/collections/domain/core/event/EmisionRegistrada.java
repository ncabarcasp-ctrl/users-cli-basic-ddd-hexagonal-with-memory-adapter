package com.jcaa.udec.collections.domain.core.event;

import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;

import java.time.Instant;

/**
 * Evento de dominio: se registró una emisión de un programa en una radio.
 */
public final class EmisionRegistrada implements EventoDominio {

    private final EmisionId emisionId;
    private final ProgramaId programaId;
    private final RadioId radioId;
    private final Instant ocurridoEn;

    public EmisionRegistrada(EmisionId emisionId, ProgramaId programaId, RadioId radioId) {
        this.emisionId = emisionId;
        this.programaId = programaId;
        this.radioId = radioId;
        this.ocurridoEn = Instant.now();
    }

    public EmisionId emisionId() {
        return emisionId;
    }

    public ProgramaId programaId() {
        return programaId;
    }

    public RadioId radioId() {
        return radioId;
    }

    @Override
    public Instant ocurridoEn() {
        return ocurridoEn;
    }
}
