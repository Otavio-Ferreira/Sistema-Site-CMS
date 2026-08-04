package com.example.demo.repository;

import com.example.demo.model.ImagemCarrossel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImagemCarrosselRepository extends JpaRepository<ImagemCarrossel, Long> {
    List<ImagemCarrossel> findByCarrosselId(Long carrosselId);
}
