package com.venturesoft.service;

import com.venturesoft.dto.EmpleadoMonedaDTO;
import com.venturesoft.model.HuCatMoneda;
import com.venturesoft.model.HuCatMonedaId;
import com.venturesoft.repository.HuCatMonedaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuCatMonedaServiceImp implements HuCatMonedaService{

    @Autowired
    private HuCatMonedaRepository repository;

    @Override
    public HuCatMoneda crearMoneda(HuCatMoneda huCatMoneda) {
        return repository.save(huCatMoneda);
    }

    @Override
    public Optional<HuCatMoneda> obtenerMonedaPorClave(HuCatMonedaId id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moneda no encontrada con identificadores: " + id)));
    }

    @Override
    public void actualizarHCM(HuCatMonedaId id, HuCatMoneda huCatMoneda) {

       HuCatMoneda moneda = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La moneda a actualizar no existe"));
       moneda.setDescripcion(huCatMoneda.getDescripcion());
       moneda.setSimbolo(huCatMoneda.getSimbolo());
       moneda.setEstatus(huCatMoneda.getEstatus());

       repository.save(moneda);
    }

    @Override
    public void eliminarMoneda(HuCatMonedaId id) {
        HuCatMoneda moneda = obtenerMonedaPorClave(id)
                .orElseThrow(() -> new EntityNotFoundException("La moneda no existe"));

        repository.delete(moneda);
    }

    @Override
    public List<HuCatMoneda> listarMonedas() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id.numCia"));
    }

    public List<EmpleadoMonedaDTO> obtenerInfoPorNumCiaYClaveMoneda(Integer numCia, String claveMoneda){
        return repository.obtenerInfoPorNumCiaYClaveMoneda(numCia, claveMoneda);
    }
}
