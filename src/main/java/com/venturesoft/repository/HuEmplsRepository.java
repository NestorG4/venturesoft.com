package com.venturesoft.repository;

import com.venturesoft.dto.EmpleadoMonedaDTO;
import com.venturesoft.model.HuEmpls;
import com.venturesoft.model.HuEmplsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuEmplsRepository extends JpaRepository<HuEmpls, HuEmplsId> {

    Optional<HuEmpls> findById(HuEmplsId id);
    /*
        CODE: 01Q
        Usar cuando en la entidad se maneje NativeQuery con @SqlResultSetMapping y @NamedNativeQuery
        @Query(nativeQuery = true, name = "HuEmpls.obtenerInfoPorNumCiaYNumEmp")
    */

    //@Query con Constructor Expression en JPQL
    @Query("SELECT new com.venturesoft.dto.EmpleadoMonedaDTO(" +
        "e.id.numCia, e.id.numEmp, e.nombre, e.apellidoPaterno, e.apellidoMaterno, e.puesto, " +
        "m.id.numCia, m.id.claveMoneda, m.descripcion, m.simbolo, m.estatus) " +
        "FROM HuEmpls e " +
        "JOIN e.huCatMoneda m " +
        "WHERE e.id.numCia = :numCia AND e.id.numEmp = :numEmp")
    List<EmpleadoMonedaDTO> obtenerInfoPorNumCiaYNumEmp(@Param("numCia") Integer numCia, @Param("numEmp") Integer numEmp);

   }
