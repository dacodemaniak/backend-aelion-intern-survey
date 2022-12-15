package survey.backend.error.jwt;

import javax.naming.AuthenticationException;

public class JwtTokenMissingException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public JwtTokenMissingException() {
        super("Token was missing");
    }
}
