package com.venturesoft.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
/*
 CODE: 01Q
 Usar cuando se requiera NativeQuery para obtener los datos de las dos tablas
@SqlResultSetMapping(
        name = "EmpleadoMonedaMapping",
        classes = @ConstructorResult(
                targetClass = EmpleadoMonedaDTO.class,
                columns = {
                        @ColumnResult(name = "EMP_NUM_CIA", type = Integer.class),
                        @ColumnResult(name = "NUM_EMP", type = Integer.class),
                        @ColumnResult(name = "NOMBRE", type = String.class),
                        @ColumnResult(name = "APELLIDO_PATERNO", type = String.class),
                        @ColumnResult(name = "APELLIDO_MATERNO", type = String.class),
                        @ColumnResult(name = "PUESTO", type = String.class),
                        @ColumnResult(name = "MON_NUM_CIA", type = Integer.class),
                        @ColumnResult(name = "MON_CLAVE_MONEDA", type = String.class),
                        @ColumnResult(name = "DESCRIPCION", type = String.class),
                        @ColumnResult(name = "SIMBOLO", type = String.class),
                        @ColumnResult(name = "ESTATUS", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "HuEmpls.obtenerInfoPorNumCiaYNumEmp",
        query = "SELECT e.NUM_CIA AS EMP_NUM_CIA, e.NUM_EMP, e.NOMBRE, " +
                "e.APELLIDO_PATERNO, e.APELLIDO_MATERNO, e.PUESTO, " +
                "m.NUM_CIA AS MON_NUM_CIA, m.CLAVE_MONEDA AS MON_CLAVE_MONEDA, " +
                "m.DESCRIPCION, m.SIMBOLO, m.ESTATUS " +
                "FROM HU_EMPLS e " +
                "INNER JOIN HU_CAT_MONEDA m " +
                "ON e.NUM_CIA = m.NUM_CIA AND e.CLAVE_MONEDA = m.CLAVE_MONEDA " +
                "WHERE e.NUM_CIA = :numCia AND e.NUM_EMP = :numEmp",
        resultSetMapping = "EmpleadoMonedaMapping"
)
 */
@Table(name = "HU_EMPLS")
public class HuEmpls {

    @EmbeddedId
    private HuEmplsId id;

    @Column(name = "CLAVE_MONEDA")
    private String claveMoneda;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "PUESTO")
    private String puesto;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "NUM_CIA", referencedColumnName = "NUM_CIA", insertable = false, updatable = false),
            @JoinColumn(name = "CLAVE_MONEDA", referencedColumnName = "CLAVE_MONEDA", insertable = false, updatable = false)
    })
    @JsonIgnore
    private HuCatMoneda huCatMoneda;

}
