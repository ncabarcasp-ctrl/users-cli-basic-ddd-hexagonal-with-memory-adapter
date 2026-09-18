package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.RegistrarRadioService;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
 
/**
 * Controlador de entrada (entrypoint) para la entidad Radio.
 * Traduce los datos "en bruto" que llegan desde la CLI (Strings) en
 * objetos de dominio válidos, e invoca los casos de uso correspondientes.
 * Por ahora solo tiene el método de creación; en próximas fases se le
 * agregarán buscarPorId, listar, actualizar y eliminar.
 */
public class RadioController {
 
    private final RegistrarRadioService registrarRadioService;
 
    public RadioController(RegistrarRadioService registrarRadioService) {
        this.registrarRadioService = registrarRadioService;
    }
 
    public Radio registrar(String nombre, String frecuencia, String tipoTransmision) {
        NombreRadio nombreRadio = new NombreRadio(nombre);
        Frecuencia frecuenciaRadio = new Frecuencia(Double.parseDouble(frecuencia.trim()));
        TipoTransmision tipo = TipoTransmision.desde(tipoTransmision);
        return registrarRadioService.ejecutar(nombreRadio, frecuenciaRadio, tipo);
    }
}
