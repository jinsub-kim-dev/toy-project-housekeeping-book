package com.jinsub.housekeeping.api.transaction.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadTransactionResponseDto implements Serializable {
    private static final long serialVersionUID = 8568066957216509639L;

    private TransactionDto transactionDto;
}
