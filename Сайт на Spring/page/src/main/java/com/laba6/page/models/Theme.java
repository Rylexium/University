package com.laba6.page.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="themes")
@Data //auto generat getters and setters
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer id_teacher;
    private Integer id_student;
    private Integer type_work;
    private Integer year;
    private Integer grade;

    public Theme(){
    }

}
