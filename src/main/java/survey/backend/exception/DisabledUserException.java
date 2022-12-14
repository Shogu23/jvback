package survey.backend.exception;

import java.io.Serial;

public class DisabledUserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DisabledUserException(String msg) {
        super(msg);
    }
}
