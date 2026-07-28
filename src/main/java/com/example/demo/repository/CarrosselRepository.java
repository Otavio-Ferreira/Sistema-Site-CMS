package com.example.demo.repository;

import com.example.demo.model.Carrossel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarrosselRepository extends JpaRepository<Carrossel, Long> {
    List<Carrossel> findByPaginaId(Long paginaId);
}
