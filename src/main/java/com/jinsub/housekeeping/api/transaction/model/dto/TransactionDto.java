package com.jinsub.housekeeping.api.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.jinsub.housekeeping.api.category.model.dto.CategoryDto;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.api.user.model.dto.UserDto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable {
    private static final long serialVersionUID = 1965866162378113596L;

    private long transactionId;
    private UserDto userDto;
    private TransactionType transactionType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime transactionDate;
    private AssetType assetType;
    private CategoryDto categoryDto;
    private long amountOfMoney;
    private String details;

    public static TransactionDto of(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .userDto(UserDto.of(transaction.getUser()))
                .transactionType(transaction.getTransactionType())
                .transactionDate(transaction.getTransactionDate())
                .assetType(transaction.getAssetType())
                .categoryDto(CategoryDto.of(transaction.getCategory()))
                .amountOfMoney(transaction.getAmountOfMoney())
                .details(transaction.getDetails())
                .build();
    }
}
