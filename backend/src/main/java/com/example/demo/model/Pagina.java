package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pagina")
public class Pagina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(name = "url_publica", nullable = false, unique = true)
    private String urlPublica;

    @Column(name = "titulo_pagina", nullable = false)
    private String tituloPagina;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @PrePersist
    void definirDataCriacaoSeAusente() {
        if (dataCriacao == null) {
            dataCriacao = LocalDate.now();
        }
    }

    // Relacionamentos com os componentes do CMS
    @OneToOne(mappedBy = "pagina", cascade = CascadeType.ALL)
    private Biografia biografia;

    @OneToMany(mappedBy = "pagina", cascade = CascadeType.ALL)
    private List<Contato> contatos;

    @OneToMany(mappedBy = "pagina", cascade = CascadeType.ALL)
    private List<Accordion> accordions;

    @OneToMany(mappedBy = "pagina", cascade = CascadeType.ALL)
    private List<BotaoCta> botoesCta;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }
    public String getUrlPublica() { return urlPublica; }
    public void setUrlPublica(String urlPublica) { this.urlPublica = urlPublica; }
    public String getTituloPagina() { return tituloPagina; }
    public void setTituloPagina(String tituloPagina) { this.tituloPagina = tituloPagina; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
}
