package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.RadioNoEncontradoException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import com.jcaa.udec.collections.domain.port.out.ActualizarRadioPort;
import com.jcaa.udec.collections.domain.port.out.BuscarRadioPorIdPort;

public class ActualizarRadioService {
    private final BuscarRadioPorIdPort buscarRadioPorIdPort;
    private final ActualizarRadioPort actualizarRadioPort;

    public ActualizarRadioService(BuscarRadioPorIdPort buscarRadioPorIdPort,
                                  ActualizarRadioPort actualizarRadioPort) {
        this.buscarRadioPorIdPort = buscarRadioPorIdPort;
        this.actualizarRadioPort = actualizarRadioPort;
    }

    public Radio ejecutar(RadioId id, NombreRadio nuevoNombre, Frecuencia nuevaFrecuencia,
                          TipoTransmision nuevoTipo) {
        Radio radio = buscarRadioPorIdPort.buscarPorId(id)
                .orElseThrow(() -> new RadioNoEncontradoException(
                        "No se puede actualizar: no existe ninguna radio con id " + id.valor() + "."));
        radio.actualizarDatos(nuevoNombre, nuevaFrecuencia, nuevoTipo);
        return actualizarRadioPort.actualizar(radio);
    }
}