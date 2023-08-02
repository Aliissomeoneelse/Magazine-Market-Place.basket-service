package com.company.basketservice.controller;


import com.company.basketservice.client.UserClient;
import com.company.basketservice.dto.LoanerDto;
import com.company.basketservice.dto.ResponseDto;
import com.company.basketservice.dto.TestResponse;
import com.company.basketservice.service.LoanerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("loaner")
public class LoanerController {

    private final LoanerService loanerService;
    private final UserClient userClient;


    @PostMapping("/create")
    @Operation(
            tags = "Loaner"
    )
    public ResponseDto<LoanerDto> create(@RequestBody LoanerDto dto){
        return loanerService.create(dto);
    }

    @GetMapping(value = ("/get-all"))
    @Operation(
            tags = "Loaner"
    )
    public ResponseDto<Page<LoanerDto>> getAll(@RequestParam Map<String, String> params) {
        return loanerService.getAll(params);
    }

    @GetMapping("/get/{id}")
    @Operation(
            tags = "Loaner"
    )
    public ResponseDto<LoanerDto> get(@PathVariable("id") Integer id){
        return loanerService.get(id);
    }

    @PutMapping("/update/{id}")
    @Operation(
            tags = "Loaner"
    )
    public ResponseDto<LoanerDto> update(@PathVariable("id") Integer id, @RequestBody LoanerDto dto){
        return loanerService.update(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            tags = "Loaner"
    )
    public ResponseDto<LoanerDto> delete(@PathVariable("id") Integer id){
        return loanerService.delete(id);
    }

    @GetMapping("/get-loaners-by-user/{id}")
    public ResponseDto<Set<LoanerDto>> getLoanersByUserId(@PathVariable("id") Integer id){
        return loanerService.getLoanersByUserId(id);
    }

    @GetMapping("/")
    public TestResponse getValue(){
        return this.userClient.getValue();
    }
}