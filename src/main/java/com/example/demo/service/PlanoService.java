package com.example.demo.service;

import com.example.demo.dto.PlanoRequestDTO;
import com.example.demo.dto.PlanoResponseDTO;
import com.example.demo.model.Plano;
import com.example.demo.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    @Transactional
    public PlanoResponseDTO criarPlano(PlanoRequestDTO dto) {
        Plano p = new Plano();
        p.setNomePlano(dto.nomePlano());
        p.setValorMensal(dto.valorMensal());
        p.setDescricao(dto.descricao());
        p = planoRepository.save(p);
        return new PlanoResponseDTO(p.getId(), p.getNomePlano(), p.getValorMensal(), p.getDescricao());
    }

    public List<PlanoResponseDTO> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(p -> new PlanoResponseDTO(p.getId(), p.getNomePlano(), p.getValorMensal(), p.getDescricao()))
                .collect(Collectors.toList());
    }
}
