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
public class LoanerDto {
    private Integer id;
    private Set<BasketDto> baskets;
    @NotNull(message = "Total price can not ne null")
    private Double totalPrice;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
