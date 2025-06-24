
import java.util.ArrayList;
public class Problema4_Trabajadores {
    public static void main(String[] args) {
        // 1) Creo un Jefe (no necesita jefe asignado, por eso pasa null)
        Jefe jefe1 = new Jefe(
            "Ana", "García", "Av. Siempre Viva 123", "1234567890",
            3000.0
        );

        // 2) Empleado fijo mensual
        FijoMensual fijo = new FijoMensual(
            "Carlos", "Pérez", "Calle Falsa 456", "0987654321",
            jefe1, 1500.0
        );

        // 3) Comisionista
        Comisionista com = new Comisionista(
            "Laura", "Quintero", "Av. Central 789", "1122334455",
            jefe1, 0.10
        );
        com.setVentasRealizadas(50000.0);  // por ejemplo S/ 50 000 en ventas

        // 4) Por horas
        PorHoras ph = new PorHoras(
            "Miguel", "López", "Pasaje 321", "6677889900",
            jefe1, 10.0, 15.0
        );
        ph.setHorasTrabajadas(45);  // 45 horas en el mes

        // 5) Armo lista de todos los trabajadores
        ArrayList<Trabajador> empleados = new ArrayList<>();
        empleados.add(jefe1);
        empleados.add(fijo);
        empleados.add(com);
        empleados.add(ph);

        // 6) Imprimo la nómina
        System.out.println("NÓMINA DEL MES\n==============");
        for (Trabajador t : empleados) {
            System.out.println(t); 
            System.out.printf("-> Sueldo a pagar: %.2f\n\n", t.calcularSueldo());
        }
    }
}

// -------------------- Clase padre --------------------
class Trabajador {
    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public Jefe jefe;

    public Trabajador() { }

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombre    = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni       = dni;
        this.jefe      = jefe;
    }

    /** Método sobrescribible para calcular el sueldo. */
    public double calcularSueldo() {
        return 0.0;
    }

    @Override
    public String toString() {
        String jefeStr = (jefe != null)
            ? jefe.nombre + " " + jefe.apellidos
            : "Ninguno";
        return String.format(
            "%s %s (DNI: %s)\nDir: %s\nJefe: %s",
            nombre, apellidos, dni,
            direccion,
            jefeStr
        );
    }
}

// -------------------- Jefe: cobra un sueldo fijo --------------------
class Jefe extends Trabajador {
    public double sueldoFijo;

    public Jefe() {
        super();
    }

    public Jefe(String nombre, String apellidos, String direccion, String dni, double sueldoFijo) {
        super(nombre, apellidos, direccion, dni, null);
        this.sueldoFijo = sueldoFijo;
    }

    @Override
    public double calcularSueldo() {
        return sueldoFijo;
    }

    @Override
    public String toString() {
        return String.format(
            "Jefe: %s %s (DNI: %s)\nDir: %s\nSueldo fijo: %.2f",
            nombre, apellidos, dni,
            direccion,
            sueldoFijo
        );
    }
}

// -------------------- Fijos Mensuales --------------------
class FijoMensual extends Trabajador {
    public double sueldoMensual;

    public FijoMensual() {
        super();
    }

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double sueldoMensual) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.sueldoMensual = sueldoMensual;
    }

    @Override
    public double calcularSueldo() {
        return sueldoMensual;
    }

    @Override
    public String toString() {
        return String.format(
            "FijoMensual: %s %s (DNI: %s)\nDir: %s\nSueldo mensual: %.2f\nJefe: %s %s",
            nombre, apellidos, dni,
            direccion,
            sueldoMensual,
            jefe.nombre, jefe.apellidos
        );
    }
}

// -------------------- Comisionistas --------------------
class Comisionista extends Trabajador {
    public double porcentaje;         // e.g. 0.10 para 10%
    public double ventasRealizadas;   // monto total de ventas

    public Comisionista() {
        super();
    }

    public Comisionista(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double porcentaje) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentaje = porcentaje;
        this.ventasRealizadas = 0.0;
    }

    public void setVentasRealizadas(double ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() {
        return porcentaje * ventasRealizadas;
    }

    @Override
    public String toString() {
        return String.format(
            "Comisionista: %s %s (DNI: %s)\nDir: %s\nVentas: %.2f, Comisión: %.2f%%\nJefe: %s %s",
            nombre, apellidos, dni,
            direccion,
            ventasRealizadas, porcentaje * 100,
            jefe.nombre, jefe.apellidos
        );
    }
}

// -------------------- Por Horas --------------------
class PorHoras extends Trabajador {
    public double precioHoraNormal;
    public double precioHoraExtra;
    public double horasTrabajadas;

    public PorHoras() {
        super();
    }

    public PorHoras(String nombre, String apellidos, String direccion, String dni, Jefe jefe,
                    double precioHoraNormal, double precioHoraExtra) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.precioHoraNormal = precioHoraNormal;
        this.precioHoraExtra  = precioHoraExtra;
        this.horasTrabajadas  = 0.0;
    }

    public void setHorasTrabajadas(double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        double normales = Math.min(horasTrabajadas, 40);
        double extras   = Math.max(0, horasTrabajadas - 40);
        return normales * precioHoraNormal
             + extras   * precioHoraExtra;
    }

    @Override
    public String toString() {
        return String.format(
            "PorHoras: %s %s (DNI: %s)\nDir: %s\nHoras trabajadas: %.1f (%.2f x normales, %.2f x extra)\nJefe: %s %s",
            nombre, apellidos, dni,
            direccion,
            horasTrabajadas, precioHoraNormal, precioHoraExtra,
            jefe.nombre, jefe.apellidos
        );
    }
}

