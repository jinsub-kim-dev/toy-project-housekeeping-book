package com.jinsub.housekeeping.base.exception.enums;

import lombok.Getter;

@Getter
public enum HouseKeepingErrorType {

    POLICY_VIOLATION_NOT_EXIST_USER(410, "policy.violation.not.exist.user"),

    UNKOWN(999, "unexpected.exception");

    int errorCode;
    String message;

    HouseKeepingErrorType(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
