package exception;

public class RacingGameException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public RacingGameException(ExceptionCode exceptionCode) {
        super(exceptionCode.getErrorMsg());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
