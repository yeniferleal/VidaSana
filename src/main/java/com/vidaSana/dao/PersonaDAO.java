package com.vidaSana.dao;

import com.vidaSana.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDAO extends JpaRepository<Persona,Long>{
    
}
