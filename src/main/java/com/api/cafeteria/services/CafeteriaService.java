package com.api.cafeteria.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.api.cafeteria.models.CafeteriaModel;
import com.api.cafeteria.repository.CafeteriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CafeteriaService {
    
    @Autowired
    CafeteriaRepository cafeteriaRepository;

    @Transactional
    public CafeteriaModel save(CafeteriaModel cafeteriaModel) {
        return cafeteriaRepository.save(cafeteriaModel);
    }

    public boolean existsByTitle(String title) {
        return cafeteriaRepository.existsByTitle(title);
    }

    public List<CafeteriaModel> findAll() {
        return cafeteriaRepository.findAll();
    }

    public Optional<CafeteriaModel> findById(UUID id) {
        return cafeteriaRepository.findById(id);
    }

    @Transactional
    public void delete(CafeteriaModel cafeteriaModel) {
        cafeteriaRepository.delete(cafeteriaModel);
    }

}
