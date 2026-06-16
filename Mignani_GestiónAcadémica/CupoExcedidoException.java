import java.lang.Exception;

public class CupoExcedidoException extends Exception { // Hereda de Exception (es Checked)
    public CupoExcedidoException(String mensaje) {
        super(mensaje); // Pasa el mensaje descriptivo a la superclase Throwable
    }
}
