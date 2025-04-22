package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.proyectDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.proyectService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/proyects")
public class proyectController {

    @Autowired
    private proyectService proyectService;

    // Rate limiting por IP
    private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 50; // Máximo de solicitudes permitidas por minuto

    // ✅ **Registrar proyecto**
    @PostMapping
    public ResponseEntity<Object> registerProyect(@RequestBody proyectDTO proyects, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        // Contar solicitudes por IP
        requestCount.put(clientIp, requestCount.getOrDefault(clientIp, 0) + 1);
        if (requestCount.get(clientIp) > MAX_REQUESTS) {
            return new ResponseEntity<>(
                "Demasiadas solicitudes, espera un momento.", 
                HttpStatus.TOO_MANY_REQUESTS
            );
        }

        // Lógica de validación y límite de registros
        int currentCount = proyectService.countAllProyects();
        int maxLimit = 100; // Define tu límite máximo de registros en la base de datos
        if (currentCount >= maxLimit) {
            return new ResponseEntity<>(
                "No se pueden agregar más proyectos. Límite alcanzado.", 
                HttpStatus.FORBIDDEN
            );
        }

        responseDTO response = proyectService.save(proyects);
            int statusCode = Integer.parseInt(
                response.getStatus().replaceAll("\\D", "")
            );
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(statusCode)
        );
    }

    // ✅ **Obtener todos los proyectos**
    @GetMapping("/")
    public ResponseEntity<Object> getAllProyects() {
        return new ResponseEntity<>(
            proyectService.findAll(), 
            HttpStatus.OK
        );
    }

    // ✅ **Obtener un proyecto por ID**
    @GetMapping("/get/{proyectId}")
    public ResponseEntity<Object> getOneProyect(@PathVariable int proyectId) {
        Optional<Object> proyecto = Optional.ofNullable(proyectService.findById(proyectId));
        return proyecto.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Proyecto no encontrado", 
            HttpStatus.NOT_FOUND)
        );
    }

    // ✅ **Actualizar proyecto**
    @PutMapping("/update/{proyectId}")
    public ResponseEntity<Object> updateProyect(@PathVariable int proyectId, @RequestBody proyectDTO proyects, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        // Contar solicitudes por IP
        requestCount.put(clientIp, requestCount.getOrDefault(clientIp, 0) + 1);
        if (requestCount.get(clientIp) > MAX_REQUESTS) {
            return new ResponseEntity<>(
                "Demasiadas solicitudes, espera un momento.", 
                HttpStatus.TOO_MANY_REQUESTS
            );
        }

        responseDTO response = proyectService.update(proyectId, proyects);
        int statusCode = Integer.parseInt(response.getStatus().replaceAll("\\D", ""));
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(statusCode)
        );
    }

    // ✅ **Eliminar proyecto por ID**
    @DeleteMapping("/delete/{proyectId}")
    public ResponseEntity<Object> deleteProyect(@PathVariable int proyectId, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        // Contar solicitudes por IP
        requestCount.put(clientIp, requestCount.getOrDefault(clientIp, 0) + 1);
        if (requestCount.get(clientIp) > MAX_REQUESTS) {
            return new ResponseEntity<>(
                "Demasiadas solicitudes, espera un momento.", 
                HttpStatus.TOO_MANY_REQUESTS
            );
        }

        responseDTO response = proyectService.deleteProyects(proyectId);
        int statusCode = Integer.parseInt(response.getStatus().replaceAll("\\D", ""));
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(statusCode)
        );
    }
}