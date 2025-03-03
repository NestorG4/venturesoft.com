package com.venturesoft.service;

import com.venturesoft.dto.EmpleadoMonedaDTO;
import com.venturesoft.model.HuEmpls;
import com.venturesoft.model.HuEmplsId;
import com.venturesoft.repository.HuEmplsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuEmplsServiceImp implements HumEmplsService {

    @Autowired
    private HuEmplsRepository repository;

    @Override
    public HuEmpls crearEmpls(HuEmpls huEmpls) {
        return repository.save(huEmpls);
    }

    @Override
    public Optional<HuEmpls> obtenerEmplsPorClave(HuEmplsId id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("El empleado con id: " + id + " no fue econtrado")));
    }

    @Override
    public void actualizarEmpls(HuEmplsId id, HuEmpls huEmpls) {
        HuEmpls empls = obtenerEmplsPorClave(id)
                .orElseThrow(() -> new RuntimeException("El empleado a actualizar no existe"));
        empls.setClaveMoneda(empls.getClaveMoneda());
        empls.setNombre(empls.getNombre());
        empls.setApellidoPaterno(empls.getApellidoPaterno());
        empls.setApellidoMaterno(empls.getApellidoMaterno());
        empls.setPuesto(empls.getPuesto());

        repository.save(empls);
    }

    @Override
    public void eliminarEmpls(HuEmplsId id) {
        HuEmpls empls = obtenerEmplsPorClave(id)
                .orElseThrow(() -> new EntityNotFoundException("El emplado no existe"));
        repository.delete(empls);
    }

    @Override
    public List<HuEmpls> listarEmpls() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id.numCia"));
    }

    public List<EmpleadoMonedaDTO> obtenerInfoPorNumCiaYNumEmp(Integer numCia, Integer numEmp) {
        return repository.obtenerInfoPorNumCiaYNumEmp(numCia, numEmp);
    }
}
