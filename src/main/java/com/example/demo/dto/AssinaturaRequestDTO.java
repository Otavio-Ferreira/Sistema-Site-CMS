package com.example.demo.dto;

import java.time.LocalDate;

public record AssinaturaRequestDTO(
    Long tenantId,
    Long planoId,
    LocalDate dataInicio,
    String statusPagamento
) {}
