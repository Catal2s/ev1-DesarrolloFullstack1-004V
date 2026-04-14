package com.TaskFlow.evaluacion.Service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TaskFlow.evaluacion.Model.Tarea;
import com.TaskFlow.evaluacion.Model.Estado;
import com.TaskFlow.evaluacion.Repository.TareaRepository;

@Service
public class TareaService {
    @Autowired
    private TareaRepository repository;
    
    public List<Tarea> listarTodas() {
        return repository.listarTodas();
    }
    public Tarea buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(()->new RuntimeException("Tarea no encontrada"));
    }
    public Tarea crearTarea(Tarea tarea) {
        // validaciones
        if (tarea.getTitulo()==null || tarea.getTitulo().trim().isEmpty()) {
            throw new RuntimeException("El titulo es obligatorio");
        }
        if (tarea.getDescripcion()==null || tarea.getDescripcion().trim().isEmpty()) {
            throw new RuntimeException("La descripcion es obligatoria");
        }
        if (tarea.getResponsable()==null || tarea.getResponsable().trim().isEmpty()) {
            throw new RuntimeException("El responsable es obligatorio");
        }
        if (tarea.getFechaLimite()==null) {
            throw new RuntimeException("La fecha limite es obligatoria");
        }
        
        tarea.setFechaCreacion(LocalDate.now());

        if (tarea.getEstado()==null) {
            tarea.setEstado(Estado.PENDIENTE);
        }
        return repository.guardar(tarea);
    }
    public Tarea actualizarTarea(Long id, Tarea tareaActualizada) {
        Tarea existente=buscarPorId(id);
        if (tareaActualizada.getTitulo()==null || tareaActualizada.getTitulo().trim().isEmpty()){
            throw new RuntimeException("El titulo es obligatorio");
        }
        if (tareaActualizada.getDescripcion()==null || tareaActualizada.getDescripcion().trim().isEmpty()){
            throw new RuntimeException("La descripcion es obligatoria");
        }
        if (tareaActualizada.getResponsable()==null || tareaActualizada.getResponsable().trim().isEmpty()){
            throw new RuntimeException("El responsable es obligatorio");
        }
        if (tareaActualizada.getFechaLimite()==null) {
            throw new RuntimeException("La fecha limite es obligatoria");
        }
        tareaActualizada.setFechaCreacion(existente.getFechaCreacion());
        
        return repository.actualizar(id, tareaActualizada)
                .orElseThrow(() -> new RuntimeException("Error al actualizar"));
    }
    public void eliminarTarea(Long id){
        if (!repository.eliminar(id)){
            throw new RuntimeException("Tarea no encontrada");
        }
    }
    public List<Tarea> filtrarPorEstado(Estado estado){
        return repository.filtrarPorEstado(estado);
    }
}