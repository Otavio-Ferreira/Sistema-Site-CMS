package com.example.demo.repository;

import com.example.demo.model.BotaoCta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BotaoCtaRepository extends JpaRepository<BotaoCta, Long> {
    List<BotaoCta> findByPaginaId(Long paginaId);
}
