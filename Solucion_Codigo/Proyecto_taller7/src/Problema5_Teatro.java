
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problema5_Teatro {

    public static void main(String[] args) {
        GestorEntradas gestor = new GestorEntradas();

        // Ventas de prueba
        gestor.vendeEntrada("Principal",  "Ana",   "normal");
        gestor.vendeEntrada("Central",    "Luis",  "reducido");
        gestor.vendeEntrada("PalcoB",     "María", "abonado");
        gestor.vendeEntrada("Lateral",    "José",  "normal");
        gestor.vendeEntrada("VIP",        "Eva",   "normal");   // zona no existe

        // Consultas de prueba
        gestor.consultaEntrada(1);
        gestor.consultaEntrada(3);
        gestor.consultaEntrada(10);  // id no existente
    }
}

// -------------------- Clase base Zona --------------------
class Zona {
    public String nombre;
    public int capacidadTotal;
    public int asientosVendidos;
    public double precioNormal;
    public double precioAbonado;

    public Zona() { }

    public Zona(String nombre, int capacidad, double precioNormal, double precioAbonado) {
        this.nombre           = nombre;
        this.capacidadTotal   = capacidad;
        this.asientosVendidos = 0;
        this.precioNormal     = precioNormal;
        this.precioAbonado    = precioAbonado;
    }

    public boolean estaCompleta() {
        return asientosVendidos >= capacidadTotal;
    }

    public boolean venderAsiento() {
        if (!estaCompleta()) {
            asientosVendidos++;
            return true;
        }
        return false;
    }
}

// -------------------- Subclases de Zona --------------------
class Principal extends Zona {
    public Principal() {
        super("Principal", 200, 25.0, 17.5);
    }
}

class PalcoB extends Zona {
    public PalcoB() {
        super("PalcoB", 40, 70.0, 40.0);
    }
}

class Central extends Zona {
    public Central() {
        super("Central", 400, 20.0, 14.0);
    }
}

class Lateral extends Zona {
    public Lateral() {
        super("Lateral", 100, 15.5, 10.0);
    }
}

// -------------------- Clase Entrada --------------------
class Entrada {
    public int id;
    public Zona zona;
    public String espectador;
    public String tipo;   // "normal", "abonado" o "reducido"
    public double precio;

    public Entrada() { }

    public Entrada(int id, Zona zona, String espectador, String tipo, double precio) {
        this.id         = id;
        this.zona       = zona;
        this.espectador = espectador;
        this.tipo       = tipo;
        this.precio     = precio;
    }

    @Override
    public String toString() {
        return String.format(
            "Entrada{id=%d, zona=%s, espectador=%s, tipo=%s, precio=%.2f}",
            id, zona.nombre, espectador, tipo, precio
        );
    }
}

// -------------------- Gestor de ventas y consultas (métodos renombrados) --------------------
class GestorEntradas {
    private List<Zona> zonas = new ArrayList<>();
    private Map<Integer, Entrada> entradas = new HashMap<>();
    private int siguienteId = 1;

    public GestorEntradas() {
        zonas.add(new Principal());
        zonas.add(new PalcoB());
        zonas.add(new Central());
        zonas.add(new Lateral());
    }

    /** Caso de uso “Vende entrada” */
    public Entrada vendeEntrada(String nombreZona, String espectador, String tipo) {
        Zona zona = buscarZona(nombreZona);
        if (zona == null) {
            System.out.println("Zona '" + nombreZona + "' no existe.");
            return null;
        }
        if (zona.estaCompleta()) {
            System.out.println("Zona '" + nombreZona + "' está completa.");
            return null;
        }

        double precio;
        if ("normal".equalsIgnoreCase(tipo)) {
            precio = zona.precioNormal;
        } else if ("abonado".equalsIgnoreCase(tipo)) {
            precio = zona.precioAbonado;
        } else if ("reducido".equalsIgnoreCase(tipo)) {
            precio = zona.precioNormal * 0.85;
        } else {
            System.out.println("Tipo de entrada inválido: " + tipo);
            return null;
        }

        zona.venderAsiento();
        Entrada e = new Entrada(siguienteId++, zona, espectador, tipo, precio);
        entradas.put(e.id, e);
        System.out.println("Venta exitosa -> " + e);
        return e;
    }

    /** Caso de uso “Consulta entrada” */
    public void consultaEntrada(int id) {
        Entrada e = entradas.get(id);
        if (e == null) {
            System.out.println("No existe entrada con id " + id + ".");
        } else {
            System.out.println("Consulta -> " + e);
        }
    }

    private Zona buscarZona(String nombre) {
        for (Zona z : zonas) {
            if (z.nombre.equalsIgnoreCase(nombre)) {
                return z;
            }
        }
        return null;
    }
}