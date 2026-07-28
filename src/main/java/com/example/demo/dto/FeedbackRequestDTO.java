package com.example.demo.dto;

import java.time.LocalDate;

public record FeedbackRequestDTO(
    Long paginaId,
    String nomeCliente,
    String textoAvaliacao,
    LocalDate dataFeedback
) {}
