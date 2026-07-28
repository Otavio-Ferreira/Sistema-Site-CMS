package com.example.demo.dto;

public record CardCtaRequestDTO(
    Long paginaId,
    String urlImagem,
    String textoDestaque,
    String linkDestino
) {}
