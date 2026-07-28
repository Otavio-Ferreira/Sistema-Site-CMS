package com.example.demo.repository;

import com.example.demo.model.ArtigoRichtext;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArtigoRichtextRepository extends JpaRepository<ArtigoRichtext, Long> {
    List<ArtigoRichtext> findByPaginaId(Long paginaId);
}
