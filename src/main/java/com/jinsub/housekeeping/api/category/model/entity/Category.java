package com.jinsub.housekeeping.api.category.model.entity;

import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
import com.jinsub.housekeeping.base.converter.BooleanToYNConverter;
import com.jinsub.housekeeping.base.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_category")
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactionList = new ArrayList<>();

    @Column
    private String categoryName;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column(name = "common_flag")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean common;
}
