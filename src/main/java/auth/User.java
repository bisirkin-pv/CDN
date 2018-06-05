package auth;

public class User {
    private static String _sessionId = "";

    public static void setSessionId(String sessionId){
        _sessionId = sessionId;
    }

    public static String getSessionId(){
        return _sessionId;
    }
}
