package com.jinsub.housekeeping.api.user.model.entity;

import com.jinsub.housekeeping.api.transaction.model.entity.Transaction;
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
@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactionList = new ArrayList<>();

    @Column
    private String userName;

    @Column
    private String hashedEmail;

    @Column
    private String hashedPassword;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;
}
