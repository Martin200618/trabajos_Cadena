package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ProductoresDTO;
import com.sena.crud_basic.model.Productores;
import com.sena.crud_basic.repository.IProductoresRepository;


@Service
public class ProductoresService {

    @Autowired
    private IProductoresRepository productoresRepository;

    // Obtener todos los productores
    public List<Productores> findAll() {
        return productoresRepository.findAll();
    }

    // Obtener productor por ID
    public Optional<Productores> findById(int id) {
        return productoresRepository.findById(id);
    }

    // Registrar un productor
    public String save(ProductoresDTO productoresDTO) {
        if (productoresDTO == null || !isValidProductor(productoresDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del productor son inválidos";
        }

        Productores productor = convertToModel(productoresDTO);
        productoresRepository.save(productor);
        return HttpStatus.OK.toString() + ": Productor registrado exitosamente";
    }

    // Actualizar un productor
    public String update(int id, ProductoresDTO productoresDTO) {
        Optional<Productores> existingProductor = findById(id);

        if (existingProductor.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El productor no existe o ya fue eliminado";
        }

        Productores productorToUpdate = existingProductor.get();
        productorToUpdate.setNombre(productoresDTO.getNombre());
        productorToUpdate.setExperienciaAnios(productoresDTO.getExperienciaAnios());

        productoresRepository.save(productorToUpdate);
        return HttpStatus.OK.toString() + ": Productor actualizado correctamente";
    }

    // Eliminar productor por ID
    public String delete(int id) {
        Optional<Productores> productor = findById(id);

        if (productor.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El productor no existe o ya fue eliminado";
        }

        productoresRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Productor eliminado correctamente";
    }

    // Conversión de DTO a Modelo
    private Productores convertToModel(ProductoresDTO productoresDTO) {
        return new Productores(0, productoresDTO.getNombre(), productoresDTO.getExperienciaAnios());
    }

    // Validación de datos del productor
    private boolean isValidProductor(ProductoresDTO productoresDTO) {
        return productoresDTO.getNombre() != null && !productoresDTO.getNombre().trim().isEmpty()
                && productoresDTO.getNombre().length() <= 100
                && productoresDTO.getExperienciaAnios() > 0;
    }
}