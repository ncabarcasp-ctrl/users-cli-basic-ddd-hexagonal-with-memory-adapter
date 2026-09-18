package com.jcaa.udec;

import com.jcaa.udec.collections.adapter.persistence.memory.GuardarUsuarioAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.ObtenerUsuariosAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.RadioMemoryRepository;
import com.jcaa.udec.collections.application.service.AgregarUsuarioService;
import com.jcaa.udec.collections.application.service.BuscarRadioService;              // NUEVO
import com.jcaa.udec.collections.application.service.ListarRadiosService;             // NUEVO
import com.jcaa.udec.collections.application.service.ObtenerUsuariosService;
import com.jcaa.udec.collections.application.service.RegistrarRadioService;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerUsuarioUseCase;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;
import com.jcaa.udec.collections.domain.port.out.ObtenerUsuariosPort;
import com.jcaa.udec.collections.entrypoint.cli.GuiCli;
import com.jcaa.udec.collections.entrypoint.cli.RadioCli;
import com.jcaa.udec.collections.entrypoint.controller.RadioController;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControladorImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- Ensamblaje Usuario ---
        GuardarUsuarioPort guardarUsuarioPort = new GuardarUsuarioAdapter();
        ObtenerUsuariosPort obtenerUsuariosPort = new ObtenerUsuariosAdapter();
        AgregarUsuarioUseCase agregarUsuarioUseCase = new AgregarUsuarioService(guardarUsuarioPort);
        ObtenerUsuarioUseCase obtenerUsuarioUseCase = new ObtenerUsuariosService(obtenerUsuariosPort);
        UsuarioControlador usuarioControlador =
                new UsuarioControladorImpl(agregarUsuarioUseCase, obtenerUsuarioUseCase);

        // --- Ensamblaje Radio (Create + Read) ---
        RadioMemoryRepository radioRepository = new RadioMemoryRepository();
        RegistrarRadioService registrarRadioService = new RegistrarRadioService(radioRepository);
        BuscarRadioService buscarRadioService = new BuscarRadioService(radioRepository);       // NUEVO
        ListarRadiosService listarRadiosService = new ListarRadiosService(radioRepository);    // NUEVO

        // Constructor ahora recibe 3 argumentos
        RadioController radioController =
                new RadioController(registrarRadioService, buscarRadioService, listarRadiosService);

        // --- Scanner compartido ---
        Scanner scanner = new Scanner(System.in);
        RadioCli radioCli = new RadioCli(radioController, scanner);

        // --- GUI ---
        GuiCli guiCli = new GuiCli(usuarioControlador, radioCli, scanner);
        guiCli.ejecutarAccion();
    }
}