package net.hashcoding.code.scuinfo;

public class Result<T> {

    private Integer code;
    private String message;
    private String detailMessage;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(ResultCode.SUCCESS.getStatus());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setDetailMessage("");
        return result;
    }

    public static <T> Result<T> error(ResultCode code, Exception exception) {
        Result<T> result = new Result<>();
        result.setData(null);
        result.setCode(code.getStatus());
        result.setMessage(code.getMessage());
        result.setDetailMessage(exception.getMessage());
        return result;
    }

    public static <T> Result<T> error(ResultCode code, String detailMessage) {
        Result<T> result = new Result<>();
        result.setData(null);
        result.setCode(code.getStatus());
        result.setMessage(code.getMessage());
        result.setDetailMessage(detailMessage);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
