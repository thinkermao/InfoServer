package net.hashcoding.code.scuinfo;

import net.hashcoding.code.scuinfo.Handler.MessageException;
import org.springframework.util.StringUtils;

public class ValidateUtils {
    public static void greatThan(String field, Long value, Long num) {
        if (value == null || num == null || value <= num) {
            failed(field, "should great than " + String.valueOf(num));
        }
    }

    public static void greatThan(String field, Integer value, Integer num) {
        if (value == null || num == null || value <= num) {
            failed(field, "should great than " + String.valueOf(num));
        }
    }

    public static void greatEqualThan(String field, Long value, Long num) {
        if (value == null || num == null || value < num) {
            failed(field, "should great equal than " + String.valueOf(num));
        }
    }

    public static void greatEqualThan(String field, Integer value, Integer num) {
        if (value == null || num == null || value < num) {
            failed(field, "should great equal than " + String.valueOf(num));
        }
    }

    public static void notEmpty(String field, String value) {
        if (StringUtils.isEmpty(value)) {
            failed(field, "is empty");
        }
    }

    public static void inRange(String field, String value, int begin, int end) {
        notEmpty(field, value);
        int size = value.length();
        if (size < begin || size > end) {
            failed(field, "out of length ["
                    + String.valueOf(begin) + ", "
                    + String.valueOf(end) + "]");
        }
    }

    public static void inRangeOrEmpty(String field, String value, int begin, int end) {
        if (StringUtils.isEmpty(value))
            return;
        inRange(field, value, begin, end);
    }

    private static void failed(String field, String message) {
        throw new MessageException(ResultCode.PARAMETER_ERROR,
                "`" + field + "`: " + message);
    }
}
