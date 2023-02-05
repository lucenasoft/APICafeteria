package com.api.cafeteria.dto;

import jakarta.validation.constraints.NotBlank;

public class CafeteriaDto {
    
    @NotBlank
    private String title;

    private Double price;

    @NotBlank
    private String description;

    private Integer amount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
