package com.example.demo.config;

import com.example.demo.model.Tenant;
import com.example.demo.model.Usuario;
import com.example.demo.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityConfigAuthenticationTest {

    @Test
    void shouldRejectRequestWithoutTokenForProtectedRoute() throws Exception {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);
        SecurityConfig.TokenAuthenticationFilter filter = new SecurityConfig.TokenAuthenticationFilter(tokenService);

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/paginas");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(401, response.getStatus());
    }

    @Test
    void shouldAcceptRequestWithValidToken() throws Exception {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);
        SecurityConfig.TokenAuthenticationFilter filter = new SecurityConfig.TokenAuthenticationFilter(tokenService);

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        Tenant tenant = new Tenant();
        tenant.setId(1L);
        usuario.setTenant(tenant);

        String token = tokenService.generateToken(usuario);

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/paginas");
        request.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(200, response.getStatus());
        assertNotNull(request.getAttribute("userEmail"));
        assertEquals("teste@email.com", request.getAttribute("userEmail"));
    }

    @Test
    void shouldRejectRequestWithInvalidToken() throws Exception {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);
        SecurityConfig.TokenAuthenticationFilter filter = new SecurityConfig.TokenAuthenticationFilter(tokenService);

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/paginas");
        request.addHeader("Authorization", "Bearer token-invalido");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(401, response.getStatus());
    }

    @Test
    void shouldAllowUserCreationWithoutToken() throws Exception {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);
        SecurityConfig.TokenAuthenticationFilter filter = new SecurityConfig.TokenAuthenticationFilter(tokenService);

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/usuarios");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldAllowUserCreationWithoutTokenForTrailingSlash() throws Exception {
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);
        SecurityConfig.TokenAuthenticationFilter filter = new SecurityConfig.TokenAuthenticationFilter(tokenService);

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/usuarios/");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertEquals(200, response.getStatus());
    }

    @Test
    void shouldPopulateSecurityContextWhenTokenIsValid() throws Exception {
        SecurityContextHolder.clearContext();
        TokenService tokenService = new TokenService("teste-secreto", 3600000L);
        SecurityConfig.TokenAuthenticationFilter filter = new SecurityConfig.TokenAuthenticationFilter(tokenService);

        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        String token = tokenService.generateToken(usuario);

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/paginas");
        request.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();

        filter.doFilter(request, response, chain);

        assertTrue(SecurityContextHolder.getContext().getAuthentication() != null);
        assertEquals("teste@email.com", SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
