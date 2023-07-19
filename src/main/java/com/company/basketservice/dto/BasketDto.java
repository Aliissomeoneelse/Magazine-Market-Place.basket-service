package com.company.basketservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasketDto {

    private Integer id;
    @NotNull(message = "Prod mass cannot be null or empty")
    private Double prodMass;
    private Double prodPrice;
    private Double totalPrice;
    //private Set<ProductDto> products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}