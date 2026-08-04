package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagem_carrossel")
public class ImagemCarrossel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrossel_id", nullable = false)
    private Carrossel carrossel;

    @Column(name = "ordem_exibicao")
    private int ordemExibicao;

    @Column(name = "url_midia", nullable = false)
    private String urlMidia;

    private String titulo;

    @Lob
    private String descricao;

    @Column(name = "link_externo")
    private String linkExterno;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Carrossel getCarrossel() { return carrossel; }
    public void setCarrossel(Carrossel carrossel) { this.carrossel = carrossel; }
    public int getOrdemExibicao() { return ordemExibicao; }
    public void setOrdemExibicao(int ordemExibicao) { this.ordemExibicao = ordemExibicao; }
    public String getUrlMidia() { return urlMidia; }
    public void setUrlMidia(String urlMidia) { this.urlMidia = urlMidia; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getLinkExterno() { return linkExterno; }
    public void setLinkExterno(String linkExterno) { this.linkExterno = linkExterno; }
}