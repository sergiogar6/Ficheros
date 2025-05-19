package org.example.Serializacion.Ejercicio5;

public class Jefe extends Empleado{
    private String departamento;

    public Jefe(String nombre, double salario, String departamento) {
        super(nombre, salario);
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Jefe{" +
                "nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}
