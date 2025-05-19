package org.example.Serializacion;

import java.io.*;
import java.util.*;

public class MapaProductos {

    public static void main(String[] args) {
        String archivo = "src/main/resources/mapa.ser";

        HashMap<String, Producto> mapaProductos = new HashMap<>();
        mapaProductos.put("A01", new Producto("Ratón", 15.99, 50));
        mapaProductos.put("A02", new Producto("Teclado", 25.99, 30));
        mapaProductos.put("A03", new Producto("Pantalla", 150.49, 20));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mapaProductos);
            System.out.println("Mapa serializado correctamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al serializar el mapa.");
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            HashMap<String, Producto> mapaRecuperado = (HashMap<String, Producto>) ois.readObject();
            System.out.println("Mapa deserializado correctamente.");

            TreeMap<String, Producto> mapaOrdenado = new TreeMap<>(mapaRecuperado);

            System.out.println("Contenido ordenado por clave:");
            for (Map.Entry<String, Producto> entry : mapaOrdenado.entrySet()) {
                System.out.println("Clave: " + entry.getKey() + " → " + entry.getValue());
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar el mapa.");
            e.printStackTrace();
        }
    }
}
