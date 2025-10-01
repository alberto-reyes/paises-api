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

@RestController
@RequestMapping("/pais")
public class PaisResource {

    @GetMapping
    public List<String> pais() {
        return List.of("mexico");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDto> listaPaises(@PathVariable Long id) {
        return ResponseEntity.ok(new PaisDto());
    }

    @PostMapping("/agregar")
    public ResponseEntity<PaisDto> nuevoPais(@RequestBody PaisRequest request, UriComponentsBuilder uriBuilder) {
        var pais = new PaisDto();
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).body(pais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDto> actualizarPais(@PathVariable Long id, @RequestBody PaisDto entity) {

        return ResponseEntity.ok(new PaisDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPais(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
