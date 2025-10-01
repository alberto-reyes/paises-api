package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.PaisDto;
import com.example.demo.dto.PaisRequest;
import com.example.demo.mapper.PaisMapper;
import com.example.demo.repository.PaisRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/pais")
public class PaisResource {

    private PaisMapper mapper;
    private PaisRepository paisRepository;

    @GetMapping
    public List<PaisDto> pais() {
        return paisRepository.findAll()
                .stream()
                .map(pais -> mapper.toDto(pais))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDto> listaPaises(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(paisRepository.findById(id).orElse(null)));
    }

    @PostMapping
    public ResponseEntity<PaisDto> nuevoPais(@RequestBody PaisRequest request, UriComponentsBuilder uriBuilder) {
        var pais = mapper.toEntity(request);

        paisRepository.save(pais);

        var uri = uriBuilder.path("/pais/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDto(pais));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDto> actualizarPais(@PathVariable Long id, @RequestBody PaisRequest request) {
        var pais = paisRepository.findById(id).orElse(null);

        if (pais == null) {
            return ResponseEntity.notFound().build();
        }
        mapper.update(request, pais);
        paisRepository.save(pais);

        return ResponseEntity.ok(mapper.toDto(pais));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPais(@PathVariable Long id) {
        var pais = paisRepository.findById(id).orElse(null);

        if (pais == null) {
            return ResponseEntity.notFound().build();
        }
        paisRepository.delete(pais);

        return ResponseEntity.noContent().build();
    }

}
