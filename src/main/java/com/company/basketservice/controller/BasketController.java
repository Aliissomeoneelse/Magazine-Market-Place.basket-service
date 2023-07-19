package com.company.basketservice.controller;


import com.company.basketservice.dto.BasketDto;
import com.company.basketservice.dto.ResponseDto;
import com.company.basketservice.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("basket")
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/create")
    @Operation(
            tags = "Basket"
    )
    public ResponseDto<BasketDto> create(@RequestBody BasketDto dto){
        return basketService.create(dto);
    }

    @GetMapping(value = ("/get-all"))
    @Operation(
            tags = "Basket"
    )
    public ResponseDto<Page<BasketDto>> getAll(@RequestParam Map<String, String> params) {
        return basketService.getAll(params);
    }

    @GetMapping("/get")
    @Operation(
            tags = "Basket"
    )
    public ResponseDto<BasketDto> get(@RequestParam Integer id){
        return basketService.get(id);
    }

    @PutMapping("/update")
    @Operation(
            tags = "Basket"
    )
    public ResponseDto<BasketDto> update(@RequestParam Integer id, @RequestBody BasketDto dto){
        return basketService.update(id,dto);
    }

    @DeleteMapping("/delete")
    @Operation(
            tags = "Basket"
    )
    public ResponseDto<BasketDto> delete(@RequestParam Integer id){
        return basketService.delete(id);
    }
}