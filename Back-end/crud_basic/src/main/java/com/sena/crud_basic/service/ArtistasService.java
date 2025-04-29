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
        try {
            return artistasRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los artistas: " + e.getMessage());
        }
    }

    // Obtener artista por ID
    public Optional<Artistas> findById(int Artistas_id) {
        try {
            return artistasRepository.findById(Artistas_id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el artista con ID: " + Artistas_id + ", Error: " + e.getMessage());
        }
    }

    // Registrar un artista
    public String save(ArtistasDTO artistasDTO) {
        if (artistasDTO == null || !isValidArtista(artistasDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del artista son inválidos";
        }

        try {
            Artistas artista = convertToModel(artistasDTO);
            artistasRepository.save(artista);
            return HttpStatus.OK.toString() + ": Artista registrado exitosamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al registrar el artista, Detalle: " + e.getMessage();
        }
    }

    // Actualizar un artista
    public String update(int Artistas_id, ArtistasDTO artistasDTO) {
        Optional<Artistas> existingArtista = findById(Artistas_id);

        if (!existingArtista.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El artista con ID " + Artistas_id + " no existe o ya fue eliminado";
        }

        try {
            Artistas artistaToUpdate = existingArtista.get();
            artistaToUpdate.setNombre(artistasDTO.getNombre());
            artistaToUpdate.setPais(artistasDTO.getPais());
            artistasRepository.save(artistaToUpdate);
            return HttpStatus.OK.toString() + ": Artista actualizado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar el artista, Detalle: " + e.getMessage();
        }
    }

    // Eliminar artista por ID
    public String delete(int Artistas_id) {
        Optional<Artistas> artista = findById(Artistas_id);

        if (!artista.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El artista con ID " + Artistas_id + " no existe o ya fue eliminado";
        }

        try {
            artistasRepository.deleteById(Artistas_id);
            return HttpStatus.OK.toString() + ": Artista eliminado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar el artista, Detalle: " + e.getMessage();
        }
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