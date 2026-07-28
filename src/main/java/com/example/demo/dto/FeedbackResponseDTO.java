package com.example.demo.dto;

import java.time.LocalDate;

public record FeedbackResponseDTO(
    Long id,
    Long paginaId,
    String nomeCliente,
    String textoAvaliacao,
    LocalDate dataFeedback
) {}
