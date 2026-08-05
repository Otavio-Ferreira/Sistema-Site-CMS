package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsService {

    private final PaginaRepository paginaRepository;
    private final TenantRepository tenantRepository;
    private final BiografiaRepository biografiaRepository;
    private final ContatoRepository contatoRepository;
    private final AccordionRepository accordionRepository;
    private final BotaoCtaRepository botaoCtaRepository;
    private final CardCtaRepository cardCtaRepository;
    private final FeedbackRepository feedbackRepository;
    private final ArtigoRichtextRepository artigoRichtextRepository;
    private final CarrosselRepository carrosselRepository;
    private final ImagemCarrosselRepository imagemCarrosselRepository;
    private final PlanoRepository planoRepository;
    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;

    public CmsService(PaginaRepository paginaRepository, TenantRepository tenantRepository,
                      BiografiaRepository biografiaRepository, ContatoRepository contatoRepository,
                      AccordionRepository accordionRepository, BotaoCtaRepository botaoCtaRepository,
                      CardCtaRepository cardCtaRepository, FeedbackRepository feedbackRepository,
                      ArtigoRichtextRepository artigoRichtextRepository, CarrosselRepository carrosselRepository,
                      ImagemCarrosselRepository imagemCarrosselRepository, PlanoRepository planoRepository,
                      AssinaturaRepository assinaturaRepository, UsuarioRepository usuarioRepository) {
        this.paginaRepository = paginaRepository;
        this.tenantRepository = tenantRepository;
        this.biografiaRepository = biografiaRepository;
        this.contatoRepository = contatoRepository;
        this.accordionRepository = accordionRepository;
        this.botaoCtaRepository = botaoCtaRepository;
        this.cardCtaRepository = cardCtaRepository;
        this.feedbackRepository = feedbackRepository;
        this.artigoRichtextRepository = artigoRichtextRepository;
        this.carrosselRepository = carrosselRepository;
        this.imagemCarrosselRepository = imagemCarrosselRepository;
        this.planoRepository = planoRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // --- PAGINA ---
    @Transactional
    public PaginaResponseDTO criarPagina(PaginaRequestDTO dto) {
        Tenant tenant = tenantRepository.findById(dto.tenantId())
                .orElseThrow(() -> new RuntimeException("Tenant não encontrado"));
        Pagina pagina = new Pagina();
        pagina.setTenant(tenant);
        pagina.setUrlPublica(dto.urlPublica());
        pagina.setTituloPagina(dto.tituloPagina());
        pagina.setDataCriacao(LocalDate.now());
        pagina = paginaRepository.save(pagina);
        return new PaginaResponseDTO(pagina.getId(), tenant.getId(), pagina.getUrlPublica(), pagina.getTituloPagina(), pagina.getDataCriacao());
    }

    public List<PaginaResponseDTO> listarPaginasPorTenant(Long tenantId) {
        return paginaRepository.findByTenantId(tenantId).stream()
                .map(p -> new PaginaResponseDTO(p.getId(), p.getTenant().getId(), p.getUrlPublica(), p.getTituloPagina(), p.getDataCriacao()))
                .collect(Collectors.toList());
    }

    public PaginaResponseDTO buscarPaginaPorId(Long id) {
        Pagina p = paginaRepository.findById(id).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        return new PaginaResponseDTO(p.getId(), p.getTenant().getId(), p.getUrlPublica(), p.getTituloPagina(), p.getDataCriacao());
    }

    @Transactional
    public void deletarPagina(Long id) {
        paginaRepository.deleteById(id);
    }

    // --- BIOGRAFIA ---
    @Transactional
    public BiografiaResponseDTO salvarBiografia(BiografiaRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Biografia bio = biografiaRepository.findByPaginaId(dto.paginaId()).orElse(new Biografia());
        bio.setPagina(pagina);
        bio.setConteudoTexto(dto.conteudoTexto());
        bio = biografiaRepository.save(bio);
        return new BiografiaResponseDTO(bio.getId(), pagina.getId(), bio.getConteudoTexto());
    }

    public BiografiaResponseDTO buscarBiografiaPorPagina(Long paginaId) {
        Biografia bio = biografiaRepository.findByPaginaId(paginaId).orElseThrow(() -> new RuntimeException("Biografia não encontrada"));
        return new BiografiaResponseDTO(bio.getId(), paginaId, bio.getConteudoTexto());
    }

    // --- CONTATO ---
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

    // --- ACCORDION ---
    @Transactional
    public AccordionResponseDTO criarAccordion(AccordionRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Accordion a = new Accordion();
        a.setPagina(pagina);
        a.setPerguntaTitulo(dto.perguntaTitulo());
        a.setRespostaConteudo(dto.respostaConteudo());
        a = accordionRepository.save(a);
        return new AccordionResponseDTO(a.getId(), pagina.getId(), a.getPerguntaTitulo(), a.getRespostaConteudo());
    }

    public List<AccordionResponseDTO> listarAccordionsPorPagina(Long paginaId) {
        return accordionRepository.findByPaginaId(paginaId).stream()
                .map(a -> new AccordionResponseDTO(a.getId(), paginaId, a.getPerguntaTitulo(), a.getRespostaConteudo()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarAccordion(Long id) {
        accordionRepository.deleteById(id);
    }

    // --- BOTAO CTA ---
    @Transactional
    public BotaoCtaResponseDTO criarBotaoCta(BotaoCtaRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        BotaoCta b = new BotaoCta();
        b.setPagina(pagina);
        b.setTextoExibicao(dto.textoExibicao());
        b.setLinkDestino(dto.linkDestino());
        b = botaoCtaRepository.save(b);
        return new BotaoCtaResponseDTO(b.getId(), pagina.getId(), b.getTextoExibicao(), b.getLinkDestino());
    }

    public List<BotaoCtaResponseDTO> listarBotoesCtaPorPagina(Long paginaId) {
        return botaoCtaRepository.findByPaginaId(paginaId).stream()
                .map(b -> new BotaoCtaResponseDTO(b.getId(), paginaId, b.getTextoExibicao(), b.getLinkDestino()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarBotaoCta(Long id) {
        botaoCtaRepository.deleteById(id);
    }

    // --- CARD CTA ---
    @Transactional
    public CardCtaResponseDTO criarCardCta(CardCtaRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        CardCta c = new CardCta();
        c.setPagina(pagina);
        c.setUrlImagem(dto.urlImagem());
        c.setTextoDestaque(dto.textoDestaque());
        c.setLinkDestino(dto.linkDestino());
        c = cardCtaRepository.save(c);
        return new CardCtaResponseDTO(c.getId(), pagina.getId(), c.getUrlImagem(), c.getTextoDestaque(), c.getLinkDestino());
    }

    public List<CardCtaResponseDTO> listarCardsCtaPorPagina(Long paginaId) {
        return cardCtaRepository.findByPaginaId(paginaId).stream()
                .map(c -> new CardCtaResponseDTO(c.getId(), paginaId, c.getUrlImagem(), c.getTextoDestaque(), c.getLinkDestino()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarCardCta(Long id) {
        cardCtaRepository.deleteById(id);
    }

    // --- FEEDBACK ---
    @Transactional
    public FeedbackResponseDTO criarFeedback(FeedbackRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        Feedback f = new Feedback();
        f.setPagina(pagina);
        f.setNomeCliente(dto.nomeCliente());
        f.setTextoAvaliacao(dto.textoAvaliacao());
        f.setDataFeedback(dto.dataFeedback() != null ? dto.dataFeedback() : LocalDate.now());
        f = feedbackRepository.save(f);
        return new FeedbackResponseDTO(f.getId(), pagina.getId(), f.getNomeCliente(), f.getTextoAvaliacao(), f.getDataFeedback());
    }

    public List<FeedbackResponseDTO> listarFeedbacksPorPagina(Long paginaId) {
        return feedbackRepository.findByPaginaId(paginaId).stream()
                .map(f -> new FeedbackResponseDTO(f.getId(), paginaId, f.getNomeCliente(), f.getTextoAvaliacao(), f.getDataFeedback()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    // --- ARTIGO RICHTEXT ---
    @Transactional
    public ArtigoRichtextResponseDTO criarArtigo(ArtigoRichtextRequestDTO dto) {
        Pagina pagina = paginaRepository.findById(dto.paginaId()).orElseThrow(() -> new RuntimeException("Página não encontrada"));
        ArtigoRichtext a = new ArtigoRichtext();
        a.setPagina(pagina);
        a.setTitulo(dto.titulo());
        a.setConteudoHtml(dto.conteudoHtml());
        a.setDataPublicacao(dto.dataPublicacao() != null ? dto.dataPublicacao() : LocalDate.now());
        a = artigoRichtextRepository.save(a);
        return new ArtigoRichtextResponseDTO(a.getId(), pagina.getId(), a.getTitulo(), a.getConteudoHtml(), a.getDataPublicacao());
    }

    public List<ArtigoRichtextResponseDTO> listarArtigosPorPagina(Long paginaId) {
        return artigoRichtextRepository.findByPaginaId(paginaId).stream()
                .map(a -> new ArtigoRichtextResponseDTO(a.getId(), paginaId, a.getTitulo(), a.getConteudoHtml(), a.getDataPublicacao()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarArtigo(Long id) {
        artigoRichtextRepository.deleteById(id);
    }

    // --- CARROSSEL & IMAGEM CARROSSEL ---
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

    // --- PLANOS ---
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

    // --- ASSINATURAS ---
    @Transactional
    public AssinaturaResponseDTO criarAssinatura(AssinaturaRequestDTO dto) {
        Tenant tenant = tenantRepository.findById(dto.tenantId()).orElseThrow(() -> new RuntimeException("Tenant não encontrado"));
        Plano plano = planoRepository.findById(dto.planoId()).orElseThrow(() -> new RuntimeException("Plano não encontrado"));
        Assinatura a = new Assinatura();
        a.setTenant(tenant);
        a.setPlano(plano);
        a.setDataInicio(dto.dataInicio());
        a.setStatusPagamento(dto.statusPagamento());
        a = assinaturaRepository.save(a);
        return new AssinaturaResponseDTO(a.getId(), tenant.getId(), tenant.getNome(), plano.getId(), plano.getNomePlano(), a.getDataInicio(), a.getStatusPagamento());
    }

    public List<AssinaturaResponseDTO> listarAssinaturasPorTenant(Long tenantId) {
        return assinaturaRepository.findByTenantId(tenantId).stream()
                .map(a -> new AssinaturaResponseDTO(a.getId(), a.getTenant().getId(), a.getTenant().getNome(), a.getPlano().getId(), a.getPlano().getNomePlano(), a.getDataInicio(), a.getStatusPagamento()))
                .collect(Collectors.toList());
    }

    // --- USUARIOS ---
    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        Tenant tenant = dto.tenantId() != null ? tenantRepository.findById(dto.tenantId()).orElse(null) : null;
        Usuario u = new Usuario();
        u.setNomeCompleto(dto.nomeCompleto());
        u.setEmail(dto.email());
        u.setSenhaHash(dto.senha());
        u.setAdmin(dto.isAdmin());
        u.setTenant(tenant);
        u = usuarioRepository.save(u);
        return new UsuarioResponseDTO(u.getId(), u.getNomeCompleto(), u.getEmail(), u.isAdmin(), tenant != null ? tenant.getId() : null);
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNomeCompleto(), u.getEmail(), u.isAdmin(), u.getTenant() != null ? u.getTenant().getId() : null))
                .collect(Collectors.toList());
    }
}
