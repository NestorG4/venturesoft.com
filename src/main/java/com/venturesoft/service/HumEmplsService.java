package com.venturesoft.service;

import com.venturesoft.model.HuEmpls;
import com.venturesoft.model.HuEmplsId;

import java.util.List;
import java.util.Optional;

public interface HumEmplsService {

    public HuEmpls crearEmpls(HuEmpls huEmpls);

    public Optional<HuEmpls> obtenerEmplsPorClave(HuEmplsId id);

    public void actualizarEmpls(HuEmplsId id, HuEmpls huEmpls);

    public void eliminarEmpls(HuEmplsId id);

    public List<HuEmpls> listarEmpls();
}
