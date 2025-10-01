package com.example.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.PaisDto;
import com.example.demo.dto.TratadoDto;
import com.example.demo.dto.TratadoRequest;
import com.example.demo.entity.Pais;
import com.example.demo.entity.Tratado;
import com.example.demo.mapper.TratadoMapper;
import com.example.demo.mapper.TratadoMapperImpl;
import com.example.demo.repository.PaisRepository;
import com.example.demo.repository.TratadoRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/tratado")
public class TratadoResource {

    private TratadoMapper tratadoMapper;
    private TratadoRepository tratadoRepository;
    private PaisRepository paisRepository;

    @GetMapping("/{id}")
    public ResponseEntity<TratadoDto> consulta(@PathVariable Long id) {
        var tratado = tratadoRepository.findById(id).orElse(null);

        if (tratado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tratadoMapper.toDto(tratado));
    }

    @PostMapping
    public ResponseEntity<TratadoDto> agregar(@RequestBody TratadoRequest request, UriComponentsBuilder uriBuilder) {
        Tratado tratado = tratadoMapper.toEntity(request);

        Set<Pais> paises = new HashSet<>(paisRepository.findAllById(request.getPaises().stream().map(dto -> dto.getId()).toList()));
        tratado.setPaises(paises);
        tratadoRepository.save(tratado);
        var response = tratadoMapper.toDto(tratado);
        var uri = uriBuilder.path("/tratado/{id}").buildAndExpand(tratado.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
    

}
