package com.jinsub.housekeeping.base.exception.model;

import com.jinsub.housekeeping.base.exception.enums.HouseKeepingErrorType;

public class HouseKeepingException extends RuntimeException {
    private static final long serialVersionUID = 4661866140662451627L;

    private HouseKeepingErrorType errorType;

    public HouseKeepingException(HouseKeepingErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public HouseKeepingException(HouseKeepingErrorType errorType, Throwable throwable) {
        super(throwable);
        this.errorType = errorType;
    }

    public HouseKeepingException(HouseKeepingErrorType errorType, String message, Throwable throwable) {
        super(message, throwable);
        this.errorType = errorType;
    }

    public HouseKeepingErrorType getErrorType() {
        return this.errorType;
    }
}
