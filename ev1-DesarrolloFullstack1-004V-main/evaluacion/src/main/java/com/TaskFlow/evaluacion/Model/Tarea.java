package com.TaskFlow.evaluacion.Model;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Genera automaticamente los getters and setters
@AllArgsConstructor
@NoArgsConstructor

public class Tarea {
    private Long id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private Prioridad prioridad;
    private String responsable;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
}