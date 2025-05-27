package com.vidaSana.dao;

import com.vidaSana.entities.SolicitudRegistro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRegistroDAO extends JpaRepository<SolicitudRegistro,Long>{
    SolicitudRegistro findByUsername(String username);
}
