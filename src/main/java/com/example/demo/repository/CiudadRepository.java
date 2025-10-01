package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long>{

}
