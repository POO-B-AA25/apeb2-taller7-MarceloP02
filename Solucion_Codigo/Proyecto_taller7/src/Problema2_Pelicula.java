import java.util.ArrayList;

public class Problema2_Pelicula {
    public static void main(String[] args) {
        // Creo dos películas
        Pelicula pelicula1 = new Pelicula("Stitch", "Diego", 2025);
        Pelicula pelicula2 = new Pelicula("La monja", "Marcelo", 2024);

        // Prueba de VHS
        VHS vhs1 = new VHS("EN", pelicula1, 10.0);
        System.out.println("VHS -> " + vhs1);

        // Prueba de DVD
        ArrayList<Pelicula> lista = new ArrayList<>();
        lista.add(pelicula1);
        lista.add(pelicula2);
        String[] idiomas = { "ES", "EN" };
        DVD dvd1 = new DVD(idiomas, lista, 12.0, 4.7);  // precio base 12.0, capacidad 4.7 GB
        System.out.println("DVD antes de incrementar precio -> " + dvd1);

        // Aplico el incremento del 10 %
        dvd1.calcularCostoAlq();
        System.out.println("DVD después de incrementar precio -> " + dvd1);
    }
}

class SoportePelicula {
    protected double precioAlq;

    public SoportePelicula(double precioAlq) {
        this.precioAlq = precioAlq;
    }

    @Override
    public String toString() {
        return String.format("precioAlq=%.2f", precioAlq);
    }
}

class DVD extends SoportePelicula {
    private String[] idiomas;
    private ArrayList<Pelicula> listaPeliculas;
    private double capacidadGb;  // capacidad en Gigabytes

    public DVD(String[] idiomas, ArrayList<Pelicula> listaPeliculas, double precioAlq, double capacidadGb) {
        super(precioAlq);
        this.idiomas = idiomas;
        this.listaPeliculas = listaPeliculas;
        this.capacidadGb = capacidadGb;
    }

    /** Incrementa el precio de alquiler en un 10 %. */
    public void calcularCostoAlq() {
        this.precioAlq *= 1.10;
    }

    @Override
    public String toString() {
        return String.format(
            "DVD{capacidad=%.1fGB, idiomas=%s, peliculas=%s, %s}",
            capacidadGb,
            String.join("/", idiomas),
            listaPeliculas,
            super.toString()
        );
    }
}

class VHS extends SoportePelicula {
    private String idioma;
    private Pelicula pelicula;

    public VHS(String idioma, Pelicula pelicula, double precioAlq) {
        super(precioAlq);
        this.idioma = idioma;
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return String.format("VHS{idioma=%s, pelicula=%s, %s}",
            idioma, pelicula, super.toString()
        );
    }
}

class Pelicula {
    private String titulo;
    private String autor;
    private int anioEdicion;

    public Pelicula(String titulo, String autor, int anioEdicion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) by %s", titulo, anioEdicion, autor);
    }
}
