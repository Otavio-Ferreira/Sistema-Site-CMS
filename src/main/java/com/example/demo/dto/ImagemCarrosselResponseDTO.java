package com.example.demo.dto;

public record ImagemCarrosselResponseDTO(
    Long id,
    Long carrosselId,
    int ordemExibicao,
    String urlMidia,
    String titulo,
    String descricao,
    String linkExterno
) {}
