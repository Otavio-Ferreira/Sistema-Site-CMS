package com.example.demo.repository;

import com.example.demo.model.Biografia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BiografiaRepository extends JpaRepository<Biografia, Long> {
    Optional<Biografia> findByPaginaId(Long paginaId);
}
