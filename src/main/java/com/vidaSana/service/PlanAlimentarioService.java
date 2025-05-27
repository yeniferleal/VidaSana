package com.vidaSana.service;

import com.vidaSana.entities.Plan_Alimentario;

import java.util.List;

public interface PlanAlimentarioService{
    
    public List<Plan_Alimentario> listar();
    
    public Plan_Alimentario guardar(Plan_Alimentario plan);
    
    public void eliminar(Plan_Alimentario plan);
        
    public Plan_Alimentario buscarPorId(long id_plan);
}
