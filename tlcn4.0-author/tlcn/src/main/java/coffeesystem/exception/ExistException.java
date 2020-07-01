package coffeesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ExistException(String message) {
        super(message);
    }
}