package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CmsController {

    private final CmsService cmsService;

    public CmsController(CmsService cmsService) {
        this.cmsService = cmsService;
    }

    // --- PÁGINAS ---
    @PostMapping("/paginas")
    public ResponseEntity<PaginaResponseDTO> criarPagina(@RequestBody PaginaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarPagina(dto));
    }

    @GetMapping("/paginas/tenant/{tenantId}")
    public ResponseEntity<List<PaginaResponseDTO>> listarPaginasPorTenant(@PathVariable Long tenantId) {
        return ResponseEntity.ok(cmsService.listarPaginasPorTenant(tenantId));
    }

    @GetMapping("/paginas/{id}")
    public ResponseEntity<PaginaResponseDTO> buscarPaginaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.buscarPaginaPorId(id));
    }

    @DeleteMapping("/paginas/{id}")
    public ResponseEntity<Void> deletarPagina(@PathVariable Long id) {
        cmsService.deletarPagina(id);
        return ResponseEntity.noContent().build();
    }

    // --- BIOGRAFIA ---
    @PostMapping("/biografias")
    public ResponseEntity<BiografiaResponseDTO> salvarBiografia(@RequestBody BiografiaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.salvarBiografia(dto));
    }

    @GetMapping("/biografias/pagina/{paginaId}")
    public ResponseEntity<BiografiaResponseDTO> buscarBiografiaPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.buscarBiografiaPorPagina(paginaId));
    }

    // --- CONTATO ---
    @PostMapping("/contatos")
    public ResponseEntity<ContatoResponseDTO> criarContato(@RequestBody ContatoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarContato(dto));
    }

    @GetMapping("/contatos/pagina/{paginaId}")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatosPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarContatosPorPagina(paginaId));
    }

    @DeleteMapping("/contatos/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        cmsService.deletarContato(id);
        return ResponseEntity.noContent().build();
    }

    // --- ACCORDION ---
    @PostMapping("/accordions")
    public ResponseEntity<AccordionResponseDTO> criarAccordion(@RequestBody AccordionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarAccordion(dto));
    }

    @GetMapping("/accordions/pagina/{paginaId}")
    public ResponseEntity<List<AccordionResponseDTO>> listarAccordionsPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarAccordionsPorPagina(paginaId));
    }

    @DeleteMapping("/accordions/{id}")
    public ResponseEntity<Void> deletarAccordion(@PathVariable Long id) {
        cmsService.deletarAccordion(id);
        return ResponseEntity.noContent().build();
    }

    // --- BOTÃO CTA ---
    @PostMapping("/botoes-cta")
    public ResponseEntity<BotaoCtaResponseDTO> criarBotaoCta(@RequestBody BotaoCtaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarBotaoCta(dto));
    }

    @GetMapping("/botoes-cta/pagina/{paginaId}")
    public ResponseEntity<List<BotaoCtaResponseDTO>> listarBotoesCtaPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarBotoesCtaPorPagina(paginaId));
    }

    @DeleteMapping("/botoes-cta/{id}")
    public ResponseEntity<Void> deletarBotaoCta(@PathVariable Long id) {
        cmsService.deletarBotaoCta(id);
        return ResponseEntity.noContent().build();
    }

    // --- CARD CTA ---
    @PostMapping("/cards-cta")
    public ResponseEntity<CardCtaResponseDTO> criarCardCta(@RequestBody CardCtaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarCardCta(dto));
    }

    @GetMapping("/cards-cta/pagina/{paginaId}")
    public ResponseEntity<List<CardCtaResponseDTO>> listarCardsCtaPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarCardsCtaPorPagina(paginaId));
    }

    @DeleteMapping("/cards-cta/{id}")
    public ResponseEntity<Void> deletarCardCta(@PathVariable Long id) {
        cmsService.deletarCardCta(id);
        return ResponseEntity.noContent().build();
    }

    // --- FEEDBACK ---
    @PostMapping("/feedbacks")
    public ResponseEntity<FeedbackResponseDTO> criarFeedback(@RequestBody FeedbackRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarFeedback(dto));
    }

    @GetMapping("/feedbacks/pagina/{paginaId}")
    public ResponseEntity<List<FeedbackResponseDTO>> listarFeedbacksPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarFeedbacksPorPagina(paginaId));
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Void> deletarFeedback(@PathVariable Long id) {
        cmsService.deletarFeedback(id);
        return ResponseEntity.noContent().build();
    }

    // --- ARTIGO RICHTEXT ---
    @PostMapping("/artigos")
    public ResponseEntity<ArtigoRichtextResponseDTO> criarArtigo(@RequestBody ArtigoRichtextRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarArtigo(dto));
    }

    @GetMapping("/artigos/pagina/{paginaId}")
    public ResponseEntity<List<ArtigoRichtextResponseDTO>> listarArtigosPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarArtigosPorPagina(paginaId));
    }

    @DeleteMapping("/artigos/{id}")
    public ResponseEntity<Void> deletarArtigo(@PathVariable Long id) {
        cmsService.deletarArtigo(id);
        return ResponseEntity.noContent().build();
    }

    // --- CARROSSEL & IMAGENS ---
    @PostMapping("/carrosseis")
    public ResponseEntity<CarrosselResponseDTO> criarCarrossel(@RequestBody CarrosselRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarCarrossel(dto));
    }

    @GetMapping("/carrosseis/pagina/{paginaId}")
    public ResponseEntity<List<CarrosselResponseDTO>> listarCarrosseisPorPagina(@PathVariable Long paginaId) {
        return ResponseEntity.ok(cmsService.listarCarrosseisPorPagina(paginaId));
    }

    @DeleteMapping("/carrosseis/{id}")
    public ResponseEntity<Void> deletarCarrossel(@PathVariable Long id) {
        cmsService.deletarCarrossel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/imagens-carrossel")
    public ResponseEntity<ImagemCarrosselResponseDTO> adicionarImagemCarrossel(@RequestBody ImagemCarrosselRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.adicionarImagemCarrossel(dto));
    }

    @DeleteMapping("/imagens-carrossel/{id}")
    public ResponseEntity<Void> deletarImagemCarrossel(@PathVariable Long id) {
        cmsService.deletarImagemCarrossel(id);
        return ResponseEntity.noContent().build();
    }

    // --- PLANOS ---
    @PostMapping("/planos")
    public ResponseEntity<PlanoResponseDTO> criarPlano(@RequestBody PlanoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarPlano(dto));
    }

    @GetMapping("/planos")
    public ResponseEntity<List<PlanoResponseDTO>> listarPlanos() {
        return ResponseEntity.ok(cmsService.listarPlanos());
    }

    // --- ASSINATURAS ---
    @PostMapping("/assinaturas")
    public ResponseEntity<AssinaturaResponseDTO> criarAssinatura(@RequestBody AssinaturaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarAssinatura(dto));
    }

    @GetMapping("/assinaturas/tenant/{tenantId}")
    public ResponseEntity<List<AssinaturaResponseDTO>> listarAssinaturasPorTenant(@PathVariable Long tenantId) {
        return ResponseEntity.ok(cmsService.listarAssinaturasPorTenant(tenantId));
    }

    // --- USUÁRIOS ---
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cmsService.criarUsuario(dto));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(cmsService.listarUsuarios());
    }
}
