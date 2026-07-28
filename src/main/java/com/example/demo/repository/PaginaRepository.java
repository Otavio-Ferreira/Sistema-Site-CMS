package com.example.demo.repository;

import com.example.demo.model.Pagina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PaginaRepository extends JpaRepository<Pagina, Long> {
    List<Pagina> findByTenantId(Long tenantId);
    Optional<Pagina> findByUrlPublica(String urlPublica);
}
