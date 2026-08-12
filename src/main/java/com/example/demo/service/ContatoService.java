package com.example.demo.service;

import com.example.demo.dto.ContatoRequestDTO;
import com.example.demo.dto.ContatoResponseDTO;
import com.example.demo.model.Contato;
import com.example.demo.model.Pagina;
import com.example.demo.repository.ContatoRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final PaginaRepository paginaRepository;

    public ContatoService(ContatoRepository contatoRepository, PaginaRepository paginaRepository) {
        this.contatoRepository = contatoRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public ContatoResponseDTO criarContato(ContatoRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Contato c = new Contato();
        c.setPagina(pagina);
        c.setTipoContato(dto.tipoContato());
        c.setValorContato(dto.valorContato());
        c = contatoRepository.save(c);
        return new ContatoResponseDTO(c.getId(), pagina.getId(), c.getTipoContato(), c.getValorContato());
    }

    public List<ContatoResponseDTO> listarContatosPorPagina(Long paginaId) {
        return contatoRepository.findByPaginaId(paginaId).stream()
                .map(c -> new ContatoResponseDTO(c.getId(), paginaId, c.getTipoContato(), c.getValorContato()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarContato(Long id) {
        contatoRepository.deleteById(id);
    }
}
