package net.hashcoding.code.scuinfo.Handler;

import net.hashcoding.code.scuinfo.ResultCode;

public class MessageException extends RuntimeException {
    private ResultCode code;
    private String detailMessage;

    public MessageException(ResultCode code, String message) {
        this.code = code;
        this.detailMessage = message;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String message) {
        this.detailMessage = message;
    }
}
