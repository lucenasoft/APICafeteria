package com.api.cafeteria.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cafeteria.dto.CafeteriaDto;
import com.api.cafeteria.models.CafeteriaModel;
import com.api.cafeteria.services.CafeteriaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
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
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Title is already using");
        }
        var cafeteriaModel = new CafeteriaModel();
        BeanUtils.copyProperties(cafeteriaDto, cafeteriaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cafeteriaService.save(cafeteriaModel));
    }

    @GetMapping
    public ResponseEntity<List<CafeteriaModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(cafeteriaService.findAll());
    }
}
