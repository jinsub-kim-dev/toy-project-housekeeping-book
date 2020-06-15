package com.jinsub.housekeeping.api.transaction.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TransactionType {
    INCOME(0),  // 수입
    EXPENSE(1); // 지출

    int value;

    TransactionType(int value) {
        this.value = value;
    }

    public int toValue() {
        return this.value;
    }

    @JsonCreator
    public TransactionType of(int typeValue) {
        for (TransactionType transactionType : values()) {
            if (transactionType.value == typeValue) {
                return transactionType;
            }
        }

        throw new IllegalArgumentException("invalid TransactionType : " + typeValue);
    }
}
