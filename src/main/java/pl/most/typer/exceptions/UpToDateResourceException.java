package pl.most.typer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UpToDateResourceException extends ResourceException {
    public UpToDateResourceException() {
    }

    public UpToDateResourceException(String message) {
        super(message);
    }

    public UpToDateResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpToDateResourceException(Throwable cause) {
        super(cause);
    }

    public UpToDateResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
