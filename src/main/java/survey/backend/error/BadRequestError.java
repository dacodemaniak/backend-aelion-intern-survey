package survey.backend.error;

public class BadRequestError extends RuntimeException {
    public BadRequestError(String message) {
        super(message);
    }

    public static BadRequestError withNoArgs(String itemType) {
        return new BadRequestError(itemType);
    }
}
