package com.venturesoft.controller;

import com.venturesoft.dto.EmpleadoMonedaDTO;
import com.venturesoft.model.HuEmpls;
import com.venturesoft.model.HuEmplsId;
import com.venturesoft.service.HuEmplsServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empls")
public class HuEmplsController {

    @Autowired
    private HuEmplsServiceImp service;

    @PostMapping("crearEmpls")
    public ResponseEntity<HuEmpls> crearMoneda(@RequestBody HuEmpls huEmpls){
        return Optional.ofNullable(service.crearEmpls(huEmpls))
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/buscarPorClave/{numCia}/{numEmp}")
    public ResponseEntity<HuEmpls> obtenerPorClave(@PathVariable Integer numCia, @PathVariable Integer nuEmp ){
        HuEmplsId id = new HuEmplsId(numCia, nuEmp);
        return service.obtenerEmplsPorClave(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/actualizar/{numCia}/{numEmp}")
    public ResponseEntity<HuEmpls> actualizarMoneda(@PathVariable Integer numCia, @PathVariable Integer nuEmp, @RequestBody HuEmpls huEmpls){
        HuEmplsId id = new HuEmplsId(numCia, nuEmp);
        try{
            service.actualizarEmpls(id, huEmpls);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{numCia}/{nuEmp}")
    public ResponseEntity<?> eliminarMoneda(@PathVariable Integer numCia, @PathVariable Integer nuEmp) {
        HuEmplsId id = new HuEmplsId(numCia, nuEmp);
        return service.obtenerEmplsPorClave(id)
                .map(empls -> {
                    service.eliminarEmpls(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<HuEmpls>> listar(){
        List<HuEmpls> empls = service.listarEmpls();
        return empls.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(empls);
    }

    @GetMapping("/buscar/{numCia}/{numEmp}")
    public ResponseEntity<List<EmpleadoMonedaDTO>> obtenerInfo(@PathVariable Integer numCia, @PathVariable Integer numEmp){
        List<EmpleadoMonedaDTO> empls = service.obtenerInfoPorNumCiaYNumEmp(numCia, numEmp);
        return empls.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(empls);
    }

    @GetMapping("/saludo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> saludo(){
        return ResponseEntity.status(HttpStatus.CREATED).body("Hola desde AMIN");
    }
}
