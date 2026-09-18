package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.entrypoint.controller.RadioController;
 
import java.util.Scanner;
 
/**
 * Adaptador de entrada de línea de comandos para la entidad Radio.
 * Por ahora el menú solo tiene la opción de registrar (Create); proximamente se agregara las opciones 2 a 5 (buscar, listar, actualizar, eliminar).
 */
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
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine();
 
            switch (opcion) {
                case "1":
                    registrar();
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
}
