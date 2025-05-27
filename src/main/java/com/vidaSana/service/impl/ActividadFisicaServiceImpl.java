package com.vidaSana.service.impl;

import com.vidaSana.entities.Actividad_Fisica;
import com.vidaSana.service.ActividadFisicaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidaSana.dao.ActividadFisicaDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActividadFisicaServiceImpl implements ActividadFisicaService{
	
	@Autowired
	private ActividadFisicaDAO actividadDao;

	@Override
	public List<Actividad_Fisica> listar() {
		return actividadDao.findAll();
	}

	@Transactional
	@Override
	public Actividad_Fisica guardar(Actividad_Fisica actividad) {
		return actividadDao.save(actividad);
	}

	@Transactional
	@Override
	public void eliminar(Actividad_Fisica actividad) {
		actividadDao.delete(actividad);
	}

	@Override
	public Actividad_Fisica buscarPorId(long id_actividad) {
		return actividadDao.getById(id_actividad);
	}
    
}
