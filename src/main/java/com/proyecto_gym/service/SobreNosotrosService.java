package com.proyecto_gym.service;

import com.proyecto_gym.domain.SobreNosotros;
import com.proyecto_gym.repository.SobreNosotrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SobreNosotrosService {
    @Autowired
    private SobreNosotrosRepository sobrenosotrosRepository;

    @Transactional(readOnly = true)
    public List<SobreNosotros> getSobreNosotross(boolean activos) {
        {
            var lista = sobrenosotrosRepository.findAll();
            return lista;
        }
    }

    // CRUD methods

    //read
    @Transactional(readOnly = true)
    public SobreNosotros getSobreNosotros(SobreNosotros sobrenosotros) {
        {
            sobrenosotros = sobrenosotrosRepository.findById(sobrenosotros.getIdInfo()).orElse(null);
            return sobrenosotros;
        }
    }

    //delete
    @Transactional
    public void delete(SobreNosotros sobrenosotros) {
        //if id is no valid, will not affect the db
        sobrenosotrosRepository.delete(sobrenosotros);
    }

    @Transactional
    public void save(SobreNosotros sobrenosotros) {
        //if the idsobrenosotros have already a value will update the row, if not will insert a new row
        sobrenosotrosRepository.save(sobrenosotros);
    }
}
