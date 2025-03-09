package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.branches;
import com.sena.crud_basic.DTO.branchesDTO;
import com.sena.crud_basic.repository.Ibranches;

@Service
public class branchesService {
    @Autowired
    private Ibranches data;

    public void save(branchesDTO branchesDTO){
        branches branchesRegister = converToModel(branchesDTO);
        data.save(branchesRegister);
    }

    public branchesDTO converToDTO(branches branches){
        branchesDTO branchesDTO = new branchesDTO(
            branches.getname(),
            branches.getaddres(),
            branches.getphone());
            return branchesDTO;
    }

    
}
