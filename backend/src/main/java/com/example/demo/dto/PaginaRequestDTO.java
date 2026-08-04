package com.example.demo.dto;

import java.time.LocalDate;

public record PaginaRequestDTO(
    Long tenantId,
    String urlPublica,
    String tituloPagina
) {}
