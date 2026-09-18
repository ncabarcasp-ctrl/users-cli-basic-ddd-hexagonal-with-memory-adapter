package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.port.out.BuscarRadioPorIdPort;
import com.jcaa.udec.collections.domain.port.out.CrearRadioPort;
import com.jcaa.udec.collections.domain.port.out.ListarRadiosPort;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RadioMemoryRepository implements CrearRadioPort, BuscarRadioPorIdPort, ListarRadiosPort {

    private final Map<String, Radio> almacenamiento = new LinkedHashMap<>();

    @Override
    public Radio crear(Radio radio) {
        almacenamiento.put(radio.id().valor(), radio);
        return radio;
    }

    @Override
    public Optional<Radio> buscarPorId(RadioId id) {
        return Optional.ofNullable(almacenamiento.get(id.valor()));
    }

    @Override
    public List<Radio> listar() {
        return List.copyOf(almacenamiento.values());
    }
}