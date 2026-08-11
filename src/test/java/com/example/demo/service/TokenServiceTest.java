package com.example.demo.service;

import com.example.demo.model.Tenant;
import com.example.demo.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @Test
    void shouldGenerateAndValidateToken() {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("teste@email.com");
        usuario.setNomeCompleto("Teste Usuário");
        usuario.setAdmin(false);

        Tenant tenant = new Tenant();
        tenant.setId(7L);
        usuario.setTenant(tenant);

        String token = tokenService.generateToken(usuario);

        assertNotNull(token);
        assertFalse(token.isBlank());
        assertEquals("teste@email.com", tokenService.getSubject(token));
    }

    @Test
    void shouldRejectTamperedToken() {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");

        String token = tokenService.generateToken(usuario);
        String tamperedToken = token.substring(0, token.length() - 1) + "x";

        assertThrows(RuntimeException.class, () -> tokenService.getSubject(tamperedToken));
    }
}
