package exceptions;

public class NoIdException extends RuntimeException{
    public NoIdException(String messsage){super(messsage);}
    public NoIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIdException(Throwable cause) {
        super(cause);
    }

    public NoIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
