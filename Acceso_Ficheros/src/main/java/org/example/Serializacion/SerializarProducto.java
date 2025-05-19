package org.example.Serializacion;

import java.io.*;
import java.util.ArrayList;

public class SerializarProducto {

    public static void main(String[] args) {

        String rutaArchivo = "src/main/resources/clase_prueba.ser";
        ArrayList<Producto> productosRecuperados = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(rutaArchivo))) {

            productosRecuperados = (ArrayList<Producto>) in.readObject();
            System.out.println("Lista deserializada correctamente.");

        } catch (FileNotFoundException e) {

            System.out.println("Archivo no encontrado. Se creará uno nuevo con datos por defecto.");
            productosRecuperados = crearDatosPorDefecto();
            guardarLista(productosRecuperados, rutaArchivo);

        } catch (IOException e) {

            System.out.println("Error al leer el archivo. Creando uno nuevo con datos por defecto.");
            productosRecuperados = crearDatosPorDefecto();
            guardarLista(productosRecuperados, rutaArchivo);

        } catch (ClassNotFoundException e) {

            System.out.println("No se pudo leer la clase.");
            e.printStackTrace();
        }

        if (productosRecuperados != null) {
            System.out.println("Lista de productos:");
            for (Producto p : productosRecuperados) {
                System.out.println(p);
            }
        }
    }


    private static ArrayList<Producto> crearDatosPorDefecto() {

        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Teclado", 19.50, 30));
        lista.add(new Producto("Raton", 14.66, 50));
        return lista;
    }

    private static void guardarLista(ArrayList<Producto> lista, String rutaArchivo) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {

            out.writeObject(lista);
            System.out.println("Archivo creado con datos por defecto.");

        } catch (IOException e) {

            System.out.println("Error al guardar el archivo.");
            e.printStackTrace();

        }
    }
}
