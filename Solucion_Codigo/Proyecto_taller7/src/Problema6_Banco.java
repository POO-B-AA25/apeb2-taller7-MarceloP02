
public class Problema6_Banco {
    public static void main(String[] args) {
        // 1) Cuenta de Cheques: puede quedar con saldo negativo
        Cheques cuentaCheques = new Cheques("001", "Alicia");
        cuentaCheques.depositar(500.0);
        cuentaCheques.retirar(600.0);   // permite sobregiro
        System.out.println(cuentaCheques);
        System.out.printf("Balance final: %.2f\n\n", cuentaCheques.getBalance());

        // 2) Cuenta de Ahorros: no permite sobregiro, interés 5%
        Ahorros cuentaAhorros = new Ahorros("002", "Benjamín", 0.05);
        cuentaAhorros.depositar(1_000.0);
        cuentaAhorros.retirar(200.0);
        cuentaAhorros.retirar(900.0);   // falla por saldo insuficiente
        cuentaAhorros.calcularInteres(); // suma 5% al balance
        System.out.println(cuentaAhorros);
        System.out.printf("Balance final: %.2f\n\n", cuentaAhorros.getBalance());

        // 3) Cuenta Platino: permite sobregiro, interés 10%
        Platino cuentaPlatino = new Platino("003", "Carolina");
        cuentaPlatino.depositar(800.0);
        cuentaPlatino.retirar(1_500.0);  // sobregiro permitido
        cuentaPlatino.calcularInteres(); // suma 10% al (posible) balance negativo
        System.out.println(cuentaPlatino);
        System.out.printf("Balance final: %.2f\n", cuentaPlatino.getBalance());
    }
}

// -------------------- Clase padre Cuenta --------------------
class Cuenta {
    protected String numeroCuenta;
    protected String nombreCliente;
    protected double balance;

    public Cuenta() { }

    public Cuenta(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta  = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance       = 0.0;
    }

    public void depositar(double cantidad) {
        balance += cantidad;
    }

    /**  
     * En la clase base permite sobregiro.  
     */
    public void retirar(double cantidad) {
        balance -= cantidad;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format(
            "%s{cuenta=%s, cliente=%s, balance=%.2f}",
            this.getClass().getSimpleName(),
            numeroCuenta, nombreCliente, balance
        );
    }
}

// -------------------- Cuenta de Cheques --------------------
class Cheques extends Cuenta {
    public Cheques() {
        super();
    }

    public Cheques(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }
    // usa retirar() de la clase base (permite saldo < 0)
}

// -------------------- Cuenta de Ahorros --------------------
class Ahorros extends Cuenta {
    private double tasaInteres;

    public Ahorros() {
        super();
    }

    /**  
     * @param numeroCuenta  
     * @param nombreCliente  
     * @param tasaInteres    tasa mensual (e.g. 0.05 → 5%)  
     */
    public Ahorros(String numeroCuenta, String nombreCliente, double tasaInteres) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = tasaInteres;
    }

    /**  
     * No permite sobregiro: sólo si hay saldo suficiente.  
     */
    @Override
    public void retirar(double cantidad) {
        if (cantidad <= balance) {
            balance -= cantidad;
        } else {
            System.out.println(
                "Retiro fallido en Ahorros (saldo insuficiente): " +
                cantidad + " > " + balance
            );
        }
    }

    /**  
     * Calcula el interés y lo suma al balance.  
     */
    public void calcularInteres() {
        double interes = balance * tasaInteres;
        balance += interes;
    }
}

// -------------------- Cuenta Platino --------------------
class Platino extends Cuenta {
    private double tasaInteres;

    public Platino() {
        super();
        this.tasaInteres = 0.10;
    }

    public Platino(String numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
        this.tasaInteres = 0.10;
    }

    /**  
     * Igual que en base, permite sobregiro.  
     */
    // retirar() heredado de Cuenta

    /**  
     * Interés fijo del 10 %, sin cargos ni penalizaciones.  
     */
    public void calcularInteres() {
        double interes = balance * tasaInteres;
        balance += interes;
    }
}

