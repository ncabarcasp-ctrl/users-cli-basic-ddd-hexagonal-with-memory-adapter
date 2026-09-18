package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.entrypoint.controller.RadioController;

import java.util.List;
import java.util.Scanner;

public class RadioCli {
    private final RadioController radioController;
    private final Scanner scanner;

    public RadioCli(RadioController radioController, Scanner scanner) {
        this.radioController = radioController;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("--- Gestión de Radios ---");
            System.out.println("1. Registrar radio");
            System.out.println("2. Buscar radio por id");
            System.out.println("3. Listar radios");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    registrar();
                    break;
                case "2":
                    buscar();
                    break;
                case "3":
                    listar();
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void registrar() {
        try {
            System.out.print("Nombre de la radio: ");
            String nombre = scanner.nextLine();
            System.out.print("Frecuencia (ej. 98.1): ");
            String frecuencia = scanner.nextLine();
            System.out.print("Tipo de transmisión (AM/FM): ");
            String tipo = scanner.nextLine();
            Radio radio = radioController.registrar(nombre, frecuencia, tipo);
            System.out.println("Radio registrada correctamente: " + radio);
        } catch (RuntimeException e) {
            System.out.println("No se pudo registrar la radio: " + e.getMessage());
        }
    }

    private void buscar() {
        try {
            System.out.print("Id de la radio: ");
            String id = scanner.nextLine();
            Radio radio = radioController.buscarPorId(id);
            System.out.println(radio);
        } catch (RuntimeException e) {
            System.out.println("No se pudo buscar la radio: " + e.getMessage());
        }
    }

    private void listar() {
        List<Radio> radios = radioController.listar();
        if (radios.isEmpty()) {
            System.out.println("No hay radios registradas todavía.");
            return;
        }
        radios.forEach(System.out::println);
    }
}