package com.jinsub.housekeeping.api.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequestDto implements Serializable {
    private static final long serialVersionUID = 9066674283595242712L;

    private long userId;
    private TransactionType transactionType;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime transactionDate;
    private AssetType assetType;
    private long categoryId;
    private long amountOfMoney;
    private String details;
}
