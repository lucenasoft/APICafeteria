package com.api.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cafeteria.models.CafeteriaModel;

import java.util.UUID;

public interface CafeteriaRepository extends JpaRepository<CafeteriaModel, UUID>{

    boolean existsByTitle(String title);
    // Nossa Injeção de dependencias.
}
