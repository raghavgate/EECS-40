package Dictionary;

public class InvalidWordError extends RuntimeException{
    public InvalidWordError(String message) {
        super(message);
    }
}
