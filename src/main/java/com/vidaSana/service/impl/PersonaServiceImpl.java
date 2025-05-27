package com.vidaSana.service.impl;

import com.vidaSana.entities.Persona;
import com.vidaSana.service.PersonaService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidaSana.dao.PersonaDAO;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaDAO personaDao;

	@Override
	public List<Persona> listar() {
		return personaDao.findAll();
	}

	@Transactional
	@Override
	public Persona guardar(Persona persona) {
		return personaDao.save(persona);
	}

	@Transactional
	@Override
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
	}

	@Override
	public Persona buscarPorId(long id_persona) {
		return personaDao.getById(id_persona);
	}
    
}
