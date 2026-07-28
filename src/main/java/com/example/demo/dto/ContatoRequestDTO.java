package com.example.demo.dto;

public record ContatoRequestDTO(
    Long paginaId,
    String tipoContato,
    String valorContato
) {}
