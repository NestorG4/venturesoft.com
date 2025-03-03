package com.venturesoft.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HU_CAT_MONEDA")
public class HuCatMoneda {

    @EmbeddedId
    private HuCatMonedaId id;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "SIMBOLO")
    private String simbolo;

    @Column(name = "ESTATUS")
    private String estatus;

    @OneToMany(mappedBy = "huCatMoneda")
    private List<HuEmpls> listEmpls;

}
