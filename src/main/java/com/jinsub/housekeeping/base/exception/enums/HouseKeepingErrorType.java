package com.jinsub.housekeeping.base.exception.enums;

import lombok.Getter;

@Getter
public enum HouseKeepingErrorType {

    POLICY_VIOLATION_NOT_EXIST_USER(410, "policy.violation.not.exist.user"),
    POLICY_VIOLATION_ALREADY_EXIST_USER(411, "policy.violation.already.exist.user"),
    POLICY_VIOLATION_NOT_EXIST_CATEGORY(412, "policy.violation.not.exist.category"),
    POLICY_VIOLATION_ALREADY_EXIST_CATEGORY(413, "policy.violation.already.exist.category"),

    UNKNOWN(999, "unexpected.exception");

    int errorCode;
    String message;

    HouseKeepingErrorType(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
