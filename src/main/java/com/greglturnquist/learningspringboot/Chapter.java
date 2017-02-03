package com.greglturnquist.learningspringboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chapter {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private Chapter() {
// No one but JPA uses this.
    }

    public Chapter(String name) {
        this.name = name;
    }
}
