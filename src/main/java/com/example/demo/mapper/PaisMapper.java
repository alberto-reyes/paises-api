package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.demo.dto.PaisDto;
import com.example.demo.dto.PaisRequest;
import com.example.demo.entity.Pais;

@Mapper(componentModel = "spring")
public interface PaisMapper {

    PaisDto toDto(Pais pais);

    Pais toEntity(PaisRequest paisRequest);

    @Mapping(target = "id", ignore = true)
    void update(PaisRequest request, @MappingTarget Pais pais);

}
