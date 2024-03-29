package lk.ijse.dep8.note.service.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    protected NotFoundException(Throwable cause) {
        super(cause);
    }
}
