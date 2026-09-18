package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.ActualizarRadioService;
import com.jcaa.udec.collections.application.service.BuscarRadioService;
import com.jcaa.udec.collections.application.service.ListarRadiosService;
import com.jcaa.udec.collections.application.service.RegistrarRadioService;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;

import java.util.List;

public class RadioController {
    private final RegistrarRadioService registrarRadioService;
    private final BuscarRadioService buscarRadioService;
    private final ListarRadiosService listarRadiosService;
    private final ActualizarRadioService actualizarRadioService;

    public RadioController(RegistrarRadioService registrarRadioService,
                           BuscarRadioService buscarRadioService,
                           ListarRadiosService listarRadiosService,
                           ActualizarRadioService actualizarRadioService) {
        this.registrarRadioService = registrarRadioService;
        this.buscarRadioService = buscarRadioService;
        this.listarRadiosService = listarRadiosService;
        this.actualizarRadioService = actualizarRadioService;
    }

    public Radio registrar(String nombre, String frecuencia, String tipoTransmision) {
        NombreRadio nombreRadio = new NombreRadio(nombre);
        Frecuencia frecuenciaRadio = new Frecuencia(Double.parseDouble(frecuencia.trim()));
        TipoTransmision tipo = TipoTransmision.desde(tipoTransmision);
        return registrarRadioService.ejecutar(nombreRadio, frecuenciaRadio, tipo);
    }

    public Radio buscarPorId(String id) {
        return buscarRadioService.ejecutar(new RadioId(id));
    }

    public List<Radio> listar() {
        return listarRadiosService.ejecutar();
    }

    public Radio actualizar(String id, String nombre, String frecuencia, String tipoTransmision) {
        RadioId radioId = new RadioId(id);
        NombreRadio nombreRadio = new NombreRadio(nombre);
        Frecuencia frecuenciaRadio = new Frecuencia(Double.parseDouble(frecuencia.trim()));
        TipoTransmision tipo = TipoTransmision.desde(tipoTransmision);
        return actualizarRadioService.ejecutar(radioId, nombreRadio, frecuenciaRadio, tipo);
    }
}