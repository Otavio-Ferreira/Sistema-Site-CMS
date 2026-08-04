package com.example.demo.dto;

public record LoginResponseDTO(Long id, String nome, String email, String token, Long tenantId) {}