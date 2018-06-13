package auth;

/**
 * Simple authorization class
 */
public class Auth implements AuthService{
    final private String standardUsername;
    final private String standardPassword;

    public Auth(String standardUsername, String standardPassword) {
        this.standardUsername = standardUsername;
        this.standardPassword = standardPassword;
    }

    @Override
    public Boolean check(String username, String password) {
        return standardUsername.equals(username) && standardPassword.equals(password);
    }
}
