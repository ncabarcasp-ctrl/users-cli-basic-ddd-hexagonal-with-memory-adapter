package com.jcaa.udec.collections.domain.core.model;
 
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
 
import java.util.Objects;
 
/**
 * Entidad de dominio Radio.
 * Representa una emisora del CEA (Sistema de Encuestas de Programación Radial),
 * con su nombre, su frecuencia y su tipo de transmisión (AM o FM).
 */
public class Radio {
 
    private final RadioId id;
    private NombreRadio nombre;
    private Frecuencia frecuencia;
    private TipoTransmision tipoTransmision;
 
    public Radio(RadioId id, NombreRadio nombre, Frecuencia frecuencia, TipoTransmision tipoTransmision) {
        this.id = id;
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.tipoTransmision = tipoTransmision;
    }
 
    public static Radio registrar(NombreRadio nombre, Frecuencia frecuencia, TipoTransmision tipoTransmision) {
        return new Radio(RadioId.generar(), nombre, frecuencia, tipoTransmision);
    }
 
    public void actualizarDatos(NombreRadio nuevoNombre, Frecuencia nuevaFrecuencia, TipoTransmision nuevoTipo) {
        this.nombre = nuevoNombre;
        this.frecuencia = nuevaFrecuencia;
        this.tipoTransmision = nuevoTipo;
    }
 
    public RadioId id() {
        return id;
    }
 
    public NombreRadio nombre() {
        return nombre;
    }
 
    public Frecuencia frecuencia() {
        return frecuencia;
    }
 
    public TipoTransmision tipoTransmision() {
        return tipoTransmision;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Radio)) return false;
        Radio radio = (Radio) o;
        return id.equals(radio.id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
 
    @Override
    public String toString() {
        return "Radio{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", frecuencia=" + frecuencia +
                ", tipoTransmision=" + tipoTransmision +
                '}';
    }
}
