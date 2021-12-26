package com.laba6.page.models;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="teachers")
@Data //auto generat getters and setters
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String family;
    private String name;
    private String patronymic;

    public Teacher(){
    }

    public Teacher(String family, String name, String patronymic){
        this.name = name;
        this.family = family;
        this.patronymic = patronymic;
    }

}
