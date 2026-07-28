package com.example.demo.dto;

import java.math.BigDecimal;

public record PlanoResponseDTO(
    Long id,
    String nomePlano,
    BigDecimal valorMensal,
    String descricao
) {}
