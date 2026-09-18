package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import java.util.Optional;

public interface BuscarRadioPorIdPort {
    Optional<Radio> buscarPorId(RadioId id);
}