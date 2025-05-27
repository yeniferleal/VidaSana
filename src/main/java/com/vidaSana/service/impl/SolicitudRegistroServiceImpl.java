package com.vidaSana.service.impl;

import com.vidaSana.entities.SolicitudRegistro;
import com.vidaSana.service.SolicitudRegistroService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vidaSana.dao.SolicitudRegistroDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudRegistroServiceImpl implements SolicitudRegistroService{
    
    @Autowired
    private SolicitudRegistroDAO solicitudDao;

    @Override
    @Transactional(readOnly = true)
    public List<SolicitudRegistro> listarSolicitudes() {
        return solicitudDao.findAll();
    }

    @Override
    public void guardarSolicitud(SolicitudRegistro solicitud) {
        solicitudDao.saveAndFlush(solicitud);
    }

    @Override
    public void eliminarSolicitud(SolicitudRegistro solicitud) {
        solicitudDao.delete(solicitud);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudRegistro buscarSolicitudRegistro(String username) {
        return solicitudDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudRegistro findById(long id) {
        return solicitudDao.findById(id).get();
    }
}
