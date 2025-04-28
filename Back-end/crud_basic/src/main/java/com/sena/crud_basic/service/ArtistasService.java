package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ArtistasDTO;
import com.sena.crud_basic.model.Artistas;
import com.sena.crud_basic.repository.IArtistasRepository;

@Service
public class ArtistasService {

    @Autowired
    private IArtistasRepository artistasRepository;

    // Obtener todos los artistas
    public List<Artistas> findAll() {
        return artistasRepository.findAll();
    }

    // Obtener artista por ID
    public Optional<Artistas> findById(int id) {
        return artistasRepository.findById(id);
    }

    // Registrar un artista
    public String save(ArtistasDTO artistasDTO) {
        if (artistasDTO == null || !isValidArtista(artistasDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del artista son inválidos";
        }

        Artistas artista = convertToModel(artistasDTO);
        artistasRepository.save(artista);
        return HttpStatus.OK.toString() + ": Artista registrado exitosamente";
    }

    // Actualizar un artista
    public String update(int id, ArtistasDTO artistasDTO) {
        Optional<Artistas> existingArtista = findById(id);

        if (!existingArtista.isPresent()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El artista no existe o ya fue eliminado";
        }

        Artistas artistaToUpdate = existingArtista.get();
        artistaToUpdate.setNombre(artistasDTO.getNombre());
        artistaToUpdate.setPais(artistasDTO.getPais());
        artistasRepository.save(artistaToUpdate);
        return HttpStatus.OK.toString() + ": Artista actualizado correctamente";
    }

    // Eliminar artista por ID
    public String delete(int id) {
        Optional<Artistas> artista = findById(id);

        if (!artista.isPresent()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El artista no existe o ya fue eliminado";
        }

        artistasRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Artista eliminado correctamente";
    }

    // Conversión de DTO a Entidad
    public Artistas convertToModel(ArtistasDTO artistasDTO) {
        return new Artistas(0, artistasDTO.getNombre(), artistasDTO.getPais());
    }

    // Validación de datos de artista
    private boolean isValidArtista(ArtistasDTO artistasDTO) {
        return artistasDTO.getNombre() != null && !artistasDTO.getNombre().trim().isEmpty()
                && artistasDTO.getNombre().length() <= 100
                && artistasDTO.getPais() != null && !artistasDTO.getPais().trim().isEmpty();
    }
}