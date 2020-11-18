package com.jinsub.housekeeping.api.transaction.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionResponseDto implements Serializable {
    private static final long serialVersionUID = 8281068684593336004L;

    private TransactionDto transactionDto;
}
