package com.example.demo.repository;

import com.example.demo.model.CardCta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CardCtaRepository extends JpaRepository<CardCta, Long> {
    List<CardCta> findByPaginaId(Long paginaId);
}
