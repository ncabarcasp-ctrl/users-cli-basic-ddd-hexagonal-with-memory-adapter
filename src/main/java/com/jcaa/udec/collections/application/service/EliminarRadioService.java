package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.RadioNoEncontradoException;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.port.out.BuscarRadioPorIdPort;
import com.jcaa.udec.collections.domain.port.out.EliminarRadioPort;

public class EliminarRadioService {
    private final BuscarRadioPorIdPort buscarRadioPorIdPort;
    private final EliminarRadioPort eliminarRadioPort;

    public EliminarRadioService(BuscarRadioPorIdPort buscarRadioPorIdPort,
                                EliminarRadioPort eliminarRadioPort) {
        this.buscarRadioPorIdPort = buscarRadioPorIdPort;
        this.eliminarRadioPort = eliminarRadioPort;
    }

    public void ejecutar(RadioId id) {
        buscarRadioPorIdPort.buscarPorId(id)
                .orElseThrow(() -> new RadioNoEncontradoException(
                        "No se puede eliminar: no existe ninguna radio con id " + id.valor() + "."));
        eliminarRadioPort.eliminar(id);
    }
}