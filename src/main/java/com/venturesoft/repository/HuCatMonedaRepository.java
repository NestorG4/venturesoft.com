package com.venturesoft.repository;

import com.venturesoft.dto.EmpleadoMonedaDTO;
import com.venturesoft.model.HuCatMoneda;
import com.venturesoft.model.HuCatMonedaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuCatMonedaRepository extends JpaRepository<HuCatMoneda, HuCatMonedaId> {

    Optional<HuCatMoneda> findById(HuCatMonedaId id);

    //@Query con Constructor Expression en JPQL
    @Query("SELECT new com.venturesoft.dto.EmpleadoMonedaDTO(" +
            "e.id.numCia, e.id.numEmp, e.nombre, e.apellidoPaterno, e.apellidoMaterno, e.puesto, " +
            "m.id.numCia, m.id.claveMoneda, m.descripcion, m.simbolo, m.estatus) " +
            "FROM HuCatMoneda m " +
            "JOIN m.listEmpls e " +
            "WHERE m.id.numCia = :numCia AND m.id.claveMoneda = :claveMoneda")
    List<EmpleadoMonedaDTO> obtenerInfoPorNumCiaYClaveMoneda(@Param("numCia") Integer numCia, @Param("claveMoneda") String claveMoneda);

}
