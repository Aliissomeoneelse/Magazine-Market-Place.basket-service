package com.company.basketservice.mapper;



import com.company.basketservice.dto.LoanerDto;
import com.company.basketservice.module.Loaner;
import org.springframework.stereotype.Component;

@Component
public abstract class LoanerMapper {

    public Loaner toEntity(LoanerDto dto) {
        Loaner loaner = new Loaner();
        loaner.setId(dto.getId());
        loaner.setTotalPrice(dto.getTotalPrice());
        loaner.setStatus(dto.getStatus());
        loaner.setCreatedAt(dto.getCreatedAt());
        loaner.setUpdatedAt(dto.getUpdatedAt());
        loaner.setDeletedAt(dto.getDeletedAt());
        return loaner;
    }

    public LoanerDto toDto(Loaner loaner) {
        LoanerDto dto = new LoanerDto();
        dto.setId(loaner.getId());
        dto.setTotalPrice(loaner.getTotalPrice());
        dto.setStatus(loaner.getStatus());
        dto.setCreatedAt(loaner.getCreatedAt());
        dto.setUpdatedAt(loaner.getUpdatedAt());
        dto.setDeletedAt(loaner.getDeletedAt());
        return dto;
    }

    public Loaner updateLoanerFromDto(LoanerDto dto, Loaner loaner) {
        if (dto == null) {
            return loaner;
        }
        if (dto.getId() != null) {
            loaner.setId(dto.getId());
        }
        if (dto.getTotalPrice() != null) {
            loaner.setTotalPrice(dto.getTotalPrice());
        }
        if (dto.getStatus() != null) {
            loaner.setStatus(dto.getStatus());
        }
        if (dto.getCreatedAt() != null) {
            loaner.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getUpdatedAt() != null) {
            loaner.setUpdatedAt(dto.getUpdatedAt());
        }
        if (dto.getDeletedAt() != null) {
            loaner.setDeletedAt(dto.getDeletedAt());
        }

        return loaner;
    }
}