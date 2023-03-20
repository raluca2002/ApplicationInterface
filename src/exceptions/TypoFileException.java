package exceptions;

public class TypoFileException extends RuntimeException{
    public TypoFileException() {
    }
    public TypoFileException(String messsage){super(messsage);}
    public TypoFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypoFileException(Throwable cause) {
        super(cause);
    }

    public TypoFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
