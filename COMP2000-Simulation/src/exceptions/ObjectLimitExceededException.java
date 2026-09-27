package exceptions;

public class ObjectLimitExceededException extends RuntimeException {
    public ObjectLimitExceededException() {
        super();
    }

    public ObjectLimitExceededException(String message) {
        super(message);
    }
}
