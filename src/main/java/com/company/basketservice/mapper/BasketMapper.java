package com.company.basketservice.mapper;


import com.company.basketservice.dto.BasketDto;
import com.company.basketservice.module.Basket;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public abstract class BasketMapper {

    public Basket toEntity(BasketDto dto) {
        Basket basket = new Basket();
        basket.setId(dto.getId());
        basket.setProdMass(dto.getProdMass());
        basket.setProdPrice(dto.getProdPrice());
        basket.setTotalPrice(dto.getTotalPrice());
        /*basket.setCreatedAt(dto.getCreatedAt());
        basket.setUpdatedAt(dto.getUpdatedAt());
        basket.setDeletedAt(dto.getDeletedAt());*/
        return basket;
    }

    public BasketDto toDto(Basket basket) {
        BasketDto dto = new BasketDto();
        dto.setId(basket.getId());
        dto.setProdMass(basket.getProdMass());
        dto.setProdPrice(basket.getProdPrice());
        dto.setTotalPrice(basket.getTotalPrice());
        /*dto.setCreatedAt(basket.getCreatedAt());
        dto.setUpdatedAt(basket.getUpdatedAt());
        dto.setDeletedAt(basket.getDeletedAt());*/
        return dto;
    }

    public Basket updateBasketFromDto(BasketDto dto, Basket basket) {
        if (dto == null) {
            return basket;
        }
        if (dto.getId() != null) {
            basket.setId(dto.getId());
        }
        if (dto.getProdMass() != null) {
            basket.setProdMass(dto.getProdMass());
        }
        if (dto.getProdPrice() != null) {
            basket.setProdPrice(dto.getProdPrice());
        }
        if (dto.getTotalPrice() != null) {
            basket.setTotalPrice(dto.getTotalPrice());
        }
        if (dto.getCreatedAt() != null) {
            basket.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getUpdatedAt() != null) {
            basket.setUpdatedAt(dto.getUpdatedAt());
        }
        if (dto.getDeletedAt() != null) {
            basket.setDeletedAt(dto.getDeletedAt());
        }

        return basket;
    }
}