package com.example.multipleentitymanagers.models.mysql;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_Store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;
    private String name;

    public Store(String name) {
        this.name = name;
    }
}
