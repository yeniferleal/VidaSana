package com.vidaSana.service;

import com.vidaSana.entities.SolicitudRegistro;
import java.util.List;

public interface SolicitudRegistroService{
    
    public List<SolicitudRegistro> listarSolicitudes();
    
    public void guardarSolicitud(SolicitudRegistro solicitud);
    
    public void eliminarSolicitud(SolicitudRegistro solicitud);
    
    public SolicitudRegistro buscarSolicitudRegistro(String username);
    
    public SolicitudRegistro findById(long id);
}
