package cl.playground.model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {

    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private List<Materia> materias = new ArrayList<>();

    public Alumno() {
    }

    public Alumno(String rut, String nombre, String apellido, String direccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public Alumno(String rut, String nombre, String apellido, String direccion, List<Materia> materias) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.materias = materias;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Datos Alumno\n");
        sb.append("RUT: ").append(rut).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Apellido: ").append(apellido).append("\n");
        sb.append("Dirección: ").append(direccion).append("\n");
        sb.append("Materias\n");

        if (materias != null && !materias.isEmpty()) {
            for (Materia materia : materias) {
                sb.append(materia.getNombre()).append("\n");
                sb.append("Notas:\n");
                sb.append( String.format("%.2f",  materia.getPromedio())).append("\n");
            }
        } else {
            sb.append("No tiene materias asignadas\n");
        }

        return sb.toString();
    }


}
