package com.digitalhouse.CoachConnectBE.controller.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActualizarTutorResultadoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String contactoCelular;
    private String foto;
    private Long profesionId;
    private String descripcion;
}
