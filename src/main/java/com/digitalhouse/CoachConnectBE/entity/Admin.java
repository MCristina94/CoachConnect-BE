package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public String getNombre() {
        return usuario != null ? usuario.getNombre() : null;
    }

    public String getApellido() {
        return usuario != null ? usuario.getApellido() : null;
    }

    public int getEdad() {
        return usuario != null ? usuario.getEdad() : 0;
    }

    public String getEmail() {
        return usuario != null ? usuario.getEmail() : null;
    }

    public String getContactoCelular() {
        return usuario != null ? usuario.getContactoCelular() : null;
    }

    public String getFoto() {
        return usuario != null ? usuario.getFoto() : null;
    }

    public String getUsername() {
        return usuario != null ? usuario.getUsername() : null;
    }

    public String getPassword() {
        return usuario != null ? usuario.getPassword() : null;
    }

    public Boolean getHabilitado() {
        return usuario.getHabilitado();
    }

    public Boolean esAdmin() {
        return Objects.equals(usuario.getRol(), RolUsuario.ADMIN.name());
    }
}
