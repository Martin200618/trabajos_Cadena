package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.CancionesDTO;
import com.sena.crud_basic.model.Albumes;
import com.sena.crud_basic.model.Canciones;
import com.sena.crud_basic.model.Generos;
import com.sena.crud_basic.repository.IAlbumesRepository;
import com.sena.crud_basic.repository.ICancionesRepository;
import com.sena.crud_basic.repository.IGenerosRepository;

@Service
public class CancionesService {

    @Autowired
    private ICancionesRepository cancionesRepository;

    @Autowired
    private IAlbumesRepository albumesRepository;

    @Autowired
    private IGenerosRepository generosRepository;

    // Obtener todas las canciones
    public List<Canciones> findAll() {
        return cancionesRepository.findAll();
    }

    // Obtener canción por ID
    public Optional<Canciones> findById(int id) {
        return cancionesRepository.findById(id);
    }

    // Registrar una canción
    public String save(CancionesDTO cancionesDTO) {
        Optional<Albumes> album = albumesRepository.findById(cancionesDTO.getAlbumId());
        if (album.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El álbum asociado no existe";
        }

        Optional<Generos> genero = generosRepository.findById(cancionesDTO.getGeneroId());
        if (genero.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El género asociado no existe";
        }

        // Convertir DTO a entidad
        Canciones cancion = convertToModel(cancionesDTO, album.get(), genero.get());
        cancionesRepository.save(cancion);

        return HttpStatus.OK.toString() + ": Canción registrada exitosamente";
    }

    // Actualizar una canción
    public String update(int id, CancionesDTO cancionesDTO) {
        Optional<Canciones> existingCancion = findById(id);
        if (existingCancion.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": La canción no existe o ya fue eliminada";
        }

        Optional<Albumes> album = albumesRepository.findById(cancionesDTO.getAlbumId());
        if (album.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El álbum asociado no existe";
        }

        Optional<Generos> genero = generosRepository.findById(cancionesDTO.getGeneroId());
        if (genero.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El género asociado no existe";
        }

        Canciones cancionToUpdate = existingCancion.get();
        cancionToUpdate.setTitulo(cancionesDTO.getTitulo());
        cancionToUpdate.setDuracion(cancionesDTO.getDuracion());
        cancionToUpdate.setAlbum(album.get());
        cancionToUpdate.setGenero(genero.get());

        cancionesRepository.save(cancionToUpdate);
        return HttpStatus.OK.toString() + ": Canción actualizada correctamente";
    }

    // Eliminar canción por ID
    public String delete(int id) {
        Optional<Canciones> cancion = findById(id);
        if (cancion.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": La canción no existe o ya fue eliminada";
        }

        cancionesRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Canción eliminada correctamente";
    }
    

    // Método para convertir DTO a entidad
    private Canciones convertToModel(CancionesDTO cancionesDTO, Albumes album, Generos genero) {
        return new Canciones(
            0, // ID automático por JPA
            cancionesDTO.getTitulo(),
            cancionesDTO.getDuracion(),
            album,
            genero,
            null, // Aquí puedes manejar productores si es necesario
            null  // Aquí puedes manejar estudios de grabación si es necesario
        );
    }
}