package com.example.demo.dto;

public record CardCtaResponseDTO(
    Long id,
    Long paginaId,
    String urlImagem,
    String textoDestaque,
    String linkDestino
) {}
