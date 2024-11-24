package cl.playground.view;

import cl.playground.model.Alumno;
import cl.playground.model.Materia;
import cl.playground.model.MateriaEnum;
import cl.playground.service.AlumnoServicio;
import cl.playground.service.ArchivoServicio;
import cl.playground.service.PromedioService;
import cl.playground.service.PromedioServiceImpl;
import cl.playground.util.ScannerSingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu extends MenuTemplate {

    private final String RUTA_ARCHIVO = "src/main/resources/alumnos.txt";
    private final AlumnoServicio alumnoServicio = new AlumnoServicio();
    private final PromedioService promedioService = new PromedioServiceImpl();
    private final ArchivoServicio archivoServicio = new ArchivoServicio(promedioService);

    public Menu() {
        super();
    }

    @Override
    protected void exportarDatos() {
        try {
            System.out.println("=== Exportar Datos ===");

            // VERIFICAR SI HAY ALUMNOS EN LA LISTA
            if (alumnoServicio.listarAlumnos().isEmpty()) {
                System.out.println("No hay alumnos registrados para exportar");
                return;
            }

            // EXPORTAR DATOS USANDO LA RUTA FIJA!!!!!!
            archivoServicio.exportarDatos(alumnoServicio.listarAlumnos(), RUTA_ARCHIVO);
            System.out.println("Los datos han sido exportados exitosamente a: " + RUTA_ARCHIVO);
        } catch (Exception e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }

    }

    @Override
    protected void crearAlumno() {
        Scanner sc = ScannerSingleton.getInstance();

        System.out.println("Ingrese el rut del alumno: ");
        String rut = sc.nextLine();

        System.out.println("Ingresa el nombre del alumno: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el apellido del alumno: ");
        String apellido = sc.nextLine();

        System.out.println("Coloque la direccion del alumno: ");
        String direccion = sc.nextLine();

        //Materia materia = new Materia(MateriaEnum.MATEMATICAS, new ArrayList<>(Arrays.asList(6.0)));

        Alumno alumno = new Alumno(
                rut,
                nombre,
                apellido,
                direccion
                //new ArrayList<>(Arrays.asList(materia))

        );
        alumnoServicio.crearAlumno(alumno);
    }

    @Override
    protected void agregarMateria() {
        Scanner sc = ScannerSingleton.getInstance();

        System.out.println("Listado de alumnos disponibles.");
        for (Alumno alumno : alumnoServicio.listarAlumnos().values()) {
            System.out.println(alumno);
        }

        System.out.println("Ingrese el rut del alumno: ");
        String rut = sc.nextLine();

        System.out.println("Materias disponibles: ");
        for (MateriaEnum materia : MateriaEnum.values()) {
            System.out.println("- " + materia);
        }

        System.out.println("Ingrese el nombre de la materia: ");
        String nombreMateria = sc.nextLine().toUpperCase();

        try {
            MateriaEnum materiaEnum = MateriaEnum.valueOf(nombreMateria);

            List<Double> notas = new ArrayList<>();
            System.out.println("Ingrese 3 notas (Presiona Enter despues de cada nota): ");

            for (int i = 0; i < 3; i++) {
                System.out.print("Nota " + (i + 1) + ": ");
                double nota = sc.nextDouble();

                if (nota >= 1.0 && nota <= 7.0) {
                    notas.add(nota);
                } else {
                    System.out.println("La nota debe estar entre 1.0 y 7.0");
                    return;
                }
            }

            Materia nuevaMateria = new Materia(materiaEnum, notas);
            alumnoServicio.agregarMateria(rut, nuevaMateria);
            System.out.println("Materia agregada exitosamente");

        } catch (IllegalArgumentException e) {
            System.out.println("Materia no valida.");
        }
    }

    @Override
    protected void agregarNotaPasoUno() {
        Scanner sc = ScannerSingleton.getInstance();

        System.out.println("Listado de alumnos disponibles: ");
        System.out.println("--------------------------------");

        for (Alumno alumno : alumnoServicio.listarAlumnos().values()) {
            System.out.println(alumno);
        }

        System.out.println("Ingrese rut del alumno. ");
        String rut = sc.nextLine();

        List<Materia> materiasAlumno = alumnoServicio.materiasPorAlumno(rut);

        if (materiasAlumno == null || materiasAlumno.isEmpty()) {
            System.out.println("El alumno no tiene materias asignadas o no existe.");
            return;
        }

        System.out.println("Materias del alumno: ");
        for (int i = 0; i < materiasAlumno.size(); i++) {
            System.out.println(i + 1 + ". " + materiasAlumno.get(i).getNombre());
        }

        System.out.println("Seleccione el numero de la materia para agregar nota: ");
        try {

            int seleccion = Integer.parseInt(sc.nextLine()) - 1;

            if (seleccion >= 0 && seleccion < materiasAlumno.size()) {
                Materia materiaSeleccionada = materiasAlumno.get(seleccion);

                System.out.println("Ingrese la nueva nota (entre 1.0 y 7.0): ");
                double nuevaNota = Double.parseDouble(sc.nextLine());

                if (nuevaNota >= 1.0 && nuevaNota <= 7.0) {
                    materiaSeleccionada.getNotas().add(nuevaNota);
                    System.out.println("Nota agregada exitosamente");
                } else {
                    System.out.println("La nota debe estar entre 1.0 y 7.0");
                }
            } else {
                System.out.println("Seleccion invalida");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un numero valido");
        }
    }

    @Override
    protected void listarAlumnos() {
        System.out.println("--- Listar Alumnos ---");

        for (Alumno alumno : alumnoServicio.listarAlumnos().values()) {
            System.out.println(alumno);
        }
    }

    @Override
    protected void terminarPrograma() {
        System.out.println("Usted esta saliendo exitosamente del programa.");
    }
}
