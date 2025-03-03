package com.venturesoft.service;

import com.venturesoft.model.HuCatMoneda;
import com.venturesoft.model.HuCatMonedaId;

import java.util.List;
import java.util.Optional;

public interface HuCatMonedaService {

    public HuCatMoneda crearMoneda(HuCatMoneda huCatMoneda);

    public Optional<HuCatMoneda> obtenerMonedaPorClave(HuCatMonedaId id);

    public void actualizarHCM(HuCatMonedaId id,HuCatMoneda huCatMoneda);

    public void eliminarMoneda(HuCatMonedaId id);

    public List<HuCatMoneda> listarMonedas();
}
