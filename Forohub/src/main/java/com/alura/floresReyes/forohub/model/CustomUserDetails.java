package com.alura.floresReyes.forohub.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;

    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define los roles o permisos del usuario
        return null; // Implementar según la lógica de roles/permisos
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getNombre();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implementar según la lógica de caducidad de cuenta
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implementar según la lógica de bloqueo de cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implementar según la lógica de caducidad de credenciales
    }

    @Override
    public boolean isEnabled() {
        return true; // Implementar según la lógica de estado activo/inactivo del usuario
    }
}