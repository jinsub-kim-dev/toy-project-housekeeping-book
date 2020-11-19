package com.jinsub.housekeeping.api.transaction.model.dto;

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
public class UpdateTransactionRequestDto implements Serializable {
    private static final long serialVersionUID = -5456510586839312126L;

    private long transactionId;
    private TransactionType transactionType;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime transactionDate;
    private AssetType assetType;
    private long amountOfMoney;
    private String details;
}
