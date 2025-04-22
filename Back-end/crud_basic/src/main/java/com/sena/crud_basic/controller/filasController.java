package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.crud_basic.DTO.filasDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.filasService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("api/v1/proyects")
public class filasController {

    @Autowired
    private filasService filasService;

    // Rate limiting por IP
    private final Map<String, Integer> requestCount = new ConcurrentHashMap<>();
    private static final int MAX_REQUESTS = 20; // Máximo de solicitudes permitidas por minuto

    // ✅ **Registrar proyecto**
    @PostMapping
    public ResponseEntity<Object> registerFilas(@RequestBody filasDTO filas, HttpServletRequest request) {
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
        int currentCount = filasService.countAllFilas();
        int maxLimit = 100; // Define tu límite máximo de registros en la base de datos
        if (currentCount >= maxLimit) {
            return new ResponseEntity<>(
                "No se pueden agregar más filas. Límite alcanzado.", 
                HttpStatus.FORBIDDEN
            );
        }

        responseDTO response = filasService.save(filas);
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
    public ResponseEntity<Object> getAllFilas() {
        return new ResponseEntity<>(
            filasService.findAll(), 
            HttpStatus.OK
        );
    }

    // ✅ **Obtener un proyecto por ID**
    @GetMapping("/get/{filasId}")
    public ResponseEntity<Object> getOneFilas(@PathVariable int filasId) {
        Optional<Object> proyecto = Optional.ofNullable(filasService.findById(filasId));
        return proyecto.map(value -> new ResponseEntity<>(
            value, 
            HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(
            "Fila no encontrada", 
            HttpStatus.NOT_FOUND)
        );
    }

    // ✅ **Actualizar proyecto**
    @PutMapping("/update/{filasId}")
    public ResponseEntity<Object> updateFilas(@PathVariable int filasId, @RequestBody filasDTO filas, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        // Contar solicitudes por IP
        requestCount.put(clientIp, requestCount.getOrDefault(clientIp, 0) + 1);
        if (requestCount.get(clientIp) > MAX_REQUESTS) {
            return new ResponseEntity<>(
                "Demasiadas solicitudes, espera un minuto.", 
                HttpStatus.TOO_MANY_REQUESTS
            );
        }

        responseDTO response = filasService.update(filasId, filas);
        int statusCode = Integer.parseInt(response.getStatus().replaceAll("\\D", ""));
        return new ResponseEntity<>(
            response, 
            HttpStatus.valueOf(statusCode)
        );
    }

    // ✅ **Eliminar proyecto por ID**
    @DeleteMapping("/delete/{filasId}")
    public ResponseEntity<Object> deleteFilas(@PathVariable int filasId, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        // Contar solicitudes por IP
        requestCount.put(clientIp, requestCount.getOrDefault(clientIp, 0) + 1);
        if (requestCount.get(clientIp) > MAX_REQUESTS) {
            return new ResponseEntity<>(
                "Demasiadas solicitudes, espera un momento.", 
                HttpStatus.TOO_MANY_REQUESTS
            );
        }

        responseDTO response = filasService.deleteFilas(filasId);
        int statusCode = Integer.parseInt(response.getStatus().replaceAll("\\D", ""));
        return new ResponseEntity<>(
            response,
            HttpStatus.valueOf(statusCode)
        );
    }
}