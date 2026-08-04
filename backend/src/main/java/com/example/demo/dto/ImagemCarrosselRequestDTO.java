package com.example.demo.dto;

public record ImagemCarrosselRequestDTO(
    Long carrosselId,
    int ordemExibicao,
    String urlMidia,
    String titulo,
    String descricao,
    String linkExterno
) {}
