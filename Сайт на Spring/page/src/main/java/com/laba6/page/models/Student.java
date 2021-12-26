package com.laba6.page.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String family;
    private String name;
    private String patronymic;
    public Student(){
    }

    public Student(String family, String name, String patronymic){
        this.name = name;
        this.family = family;
        this.patronymic = patronymic;
    }
}
