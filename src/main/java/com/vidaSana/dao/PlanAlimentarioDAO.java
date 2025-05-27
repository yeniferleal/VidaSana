package com.vidaSana.dao;

import com.vidaSana.entities.Plan_Alimentario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanAlimentarioDAO extends JpaRepository<Plan_Alimentario,Long>{
    
}
