package com.laba6.page.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="years")
@Data
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer year;

    public Year() {
    }
}
