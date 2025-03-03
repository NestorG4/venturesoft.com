package com.venturesoft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoMonedaDTO {

    private Integer numCiaEmp;
    private Integer numEmp;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String puesto;
    private Integer numCiaMoneda;
    private String claveMoneda;
    private String descripcion;
    private String simbolo;
    private String estatus;

}
