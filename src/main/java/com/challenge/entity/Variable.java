package com.challenge.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Variable {
    @Id
    String nombre;
    
    Float valorNumerico;
}
