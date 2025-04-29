package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.AlbumesDTO;
import com.sena.crud_basic.model.Albumes;
import com.sena.crud_basic.model.Artistas;
import com.sena.crud_basic.repository.IAlbumesRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.sena.crud_basic.repository.IArtistasRepository;

@Service
public class AlbumesService {

    @Autowired
    private IAlbumesRepository albumesRepository;

    @Autowired
    private IArtistasRepository artistasRepository;

    // Obtener todos los álbumes
    public List<Albumes> findAll() {
        return albumesRepository.findAll();
    }

    // Obtener álbum por ID
    public Optional<Albumes> findById(int id) {
        return albumesRepository.findById(id);
    }

    // Registrar un álbum
    public String save(AlbumesDTO albumesDTO) {
        Optional<Artistas> artista = artistasRepository.findById(Integer.parseInt(albumesDTO.getArtista()));
        if (artista.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El artista asociado no existe";
        }

        Albumes album = convertToModel(albumesDTO, artista.get());
        albumesRepository.save(album);
        return HttpStatus.OK.toString() + ": Álbum registrado exitosamente";
    }

    // Actualizar un álbum
    public String update(int id, AlbumesDTO albumesDTO) {
        Optional<Albumes> existingAlbum = findById(id);
        if (existingAlbum.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El álbum no existe";
        }

        Optional<Artistas> artista = artistasRepository.findById(Integer.parseInt(albumesDTO.getArtista()));
        if (artista.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El artista asociado no existe";
        }

        Albumes albumToUpdate = updateFields(existingAlbum.get(), albumesDTO, artista.get());
        albumesRepository.save(albumToUpdate);
        return HttpStatus.OK.toString() + ": Álbum actualizado correctamente";
    }

    // Eliminar álbum por ID
    public String delete(int id) {
        Optional<Albumes> album = findById(id);
        if (album.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El álbum no existe o ya fue eliminado";
        }

        albumesRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Álbum eliminado correctamente";
    }

    // Conversión de DTO a Modelo
    private Albumes convertToModel(AlbumesDTO albumesDTO, Artistas artista) {
        try {
            Date fechaLanzamiento = new SimpleDateFormat("yyyy-MM-dd").parse(albumesDTO.getFechaLanzamiento());
            return new Albumes(
                0, 
                albumesDTO.getTitulo(), 
                artista, 
                new java.sql.Date(fechaLanzamiento.getTime())
            );
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha de lanzamiento: " + albumesDTO.getFechaLanzamiento(), e);
        }
    }

    // Método para actualizar los campos de un álbum
    private Albumes updateFields(Albumes albumToUpdate, AlbumesDTO albumesDTO, Artistas artista) {
        try {
            Date fechaLanzamiento = new SimpleDateFormat("yyyy-MM-dd").parse(albumesDTO.getFechaLanzamiento());
            albumToUpdate.setFechaLanzamiento(new java.sql.Date(fechaLanzamiento.getTime()));
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha de lanzamiento: " + albumesDTO.getFechaLanzamiento(), e);
        }

        albumToUpdate.setTitulo(albumesDTO.getTitulo());
        albumToUpdate.setArtista(artista);
        return albumToUpdate;
    }
}