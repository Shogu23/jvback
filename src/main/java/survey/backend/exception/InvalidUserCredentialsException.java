package survey.backend.exception;

import java.io.Serial;

public class InvalidUserCredentialsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidUserCredentialsException(String msg) {
        super(msg);
    }
}
