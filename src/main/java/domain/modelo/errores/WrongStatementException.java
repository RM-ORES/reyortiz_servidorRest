package domain.modelo.errores;

public class WrongStatementException extends RuntimeException{
    public WrongStatementException(String message) {
        super(message);
    }
}
