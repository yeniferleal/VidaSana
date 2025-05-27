package com.vidaSana.service.impl;

import com.vidaSana.entities.Plan_Alimentario;
import com.vidaSana.service.PlanAlimentarioService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidaSana.dao.PlanAlimentarioDAO;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanAlimentarioServiceImpl implements PlanAlimentarioService{
	
	@Autowired
	private PlanAlimentarioDAO planDao;

	@Override
	public List<Plan_Alimentario> listar() {
		return planDao.findAll();
	}

	@Transactional
	@Override
	public Plan_Alimentario guardar(Plan_Alimentario plan) {
		return planDao.save(plan);
	}

	@Transactional
	@Override
	public void eliminar(Plan_Alimentario plan) {
		planDao.delete(plan);
	}

	@Override
	public Plan_Alimentario buscarPorId(long id_plan) {
		return planDao.getById(id_plan);
	}
    
}
