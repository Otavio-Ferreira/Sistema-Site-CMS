package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PlanoRequestDTO(
    String nomePlano,
    BigDecimal valorMensal,
    String descricao
) {}
