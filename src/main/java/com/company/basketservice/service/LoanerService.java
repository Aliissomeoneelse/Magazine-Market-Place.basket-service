package com.company.basketservice.service;

import com.company.basketservice.dto.LoanerDto;
import com.company.basketservice.dto.ResponseDto;
import com.company.basketservice.mapper.LoanerMapper;
import com.company.basketservice.module.Loaner;
import com.company.basketservice.repository.LoanerRepository;
import com.company.basketservice.repository.LoanerRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanerService {

    private final LoanerMapper loanerMapper;
    private final LoanerRepository loanerRepository;
    private final LoanerRepositoryImpl loanerRepositoryImpl;

    public ResponseDto<LoanerDto> create(LoanerDto dto) {
        try {
            Loaner loaner = loanerMapper.toEntity(dto);
            loaner.setCreatedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("Loaner successful created!")
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<Page<LoanerDto>> getAll(Map<String, String> params) {
        Page<LoanerDto> basket = this.loanerRepositoryImpl
                .getLoaner(params)
                .map(this.loanerMapper::toDto);
        if (basket.isEmpty()) {
            return ResponseDto.<Page<LoanerDto>>builder()
                    .message("This params " + params + " are not found")
                    .build();
        }
        return ResponseDto.<Page<LoanerDto>>builder()
                .message("Ok")
                .success(true)
                .data(basket)
                .build();
    }

    public ResponseDto<LoanerDto> get(Integer id) {
        Optional<Loaner> optional = loanerRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<LoanerDto>builder()
                .success(true)
                .message("OK")
                .data(loanerMapper.toDto(optional.get()))
                .build();
    }

    public boolean getForValidation(Integer id) {
        Optional<Loaner> optional = loanerRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return true;
        }
        return false;
    }

    public ResponseDto<LoanerDto> update(Integer id, LoanerDto dto) {
        Optional<Loaner> optional = loanerRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Loaner loaner = loanerMapper.updateLoanerFromDto(dto, optional.get());
            loaner.setId(optional.get().getId());
            loaner.setUpdatedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("Loaner successful updated!")
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while updating error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<LoanerDto> delete(Integer id) {
        Optional<Loaner> optional = loanerRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Loaner loaner = optional.get();
            loaner.setDeletedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("Loaner successful deleted!")
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }
}

