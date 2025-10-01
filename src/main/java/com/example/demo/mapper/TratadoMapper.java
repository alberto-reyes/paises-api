package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.TratadoDto;
import com.example.demo.dto.TratadoRequest;
import com.example.demo.entity.Tratado;

@Mapper(componentModel = "spring")
public interface TratadoMapper {


    TratadoDto toDto(Tratado tratado);

    Tratado toEntity(TratadoRequest request);

}
