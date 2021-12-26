package com.laba6.page.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="key_words")
@Data //auto generat getters and setters
public class Key_word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String word;

    public Key_word(){
    }

    public Key_word(String word){
        this.word = word;
    }
}
