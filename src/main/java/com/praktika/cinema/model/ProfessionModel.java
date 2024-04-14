package com.praktika.cinema.model;

import com.praktika.cinema.entity.ProfessionEntity;

public class ProfessionModel {
    private Long id;
    private String name;

    public static ProfessionModel toModel(ProfessionEntity professionEntity){
        ProfessionModel model = new ProfessionModel();
        model.setId(professionEntity.getId());
        model.setName(professionEntity.getName().name());

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
