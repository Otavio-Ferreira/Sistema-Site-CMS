package com.example.demo.repository;

import com.example.demo.model.Accordion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccordionRepository extends JpaRepository<Accordion, Long> {
    List<Accordion> findByPaginaId(Long paginaId);
}
