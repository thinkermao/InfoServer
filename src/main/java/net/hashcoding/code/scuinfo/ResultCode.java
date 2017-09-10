package net.hashcoding.code.scuinfo;

public enum ResultCode {
    SUCCESS(200, "ok"),
    PARAMETER_ERROR(400, "parameters error"),
    NOT_FOUND(404, "not found"),
    INTERNAL_ERROR(500, "internal server error");

    private final Integer status;
    private final String message;

    ResultCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    String getMessage() {
        return message;
    }

    Integer getStatus() {
        return status;
    }
}
