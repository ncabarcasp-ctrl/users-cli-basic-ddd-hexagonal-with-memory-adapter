package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ListarRadiosPort;
import java.util.List;

public class ListarRadiosService {
    private final ListarRadiosPort listarRadiosPort;

    public ListarRadiosService(ListarRadiosPort listarRadiosPort) {
        this.listarRadiosPort = listarRadiosPort;
    }

    public List<Radio> ejecutar() {
        return listarRadiosPort.listar();
    }
}