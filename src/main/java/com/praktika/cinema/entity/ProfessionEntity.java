package com.praktika.cinema.entity;

import com.praktika.cinema.enums.EProfession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profession")
public class ProfessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Long стоило бы заменить на byte
    @Enumerated(EnumType.STRING)
    private EProfession name;
    @OneToMany(mappedBy = "profession")
    private List<PersonEntity> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EProfession getName() {
        return name;
    }

    public void setName(EProfession name) {
        this.name = name;
    }

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }
}
