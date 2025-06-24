
import java.util.ArrayList;

public class Problema1_Libro {

    public static void main(String[] args) {
        // 1) Creo unas Palabras
        Palabra p1 = new Palabra("Este");
        Palabra p2 = new Palabra("es");
        Palabra p3 = new Palabra("un");
        Palabra p4 = new Palabra("ejemplo");

        // 2) Creo Sentencias con esas Palabras
        ArrayList<Palabra> listaPal1 = new ArrayList<>();
        listaPal1.add(p1);
        listaPal1.add(p2);
        listaPal1.add(p3);
        listaPal1.add(p4);
        Sentencia s1 = new Sentencia(listaPal1);

        ArrayList<Palabra> listaPal2 = new ArrayList<>();
        listaPal2.add(p4);
        listaPal2.add(p3);
        listaPal2.add(p2);
        listaPal2.add(p1);
        Sentencia s2 = new Sentencia(listaPal2);

        // 3) Creo un Párrafo con esas Sentencias
        ArrayList<Sentencia> sentencias = new ArrayList<>();
        sentencias.add(s1);
        sentencias.add(s2);
        Parrafo parrafo = new Parrafo(sentencias);

        // 4) Creo una Figura
        Figura figura = new Figura("Diagrama de clases");

        // 5) Armo la Sección y le agrego el Párrafo y la Figura
        Seccion seccion = new Seccion("Introducción");
        seccion.agregarComponente(parrafo);
        seccion.agregarComponente(figura);

        // 6) Armo el Capítulo
        Capitulo capitulo = new Capitulo("Jerarquía de clases de un capítulo");
        capitulo.agregarSeccion(seccion);

        // 7) Imprimo
        System.out.println(capitulo);
    }
}

// -------------------- Clase padre de todos los componentes de sección --------------------
class ComponenteCapitulo {

    public String etiqueta;

    public ComponenteCapitulo() {
    }

    public ComponenteCapitulo(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return "etiqueta=" + etiqueta;
    }
}

// -------------------- El Capítulo contiene varias Secciones --------------------
class Capitulo {

    public String titulo;
    public ArrayList<Seccion> secciones = new ArrayList<>();

    public Capitulo() {
    }

    public Capitulo(String titulo) {
        this.titulo = titulo;
    }

    public void agregarSeccion(Seccion s) {
        secciones.add(s);
    }

    @Override
    public String toString() {
        return "Capitulo{"
                + "titulo=" + titulo
                + ", secciones=" + secciones
                + '}';
    }
}

// -------------------- Sección extiende de ComponenteCapitulo --------------------
class Seccion extends ComponenteCapitulo {

    public String encabezado;
    public ArrayList<ComponenteCapitulo> componentes = new ArrayList<>();

    public Seccion() {
        super("Seccion");
    }

    public Seccion(String encabezado) {
        super("Seccion");
        this.encabezado = encabezado;
    }

    public void agregarComponente(ComponenteCapitulo comp) {
        componentes.add(comp);
    }

    @Override
    public String toString() {
        return "Seccion{"
                + "encabezado=" + encabezado
                + ", componentes=" + componentes
                + "} " + super.toString();
    }
}

// -------------------- Párrafo extiende de ComponenteCapitulo --------------------
class Parrafo extends ComponenteCapitulo {

    public ArrayList<Sentencia> sentencias = new ArrayList<>();

    public Parrafo() {
        super("Parrafo");
    }

    public Parrafo(ArrayList<Sentencia> sentencias) {
        super("Parrafo");
        this.sentencias = sentencias;
    }

    @Override
    public String toString() {
        return "Parrafo{"
                + "sentencias=" + sentencias
                + "} " + super.toString();
    }
}

// -------------------- Figura extiende de ComponenteCapitulo --------------------
class Figura extends ComponenteCapitulo {

    public String descripcion;

    public Figura() {
        super("Figura");
    }

    public Figura(String descripcion) {
        super("Figura");
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Figura{"
                + "descripcion=" + descripcion
                + "} " + super.toString();
    }
}

// -------------------- Sentencia (no hereda de ComponenteCapitulo) --------------------
class Sentencia {

    public ArrayList<Palabra> palabras = new ArrayList<>();

    public Sentencia() {
    }

    public Sentencia(ArrayList<Palabra> palabras) {
        this.palabras = palabras;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Palabra p : palabras) {
            sb.append(p.texto).append(" ");
        }
        return sb.toString().trim() + ".";
    }
}

// -------------------- Palabra --------------------
class Palabra {

    public String texto;

    public Palabra() {
    }

    public Palabra(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}
