package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.RadioNoEncontradoException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.port.out.BuscarRadioPorIdPort;

public class BuscarRadioService {
    private final BuscarRadioPorIdPort buscarRadioPorIdPort;

    public BuscarRadioService(BuscarRadioPorIdPort buscarRadioPorIdPort) {
        this.buscarRadioPorIdPort = buscarRadioPorIdPort;
    }

    public Radio ejecutar(RadioId id) {
        return buscarRadioPorIdPort.buscarPorId(id)
                .orElseThrow(() -> new RadioNoEncontradoException(
                        "No se encontró ninguna radio con id " + id.valor() + "."));
    }
}