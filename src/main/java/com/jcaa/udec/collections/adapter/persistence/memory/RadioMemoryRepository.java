package com.jcaa.udec.collections.adapter.persistence.memory;
 
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.CrearRadioPort;
 
import java.util.LinkedHashMap;
import java.util.Map;
 
/**
 * Adaptador de persistencia en memoria para la entidad Radio.
 * Por ahora solo implementa CrearRadioPort porque es lo único que existe
 * en el dominio; en próximas fases (Read, Update, Delete, List) esta misma
 * clase va a implementar también esos puertos, reutilizando el mismo mapa.
 */
public class RadioMemoryRepository implements CrearRadioPort {
 
    private final Map<String, Radio> almacenamiento = new LinkedHashMap<>();
 
    @Override
    public Radio crear(Radio radio) {
        almacenamiento.put(radio.id().valor(), radio);
        return radio;
    }
}
