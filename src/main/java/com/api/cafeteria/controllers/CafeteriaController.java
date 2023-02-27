package com.api.cafeteria.controllers;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cafeteria.dto.CafeteriaDto;
import com.api.cafeteria.models.CafeteriaModel;
import com.api.cafeteria.services.CafeteriaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cafeteria")
public class CafeteriaController {
    
    @Autowired
    CafeteriaService cafeteriaService;

    @PostMapping
    public ResponseEntity<Object> saveCafeteria(@RequestBody @Valid CafeteriaDto cafeteriaDto){
        if(cafeteriaService.existsByTitle(cafeteriaDto.getTitle())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This title is already in use.");
        }
        var cafeteriaModel = new CafeteriaModel();
        BeanUtils.copyProperties(cafeteriaDto, cafeteriaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cafeteriaService.save(cafeteriaModel));
    }

    @GetMapping
    public ResponseEntity<List<CafeteriaModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(cafeteriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id) {
        Optional<CafeteriaModel> cafeteriaModelOptional = cafeteriaService.findById(id);
        if (!cafeteriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Snack not registered");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cafeteriaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id) {
        Optional<CafeteriaModel> cafeteriaModelOptional = cafeteriaService.findById(id);
        if (!cafeteriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Snack not registered");
        }
        cafeteriaService.delete(cafeteriaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Snack deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable(value = "id") UUID id, @RequestBody @Valid CafeteriaDto cafeteriaDto) {
        Optional<CafeteriaModel> cafeteriaModelOptional = cafeteriaService.findById(id);
        if (!cafeteriaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Snack not registered");
        }
        var cafeteriaModel = new CafeteriaModel();
        BeanUtils.copyProperties(cafeteriaDto, cafeteriaModel);
        cafeteriaModel.setId(cafeteriaModelOptional.get().getId());
        cafeteriaService.save(cafeteriaModel);
        return ResponseEntity.status(HttpStatus.OK).body("Snack updated");

    }
}
