package cl.playground.service;

import cl.playground.model.Alumno;
import cl.playground.model.Materia;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ArchivoServicio {

    private List<Alumno> alumnosACargar;
    private PromedioService promedioServiceImpl;

    public ArchivoServicio(PromedioService promedioServiceImpl) {
        this.promedioServiceImpl = promedioServiceImpl;
    }

    public void exportarDatos(Map<String, Alumno> alumnos, String rutaArchivo) {
        if (alumnos == null || rutaArchivo == null || alumnos.isEmpty()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {

            for (Alumno alumno : alumnos.values()) {
                writer.write(String.format("Alumno : %s - %s\n",
                        alumno.getRut(),
                        alumno.getNombre())
                );

                for (Materia materia : alumno.getMaterias()) {
                    writer.write(String.format("Materia : %s - Promedio : %.1f\n",
                            materia.getNombre(),
                            promedioServiceImpl.calcularPromedio(materia.getNotas())
                    ));
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
