package com.laba6.page.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="works")
@Data
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;

    public Work() {
    }

    public Work(String type) {
        this.type = type;
    }
}
