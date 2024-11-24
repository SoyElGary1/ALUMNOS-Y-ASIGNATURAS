package cl.playground.model;

import cl.playground.service.PromedioServiceImpl;

import java.util.List;

public class Materia {
    private MateriaEnum nombre;
    private List<Double> notas;

    public Materia() {
    }

    public Materia(MateriaEnum nombre, List<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public MateriaEnum getNombre() {
        return nombre;
    }

    public void setNombre(MateriaEnum nombre) {
        this.nombre = nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    public double getPromedio() {
        return new PromedioServiceImpl().calcularPromedio(this.notas);
    }

    @Override
    public String toString() {
        return "Materia{" +
                "nombre=" + nombre +
                ", notas=" + notas +
                '}';
    }
}
