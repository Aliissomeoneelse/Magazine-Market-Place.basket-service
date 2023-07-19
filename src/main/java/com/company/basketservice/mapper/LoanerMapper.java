package com.company.basketservice.mapper;



import com.company.basketservice.dto.LoanerDto;
import com.company.basketservice.module.Loaner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Mapper(componentModel = "spring")
public abstract class LoanerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Loaner toEntity(LoanerDto dto);

    public abstract LoanerDto toDto(Loaner loaner);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Loaner updateLoanerFromDto(LoanerDto dto, @MappingTarget Loaner loaner);

    public abstract Set<LoanerDto> toSetDto(Set<Loaner> loaners);
}