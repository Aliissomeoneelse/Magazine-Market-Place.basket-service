package com.company.basketservice.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(generator = "basket_seq")
    @SequenceGenerator(name = "basket_seq", sequenceName = "basket_seq", allocationSize = 1)
    private Integer id;
    @Column(name="prod_mass")
    private Double prodMass;
    @Column(name="prod_price")
    private Double prodPrice;
    @Column(name="total_price")
    private Double totalPrice;

    //@OneToMany(mappedBy = "basketId")
    //private Set<Product> products;

    @Column(name = "loaner_id")
    private Integer loanerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}