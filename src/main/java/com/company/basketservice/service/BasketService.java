package com.company.basketservice.service;

import com.company.basketservice.dto.BasketDto;
import com.company.basketservice.dto.ResponseDto;
import com.company.basketservice.mapper.BasketMapper;
import com.company.basketservice.module.Basket;
import com.company.basketservice.repository.BasketRepository;
import com.company.basketservice.repository.BasketRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketMapper basketMapper;
    private final BasketRepository basketRepository;
    private final BasketRepositoryImpl basketRepositoryImpl;

    public ResponseDto<BasketDto> create(BasketDto dto) {
        try {
            Basket basket = basketMapper.toEntity(dto);
            basket.setCreatedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Basket successful created!")
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<BasketDto> get(Integer id) {
        Optional<Basket> optional = basketRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<BasketDto>builder()
                .success(true)
                .message("OK")
                .data(basketMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<Page<BasketDto>> getAll(Map<String, String> params) {
        Page<BasketDto> basket = this.basketRepositoryImpl
                .getBasket(params)
                .map(this.basketMapper::toDto);
        if (basket.isEmpty()) {
            return ResponseDto.<Page<BasketDto>>builder()
                    .message("This params " + params + " are not found")
                    .build();
        }
        return ResponseDto.<Page<BasketDto>>builder()
                .message("Ok")
                .success(true)
                .data(basket)
                .build();
    }

    public ResponseDto<BasketDto> update(Integer id, BasketDto dto) {
        Optional<Basket> optional = basketRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Basket basket = basketMapper.updateBasketFromDto(dto, optional.get());
            basket.setId(optional.get().getId());
            basket.setUpdatedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Basket successful updated!")
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while updating error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }


    public ResponseDto<BasketDto> delete(Integer id) {
        Optional<Basket> optional = basketRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Basket basket = optional.get();
            basket.setId(optional.get().getId());
            basket.setDeletedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Basket successful deleted!")
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

}
