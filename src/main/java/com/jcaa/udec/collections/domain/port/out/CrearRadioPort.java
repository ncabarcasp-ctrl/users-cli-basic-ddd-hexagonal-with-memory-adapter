package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Radio;
 
/**
 * Puerto de salida (output port) para la operación de creación (Create de CRUDL).
 * Cualquier adaptador de persistencia que quiera dar soporte al caso de uso
 * "Registrar radio" debe implementar este contrato.
 */
public interface CrearRadioPort {
 
    Radio crear(Radio radio);
}
