package exceptions;

public class ErrorResponse {
    private String message;
    private int errorCode;

    public ErrorResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
