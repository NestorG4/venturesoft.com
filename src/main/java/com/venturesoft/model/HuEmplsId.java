package com.venturesoft.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class HuEmplsId implements Serializable {

    @Column(name = "NUM_CIA")
    private Integer numCia;

    @Column(name = "NUM_EMP")
    private Integer numEmp;
}
