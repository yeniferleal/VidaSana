package com.vidaSana.service;

import com.vidaSana.entities.Actividad_Fisica;

import java.util.List;

public interface ActividadFisicaService{
    
    public List<Actividad_Fisica> listar();
    
    public Actividad_Fisica guardar(Actividad_Fisica actividad);
    
    public void eliminar(Actividad_Fisica actividad);
        
    public Actividad_Fisica buscarPorId(long id_actividad);
}
