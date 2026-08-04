package com.example.demo.dto;

public record ContatoResponseDTO(
    Long id,
    Long paginaId,
    String tipoContato,
    String valorContato
) {}
