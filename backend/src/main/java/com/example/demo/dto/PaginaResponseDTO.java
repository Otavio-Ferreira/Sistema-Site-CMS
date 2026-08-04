package com.example.demo.dto;

import java.time.LocalDate;

public record PaginaResponseDTO(
    Long id,
    Long tenantId,
    String urlPublica,
    String tituloPagina,
    LocalDate dataCriacao
) {}
