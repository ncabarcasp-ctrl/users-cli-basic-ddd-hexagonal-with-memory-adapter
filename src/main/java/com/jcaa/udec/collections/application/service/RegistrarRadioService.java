package com.jcaa.udec.collections.application.service;

 
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import com.jcaa.udec.collections.domain.port.out.CrearRadioPort;
 
/**
 * Caso de uso "Registrar radio" (Create de CRUDL, HU-01 del backlog del CEA).
 * Coordina la creación de una nueva Radio y delega su persistencia
 * en el puerto de salida correspondiente, sin conocer el detalle
 * de cómo ni dónde se guarda.
 */
public class RegistrarRadioService {
 
    private final CrearRadioPort crearRadioPort;
 
    public RegistrarRadioService(CrearRadioPort crearRadioPort) {
        this.crearRadioPort = crearRadioPort;
    }
 
    public Radio ejecutar(NombreRadio nombre, Frecuencia frecuencia, TipoTransmision tipoTransmision) {
        Radio radio = Radio.registrar(nombre, frecuencia, tipoTransmision);
        return crearRadioPort.crear(radio);
    }
}
