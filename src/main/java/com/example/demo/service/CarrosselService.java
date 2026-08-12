package com.example.demo.service;

import com.example.demo.dto.CarrosselRequestDTO;
import com.example.demo.dto.CarrosselResponseDTO;
import com.example.demo.dto.ImagemCarrosselRequestDTO;
import com.example.demo.dto.ImagemCarrosselResponseDTO;
import com.example.demo.model.Carrossel;
import com.example.demo.model.ImagemCarrossel;
import com.example.demo.model.Pagina;
import com.example.demo.repository.CarrosselRepository;
import com.example.demo.repository.ImagemCarrosselRepository;
import com.example.demo.repository.PaginaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrosselService {

    private final CarrosselRepository carrosselRepository;
    private final ImagemCarrosselRepository imagemCarrosselRepository;
    private final PaginaRepository paginaRepository;

    public CarrosselService(CarrosselRepository carrosselRepository, ImagemCarrosselRepository imagemCarrosselRepository, PaginaRepository paginaRepository) {
        this.carrosselRepository = carrosselRepository;
        this.imagemCarrosselRepository = imagemCarrosselRepository;
        this.paginaRepository = paginaRepository;
    }

    @Transactional
    public CarrosselResponseDTO criarCarrossel(CarrosselRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Carrossel c = new Carrossel();
        c.setPagina(pagina);
        c = carrosselRepository.save(c);
        return new CarrosselResponseDTO(c.getId(), pagina.getId(), List.of());
    }

    public List<CarrosselResponseDTO> listarCarrosseisPorPagina(Long paginaId) {
        return carrosselRepository.findByPaginaId(paginaId).stream()
                .map(c -> {
                    List<ImagemCarrosselResponseDTO> imagens = imagemCarrosselRepository.findByCarrosselId(c.getId()).stream()
                            .sorted((a, b) -> Integer.compare(a.getOrdemExibicao(), b.getOrdemExibicao()))
                            .map(img -> new ImagemCarrosselResponseDTO(
                                    img.getId(),
                                    c.getId(),
                                    img.getOrdemExibicao(),
                                    img.getUrlMidia(),
                                    img.getTitulo(),
                                    img.getDescricao(),
                                    img.getLinkExterno()))
                            .collect(Collectors.toList());
                    return new CarrosselResponseDTO(c.getId(), paginaId, imagens);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarCarrossel(Long id) {
        carrosselRepository.deleteById(id);
    }

    @Transactional
    public ImagemCarrosselResponseDTO adicionarImagemCarrossel(ImagemCarrosselRequestDTO dto) {
        Carrossel carrossel = carrosselRepository.findById(dto.carrosselId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrossel não encontrado"));
        ImagemCarrossel img = new ImagemCarrossel();
        img.setCarrossel(carrossel);
        img.setOrdemExibicao(dto.ordemExibicao());
        img.setUrlMidia(dto.urlMidia() != null ? dto.urlMidia() : "");
        img.setTitulo(dto.titulo() != null ? dto.titulo() : "");
        img.setDescricao(dto.descricao() != null ? dto.descricao() : "");
        img.setLinkExterno(dto.linkExterno() != null ? dto.linkExterno() : "");
        img = imagemCarrosselRepository.save(img);
        return new ImagemCarrosselResponseDTO(img.getId(), carrossel.getId(), img.getOrdemExibicao(), img.getUrlMidia(), img.getTitulo(), img.getDescricao(), img.getLinkExterno());
    }

    @Transactional
    public void deletarImagemCarrossel(Long id) {
        imagemCarrosselRepository.deleteById(id);
    }
}
