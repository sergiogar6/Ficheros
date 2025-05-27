package org.example.PracticaJSON;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.ToString;


import java.util.List;
@Getter @ToString
public class Videojuego {
    private String nombre;
    private String plataforma;
    private double precio;
    private boolean disponible;
    private List<Generos> listaGeneros;

    public Videojuego(String nombre, String plataforma, double precio, boolean disponible, List<Generos> listaGeneros) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.disponible = disponible;
        this.listaGeneros = listaGeneros;
    }

    public static void main(String[] args) {

        Gson gson = new Gson();


    }
}
