package com.example.demo.dto;

public record UsuarioRequestDTO(
    String nomeCompleto,
    String email,
    String senha,
    boolean isAdmin,
    Long tenantId
) {}
