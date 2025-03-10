package com.proyecto_gym.service;

import com.proyecto_gym.domain.PlanMembresia;
import com.proyecto_gym.repository.PlanMembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanMembresiaService {
    @Autowired
    private PlanMembresiaRepository planMembresiaRepository;

    @Transactional(readOnly = true)
    public List<PlanMembresia> getPlanMembresias(boolean activos) {
        {
            var lista = planMembresiaRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public PlanMembresia getPlanMembresia(PlanMembresia planMembresia) {
        {
            planMembresia = planMembresiaRepository.findById(planMembresia.getIdPlan()).orElse(null);
            return planMembresia;
        }
    }

    //delete
    @Transactional
    public void delete(PlanMembresia planMembresia) {
        //if id is no valid, will not affect the db
        planMembresiaRepository.delete(planMembresia);
    }

    @Transactional
    public void save(PlanMembresia planMembresia) {
        //if the idplan have already a value will update the row, if not will insert a new row
        planMembresiaRepository.save(planMembresia);
    }
}