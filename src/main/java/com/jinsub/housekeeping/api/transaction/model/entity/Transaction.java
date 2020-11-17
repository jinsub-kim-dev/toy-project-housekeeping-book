package com.jinsub.housekeeping.api.transaction.model.entity;

import com.jinsub.housekeeping.api.category.model.entity.Category;
import com.jinsub.housekeeping.api.transaction.enums.AssetType;
import com.jinsub.housekeeping.api.transaction.enums.TransactionType;
import com.jinsub.housekeeping.api.user.model.entity.User;
import com.jinsub.housekeeping.base.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column
    private LocalDateTime transactionDate;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private AssetType assetType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private long amountOfMoney;

    @Column
    private String details;

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getTransactionList().remove(this);
        }
        this.user = user;
        this.user.getTransactionList().add(this);
    }

    public void setCategory(Category category) {
        if (this.category != null) {
            this.category.getTransactionList().remove(this);
        }
        this.category = category;
        this.category.getTransactionList().add(this);
    }
}
