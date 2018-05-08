package datacore;

public class RequestStatus {
    private final int status;
    private final String message;

    public RequestStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
