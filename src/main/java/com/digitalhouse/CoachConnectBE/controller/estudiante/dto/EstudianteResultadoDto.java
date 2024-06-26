package com.digitalhouse.CoachConnectBE.controller.estudiante.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EstudianteResultadoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String contactoCelular;
    private String foto;
    private String username;
    private String nivelEducativo;
    private Boolean habilitado;
}
