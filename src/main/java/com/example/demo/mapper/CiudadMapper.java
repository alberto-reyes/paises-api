package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.CiudadDto;
import com.example.demo.dto.CiudadRequest;
import com.example.demo.entity.Ciudad;

@Mapper(componentModel = "spring")
public interface CiudadMapper {
    
    CiudadDto toDto(Ciudad ciudad);

    @Mapping(source = "paisId", target = "pais.id")
    Ciudad toEntity(CiudadRequest request);
}
