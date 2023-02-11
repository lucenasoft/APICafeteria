package com.api.cafeteria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CafeteriaDto {
    
    @NotBlank
    private String title;

    private Double price;

    @NotBlank
    private String description;

    private Integer amount;

}
