package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "card_cta")
public class CardCta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;

    @Column(name = "url_imagem")
    private String urlImagem;

    @Column(name = "texto_destaque")
    private String textoDestaque;

    @Column(name = "link_destino")
    private String linkDestino;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }
    public String getTextoDestaque() { return textoDestaque; }
    public void setTextoDestaque(String textoDestaque) { this.textoDestaque = textoDestaque; }
    public String getLinkDestino() { return linkDestino; }
    public void setLinkDestino(String linkDestino) { this.linkDestino = linkDestino; }
}