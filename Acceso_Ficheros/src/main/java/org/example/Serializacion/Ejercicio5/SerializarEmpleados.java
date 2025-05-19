package org.example.Serializacion.Ejercicio5;

import java.io.*;
import java.util.ArrayList;

public class SerializarEmpleados {
    public static void main(String[] args) {
        String archivo = "src/main/resources/empleados.ser";

        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Carlos", 2000));
        empleados.add(new Empleado("Ana", 2200));
        empleados.add(new Jefe("Pedro", 4000, "IT"));
        empleados.add(new Jefe("Laura", 3500, "Finanzas"));


        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {

            oos.writeObject(empleados);
            System.out.println("Lista serializada en " + archivo);

        } catch (IOException e) {
            System.out.println("Error al serializar.");
            e.printStackTrace();
        }


        ArrayList<Empleado> listaRecuperada = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {

            listaRecuperada = (ArrayList<Empleado>) ois.readObject();
            System.out.println("Lista deserializada correctamente.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar.");
            e.printStackTrace();
        }


        if (listaRecuperada != null) {
            System.out.println("Lista de empleados:");
            for (Empleado emp : listaRecuperada) {
                System.out.println(emp);
            }
        }
    }
}
