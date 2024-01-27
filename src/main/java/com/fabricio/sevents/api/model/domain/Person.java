package com.fabricio.sevents.api.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Person {

    @Column(name = "name")
    private String name;

    @Column(name = "document")
    private String document;

    @Column(name = "birthdate")
    private LocalDateTime birthdate;

    @Column(name = "phone")
    private String phone;

}