package com.vidaSana.service;

import com.vidaSana.entities.Persona;
import java.util.List;

public interface PersonaService{
    
    public List<Persona> listar();
    
    public Persona guardar(Persona persona);
    
    public void eliminar(Persona persona);
        
    public Persona buscarPorId(long id_persona);
}
