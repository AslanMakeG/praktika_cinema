package com.praktika.cinema.model;

import com.praktika.cinema.entity.PersonEntity;

public class PersonModel {
    private Long id;
    private String fullName;
    private ProfessionModel profession;

    public static PersonModel toModel(PersonEntity personEntity){
        PersonModel model = new PersonModel();
        model.setId(personEntity.getId());
        model.setFullName(personEntity.getFullName());
        model.setProfession(ProfessionModel.toModel(personEntity.getProfession()));

        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public ProfessionModel getProfession() {
        return profession;
    }

    public void setProfession(ProfessionModel profession) {
        this.profession = profession;
    }
}
