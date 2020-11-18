package com.jinsub.housekeeping.api.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadTransactionResponseDto implements Serializable {
    private static final long serialVersionUID = 8568066957216509639L;

    private long transactionId;
    private long userId;
    private TransactionType transactionType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime transactionDate;
    private AssetType assetType;
    private long categoryId;
    private long amountOfMoney;
    private String details;

    public static ReadTransactionResponseDto of(Transaction transaction) {
        return ReadTransactionResponseDto.builder()
                .transactionId(transaction.getTransactionId())
                .userId(transaction.getUser().getUserId())
                .transactionType(transaction.getTransactionType())
                .transactionDate(transaction.getTransactionDate())
                .assetType(transaction.getAssetType())
                .categoryId(transaction.getCategory().getCategoryId())
                .amountOfMoney(transaction.getAmountOfMoney())
                .details(transaction.getDetails())
                .build();
    }
}
