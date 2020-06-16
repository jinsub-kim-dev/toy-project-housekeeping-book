package com.jinsub.housekeeping.api.transaction.enums;

public enum AssetType {
    CASH(0),    // 현금
    CARD(1);    // 카드

    int value;

    AssetType(int value) {
        this.value = value;
    }

    public int toValue() {
        return this.value;
    }

    public AssetType of(int typeValue) {
        for (AssetType assetType : values()) {
            if (assetType.value == typeValue) {
                return assetType;
            }
        }

        throw new IllegalArgumentException("invalid AssetType : " + typeValue);
    }
}
