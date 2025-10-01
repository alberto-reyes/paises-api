package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.CiudadDto;
import com.example.demo.dto.CiudadRequest;
import com.example.demo.mapper.CiudadMapper;
import com.example.demo.repository.CiudadRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ciudad")
public class CiudadResource {

    private CiudadRepository ciudadRepository;
    private CiudadMapper ciudadMapper;

    @GetMapping
    public List<CiudadDto> lista() {
        return ciudadRepository.findAll().stream().map(ciudad -> ciudadMapper.toDto(ciudad)).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDto> consulta(@PathVariable Long id) {
        var ciudad = ciudadRepository.findById(id).orElse(null);
        if (ciudad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ciudadMapper.toDto(ciudad));
    }

    @PostMapping
    public ResponseEntity<CiudadDto> agregar(@RequestBody CiudadRequest request, UriComponentsBuilder uriBuilder) {
        var ciudad = ciudadMapper.toEntity(request);
        ciudadRepository.save(ciudad);

        var uri = uriBuilder.path("/ciudad/{id}").buildAndExpand(ciudad.getId()).toUri();

        return ResponseEntity.created(uri).body(ciudadMapper.toDto(ciudad));
    }

}
