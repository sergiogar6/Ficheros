package org.example.PracticaJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class AppVideojuego {
    static ArrayList<Videojuego> listaVideojuegos = new ArrayList<>();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static String json;

    public static void main(String[] args) {
        // Crear 3 videojuegos y guardarlos en una coleccion (arraylist)
        listaVideojuegos.add(new Videojuego("Halo Infinite", "Xbox", 59.99, true, Arrays.asList(Generos.Accion, Generos.Shooter)));
        listaVideojuegos.add(new Videojuego("Forza Horizon 5", "Xbox", 49.99, true, Arrays.asList(Generos.Carreras)));
        listaVideojuegos.add(new Videojuego("Gears 5", "Xbox", 19.99, false, Arrays.asList(Generos.Accion, Generos.Aventura)));

        // Guardar la coleccion en un JSON
        guardarJson();

        // Leer por pantalla el JSON
        leerJson();

        // Reconstruir la coleccion desde el JSON
        jsonAObjeto();

        // Añadir un nuevo juego a la coleccion
        listaVideojuegos.add(new Videojuego("Spider-Man 2", "PS5", 69.99, true, Arrays.asList(Generos.Accion)));

        // Mostrar juegos precio menor a 30€
        System.out.println("Juegos de menos de 30€: ");
        listaVideojuegos.stream()
                .filter(v -> v.getPrecio() < 30)
                .forEach(System.out::println);

        // Volver a guardar la lista en el JSON
        guardarJson();

    }

    public static void guardarJson() {
        json = gson.toJson(listaVideojuegos);
        try (FileWriter writer = new FileWriter("src/main/resources/videojuegos.json")) {
            gson.toJson(listaVideojuegos, writer);
            System.out.println("Videojuegos guardados en videojuegos.JSON");
        } catch (Exception e) {
            System.out.println("Algo ha salido mal");
            e.printStackTrace();
        }
    }

    public static void jsonAObjeto() {
        // Uso de Type para poder almacenar colecciones en JSON
        Type listaTipo = new TypeToken<ArrayList<Videojuego>>() {}.getType();
        listaVideojuegos = gson.fromJson(json, listaTipo);

    }

    public static void leerJson() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/videojuegos.json"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException(e);
        }
    }
}
