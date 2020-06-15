package com.jinsub.housekeeping.api.category.model.entity;

import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.base.converter.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column
    private String categoryName;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column(name = "common_flag")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean common;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;
}
