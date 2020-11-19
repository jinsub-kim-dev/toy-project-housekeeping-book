package com.jinsub.housekeeping.api.transaction.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTransactionResponseDto implements Serializable {
    private static final long serialVersionUID = 6095321727013838428L;

    private TransactionDto transactionDto;
}
