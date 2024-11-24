package cl.playground.view;

import cl.playground.util.ScannerSingleton;

import java.util.Scanner;

public abstract class MenuTemplate {

    private final Scanner sc;

    public MenuTemplate() {
        this.sc = ScannerSingleton.getInstance();
    }

    protected abstract void exportarDatos();
    protected abstract void crearAlumno();
    protected abstract void agregarMateria();
    protected abstract void agregarNotaPasoUno();
    protected abstract void listarAlumnos();
    protected abstract void terminarPrograma();

    public void iniciarMenu() {
        boolean running = true;

        while (running) {
            String menu = """
                ==============================
                        MENU PRINCIPAL
                ==============================
                1. Crear Alumnos
                2. Listar Alumnos
                3. Agregar Materias
                4. Agregar Notas
                5. exportarDatos
                6. Salir
                ==============================
                Ingrese una opcion:
                """;
            System.out.println(menu);

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    listarAlumnos();
                    break;
                case 3:
                    agregarMateria();
                    break;
                case 4:
                    agregarNotaPasoUno();
                    break;
                case 5:
                    exportarDatos();
                    break;
                case 6:
                    terminarPrograma();
                    running = false;
                    break;
                default:
                    System.out.println("Seleccione una de las opciones validas");
            }
        }


    }
}
