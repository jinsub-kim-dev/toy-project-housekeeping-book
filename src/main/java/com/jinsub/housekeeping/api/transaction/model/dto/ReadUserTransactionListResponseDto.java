package com.jinsub.housekeeping.api.transaction.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserTransactionListResponseDto implements Serializable {
    private static final long serialVersionUID = -3308797364525231872L;

    private List<TransactionDto> transactionDtoList;
}
