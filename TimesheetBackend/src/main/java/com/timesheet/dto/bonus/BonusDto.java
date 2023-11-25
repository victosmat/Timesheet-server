package com.timesheet.dto.bonus;

import jakarta.persistence.Column;

public class BonusDto {
    private Integer id;
    private String name;
    private String description;
    private Long gratuity;

    public BonusDto(Integer id, String name, String description, Long gratuity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gratuity = gratuity;
    }

    public BonusDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGratuity() {
        return gratuity;
    }

    public void setGratuity(Long gratuity) {
        this.gratuity = gratuity;
    }
}
