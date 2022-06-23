package lk.ijse.dep8.note.service.exception;

public class UnAuthorizedAccessException extends RuntimeException {

    public UnAuthorizedAccessException(String message) {
        super(message);
    }

    public UnAuthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    protected UnAuthorizedAccessException(Throwable cause) {
        super(cause);
    }
}
