package com.example.demo.repository;

import com.example.demo.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByTenantId(Long tenantId);
}
