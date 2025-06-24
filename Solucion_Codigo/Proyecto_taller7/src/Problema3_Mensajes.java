
public class Problema3_Mensajes {

    public static void main(String[] args) {
        // 1) SMS con solo números
        SMS sms1 = new SMS("¡Hola!", "1234567890", "0987654321");
        System.out.println(sms1);
        sms1.enviarMensaje();
        sms1.visualizarMensaje();

        // 2) SMS por defecto
        SMS sms2 = new SMS();
        System.out.println(sms2);

        // 3) SMS con nombres y números
        SMS sms3 = new SMS(
                "Buenos días",
                "1234567890", "Juan Pérez",
                "0987654321", "María López"
        );
        System.out.println(sms3);
        sms3.enviarMensaje();
        sms3.visualizarMensaje();

        // 4) MMS con solo números
        MMS mms1 = new MMS("foto.png", "555000123", "666000456");
        System.out.println(mms1);
        mms1.enviarMensaje();
        mms1.visualizarMensaje();

        // 5) MMS por defecto
        MMS mms2 = new MMS();
        System.out.println(mms2);

        // 6) MMS con nombres y números
        MMS mms3 = new MMS(
                "video.mp4",
                "555000123", "Ana Torres",
                "666000456", "Luis García"
        );
        System.out.println(mms3);
        mms3.enviarMensaje();
        mms3.visualizarMensaje();
    }
}

class Mensaje {

    public String numeroRemitente;
    public String numeroDestinatario;
    public String nombreRemitente;
    public String nombreDestinatario;

    public Mensaje() {
    }

    public Mensaje(String numeroRemitente, String numeroDestinatario) {
        this.numeroRemitente = numeroRemitente;
        this.numeroDestinatario = numeroDestinatario;
    }

    public Mensaje(
            String numeroRemitente, String nombreRemitente,
            String numeroDestinatario, String nombreDestinatario
    ) {
        this.numeroRemitente = numeroRemitente;
        this.nombreRemitente = nombreRemitente;
        this.numeroDestinatario = numeroDestinatario;
        this.nombreDestinatario = nombreDestinatario;
    }

    public void enviarMensaje() {
        System.out.println("Enviando mensaje de "
                + numeroRemitente + " a " + numeroDestinatario);
    }

    public void visualizarMensaje() {
        System.out.println("Mensaje de "
                + numeroRemitente + " a " + numeroDestinatario);
    }

    @Override
    public String toString() {
        return "Mensaje{"
                + "numRemitente='" + numeroRemitente + '\''
                + ", numDestinatario='" + numeroDestinatario + '\''
                + (nombreRemitente != null ? ", nombreRemitente='" + nombreRemitente + '\'' : "")
                + (nombreDestinatario != null ? ", nombreDestinatario='" + nombreDestinatario + '\'' : "")
                + '}';
    }
}

class SMS extends Mensaje {

    public String texto;

    public SMS() {
        super();
    }

    public SMS(String texto, String numeroRemitente, String numeroDestinatario) {
        super(numeroRemitente, numeroDestinatario);
        this.texto = texto;
    }

    public SMS(
            String texto,
            String numeroRemitente, String nombreRemitente,
            String numeroDestinatario, String nombreDestinatario
    ) {
        super(numeroRemitente, nombreRemitente, numeroDestinatario, nombreDestinatario);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando SMS de "
                + numeroRemitente + " a " + numeroDestinatario);
    }

    @Override
    public void visualizarMensaje() {
        String rem = nombreRemitente != null ? nombreRemitente : numeroRemitente;
        String dest = nombreDestinatario != null ? nombreDestinatario : numeroDestinatario;
        System.out.println("SMS de " + rem + " a " + dest + ": " + texto);
    }

    @Override
    public String toString() {
        return "SMS{"
                + "texto='" + texto + '\''
                + "} " + super.toString();
    }
}

class MMS extends Mensaje {

    public String nombreFichero;

    public MMS() {
        super();
    }

    public MMS(String nombreFichero, String numeroRemitente, String numeroDestinatario) {
        super(numeroRemitente, numeroDestinatario);
        this.nombreFichero = nombreFichero;
    }

    public MMS(
            String nombreFichero,
            String numeroRemitente, String nombreRemitente,
            String numeroDestinatario, String nombreDestinatario
    ) {
        super(numeroRemitente, nombreRemitente, numeroDestinatario, nombreDestinatario);
        this.nombreFichero = nombreFichero;
    }

    @Override
    public void enviarMensaje() {
        System.out.println("Enviando MMS de "
                + numeroRemitente + " a " + numeroDestinatario);
    }

    @Override
    public void visualizarMensaje() {
        String rem = nombreRemitente != null ? nombreRemitente : numeroRemitente;
        String dest = nombreDestinatario != null ? nombreDestinatario : numeroDestinatario;
        System.out.println("MMS de " + rem + " a " + dest + ": archivo " + nombreFichero);
    }

    @Override
    public String toString() {
        return "MMS{"
                + "nombreFichero='" + nombreFichero + '\''
                + "} " + super.toString();
    }
}
