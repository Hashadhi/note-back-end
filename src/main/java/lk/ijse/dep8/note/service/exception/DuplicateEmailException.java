package lk.ijse.dep8.note.service.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    protected DuplicateEmailException(Throwable cause) {
        super(cause);
    }


}
