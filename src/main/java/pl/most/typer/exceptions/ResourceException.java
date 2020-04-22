package pl.most.typer.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ResourceException extends RuntimeException {

    public String resource;
    public String issue;

    public ResourceException() {
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }

    public ResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
