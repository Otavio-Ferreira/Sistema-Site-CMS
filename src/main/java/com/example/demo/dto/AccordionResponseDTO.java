package com.example.demo.dto;

public record AccordionResponseDTO(
    Long id,
    Long paginaId,
    String perguntaTitulo,
    String respostaConteudo
) {}
