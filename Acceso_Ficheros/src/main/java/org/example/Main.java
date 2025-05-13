package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        combinarTextos(new File("src/main/resources/archivo1.txt"), new File("src/main/resources/archivo2.txt"));
    }

    public static void generarArchivos() throws IOException {
        System.out.println("Introduce la cantidad de archivos a crear: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        System.out.println("Introduce la carpeta donde quieres crearlos: ");
        String carpeta = sc.nextLine();

        File directorio = new File(carpeta);

        directorio.mkdir();

        for (int i = 1; i <= cantidad; i++) {

            File file = new File(carpeta + "/archivo(" + i + ").txt");
            if (file.createNewFile()) System.out.println(file.getName() + " creado con exito.");
            BufferedWriter escritor = new BufferedWriter(new FileWriter(carpeta + "/" + file.getName()));
            escritor.write("Este es el fichero " + file.getName());
            escritor.close();
        }

    }

    public static void listarArchivos(String ruta) {
        File carpeta = new File(ruta);

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null && archivos.length > 0) {
                System.out.println("Listado archivos de la carpeta " + ruta + "...");
                for (File f : archivos) {
                    if (f.isFile()) {
                        System.out.println(f.getName() + " - " + f.length() + " bytes");
                    }
                }
            } else {
                System.out.println("La carpeta esta vacia");
            }
        } else System.err.println("La ruta proporcionada no existe");
    }

    public static void listarArchivos(String ruta, String tipoArchivo) {
        File carpeta = new File(ruta);

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null && archivos.length > 0) {
                System.out.println("Listado archivos con extension " + tipoArchivo + " de la carpeta " + ruta + "...");
                for (File f : archivos) {
                    if (f.isFile() && f.getName().endsWith(tipoArchivo)) {
                        System.out.println(f.getName() + " - " + f.length() + " bytes");
                    }
                }
            } else {
                System.out.println("La carpeta esta vacia");
            }
        } else System.err.println("La ruta proporcionada no existe");
    }

    public static int buscarPalabra(String palabraBuscada) throws IOException {
        System.out.println("Escribe la ruta del archivo");
        String ruta = sc.next();
        BufferedReader lector = new BufferedReader(new FileReader(ruta));

        int count = 0;
        String linea;
        String[] palabras;

        while ((linea = lector.readLine()) != null) {
            palabras = linea.split(" ");
            for (String palabra : palabras) {
                if (palabra.equalsIgnoreCase(palabraBuscada)) {
                    count++;
                }
            }
        }

        lector.close();

        return count;
    }

    public static int buscarPalabraScanner(String palabraBuscada) throws IOException {

        File file = new File("src/main/resources/datos.txt");
        Scanner lector = new Scanner(file);

        int count = 0;

        String[] palabras;

        while (lector.hasNextLine()) {
            String linea = lector.nextLine();
            palabras = linea.split(" ");
            for (String palabra : palabras) {
                if (palabra.equalsIgnoreCase(palabraBuscada)) {
                    count++;
                }
            }
        }

        lector.close();

        return count;
    }

    public static void empezarMayuscula(File archivo) throws IOException {

        File archivoTemp = new File(archivo.getParent(), "archivoTemp.txt");

        try (
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemp))
        ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] palabras = linea.split("\\s+"); // Maneja varios espacios o tabs
                StringBuilder nuevaLinea = new StringBuilder();

                for (String palabra : palabras) {
                    if (!palabra.isEmpty()) {
                        String mayus = palabra.substring(0, 1).toUpperCase() +
                                palabra.substring(1).toLowerCase();
                        nuevaLinea.append(mayus).append(" ");
                    }
                }

                escritor.write(nuevaLinea.toString().trim());
                escritor.newLine();
            }
        }

        if (archivo.delete()) {
            archivoTemp.renameTo(archivo);
            System.out.println("Archivo modificado correctamente");
        } else {
            System.out.println("No se ha podido remplezar el archivo original");
        }
    }

    public static void combinarTextos(File archivo1, File archivo2) throws FileNotFoundException {
        File archivoSalida = new File(archivo1.getParentFile()+"/archivoCombinado.txt");

        Scanner sc1 = new Scanner(archivo1);
        Scanner sc2 = new Scanner(archivo2);
        PrintWriter escritor = new PrintWriter(archivoSalida);

        while (sc1.hasNext() || sc2.hasNext()) {
            if (sc1.hasNext()) {
                escritor.print(sc1.next() + " ");
            }
            if (sc2.hasNext()) {
                escritor.print(sc2.next() + " ");
            }
        }

        sc1.close();
        sc2.close();
        escritor.close();

        System.out.println("Archivo combinado creado: " + archivoSalida.getAbsolutePath());
    }
}