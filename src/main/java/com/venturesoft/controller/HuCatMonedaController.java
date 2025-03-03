package com.venturesoft.controller;

import com.venturesoft.dto.EmpleadoMonedaDTO;
import com.venturesoft.model.HuCatMoneda;
import com.venturesoft.model.HuCatMonedaId;
import com.venturesoft.service.HuCatMonedaServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/huCatMoneda")
public class HuCatMonedaController {

    @Autowired
    private HuCatMonedaServiceImp service;

    @PostMapping("crearMoneda")
    public ResponseEntity<HuCatMoneda> crearMoneda(@RequestBody HuCatMoneda huCatMoneda){
        return Optional.ofNullable(service.crearMoneda(huCatMoneda))
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/buscarPorClave/{numCia}/{claveMoneda}")
    public ResponseEntity<HuCatMoneda> obtenerPorClave(@PathVariable Integer numCia, @PathVariable String claveMoneda){
        HuCatMonedaId id = new HuCatMonedaId(numCia, claveMoneda);
        return service.obtenerMonedaPorClave(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/actualizar/{numCia}/{claveMoneda}")
    public ResponseEntity<HuCatMoneda> actualizarMoneda(@PathVariable Integer numCia, @PathVariable String claveMoneda, @RequestBody HuCatMoneda huCatMoneda){
        HuCatMonedaId id = new HuCatMonedaId(numCia, claveMoneda);
        try{
            service.actualizarHCM(id, huCatMoneda);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{numCia}/{claveMoneda}")
    public ResponseEntity<?> eliminarMoneda(@PathVariable Integer numCia, @PathVariable String claveMoneda) {
        HuCatMonedaId id = new HuCatMonedaId(numCia, claveMoneda);
        return service.obtenerMonedaPorClave(id)
                .map(moneda -> {
                    service.eliminarMoneda(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<HuCatMoneda>> listar(){
        List<HuCatMoneda> monedas = service.listarMonedas();
        return monedas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(monedas);
    }

    @GetMapping("/buscar/{numCia}/{claveMoneda}")
    public ResponseEntity<List<EmpleadoMonedaDTO>> obtenerInfo(@PathVariable Integer numCia, @PathVariable String claveMoneda){
        List<EmpleadoMonedaDTO> monedas = service.obtenerInfoPorNumCiaYClaveMoneda(numCia, claveMoneda);
        return monedas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(monedas);
    }

    @GetMapping("/saludo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saludo(){
        return "Saludos desde ADMIN";
    }
}
