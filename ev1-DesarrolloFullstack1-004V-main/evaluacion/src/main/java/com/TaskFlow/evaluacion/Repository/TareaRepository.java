package com.TaskFlow.evaluacion.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import com.TaskFlow.evaluacion.Model.Tarea;
import com.TaskFlow.evaluacion.Model.Estado;

@Repository
public class TareaRepository {
    
    private List<Tarea> tareas=new ArrayList<>();
    private AtomicLong contadorId=new AtomicLong(1);
    
    public Tarea guardar(Tarea tarea){
        tarea.setId(contadorId.getAndIncrement());
        tareas.add(tarea);
        return tarea;
    }
    public Optional<Tarea>buscarPorId(Long id){
        for (Tarea t:tareas){
            if (t.getId().equals(id)){
                return Optional.of(t);
            }
        }
        return Optional.empty();
    }
    public List<Tarea>listarTodas(){
        return new ArrayList<>(tareas);
    }
    public Optional<Tarea>actualizar(Long id, Tarea nuevaData){
        for (int i=0;i<tareas.size();i++){
            if (tareas.get(i).getId().equals(id)) {
                nuevaData.setId(id);
                tareas.set(i, nuevaData);
                return Optional.of(nuevaData);
            }
        }
        return Optional.empty();
    }
    public boolean eliminar(Long id) {
        return tareas.removeIf(t -> t.getId().equals(id));
    }
    public List<Tarea> filtrarPorEstado(Estado estado) {
        List<Tarea> resultado = new ArrayList<>();
        for (Tarea t : tareas) {
            if (t.getEstado().equals(estado)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}