package com.TaskFlow.evaluacion.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.TaskFlow.evaluacion.Model.Tarea;
import com.TaskFlow.evaluacion.Model.Estado;
import com.TaskFlow.evaluacion.Service.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {
    @Autowired
    private TareaService service;

    @GetMapping
    public List<Tarea> listar() {
        return service.listarTodas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        try {
            Tarea nueva = service.crearTarea(tarea);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        try {
            return ResponseEntity.ok(service.actualizarTarea(id, tarea));
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Tarea no encontrada")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            service.eliminarTarea(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Tarea>> filtrarPorEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(service.filtrarPorEstado(estado));
    }
}