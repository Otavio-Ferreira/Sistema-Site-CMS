package com.example.demo.dto;

public record UsuarioResponseDTO(
    Long id,
    String nomeCompleto,
    String email,
    boolean isAdmin,
    Long tenantId
) {}
