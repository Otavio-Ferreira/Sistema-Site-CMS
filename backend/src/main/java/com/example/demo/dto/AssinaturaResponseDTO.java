package com.example.demo.dto;

import java.time.LocalDate;

public record AssinaturaResponseDTO(
    Long id,
    Long tenantId,
    String nomeTenant,
    Long planoId,
    String nomePlano,
    LocalDate dataInicio,
    String statusPagamento
) {}
