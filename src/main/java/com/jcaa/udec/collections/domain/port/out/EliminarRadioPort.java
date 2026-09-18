package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.valueobject.RadioId;

public interface EliminarRadioPort {
    void eliminar(RadioId id);
}