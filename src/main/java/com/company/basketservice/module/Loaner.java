package com.company.basketservice.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "loaner")
public class Loaner {
    @Id
    @GeneratedValue(generator = "loaner_seq")
    @SequenceGenerator(name = "loaner_seq",sequenceName = "loaner_seq",allocationSize = 1)
    private Integer id;

    @OneToMany(mappedBy = "loanerId")
    private Set<Basket> baskets;

    @Column(name="total_price")
    private Double totalPrice;
    private Boolean status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}