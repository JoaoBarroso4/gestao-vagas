package portfolio.joaom.gestaovagas.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Username or email already in use");
    }
}
