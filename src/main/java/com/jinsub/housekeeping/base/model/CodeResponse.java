package com.jinsub.housekeeping.base.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeResponse implements Serializable {

    private static final long serialVersionUID = -2137205367153992339L;
    public final static int SUCCESS_CODE = 0;
    public final static int FAIL_CODE = 500;

    @JsonProperty(value = "code")
    private int code = SUCCESS_CODE;
    @JsonProperty(value = "message")
    private String message;
    @JsonProperty(value = "result")
    private Object result;

    public static CodeResponse success() {
        return CodeResponse.builder().code(CodeResponse.SUCCESS_CODE).build();
    }

    public static CodeResponse successResult(Object result) {
        return CodeResponse.builder().code(CodeResponse.SUCCESS_CODE).result(result).build();
    }

    public static CodeResponse successMessage(String message) {
        return CodeResponse.builder().code(CodeResponse.SUCCESS_CODE).message(message).build();
    }

    public static CodeResponse successMessageResult(String message, Object result) {
        return CodeResponse.builder().code(SUCCESS_CODE).message(message).result(result).build();
    }

    public static CodeResponse fail() {
        return CodeResponse.builder().code(FAIL_CODE).build();
    }

    public static CodeResponse failCodeMessage(int code, String message) {
        return CodeResponse.builder().code(code).message(message).build();
    }
}
