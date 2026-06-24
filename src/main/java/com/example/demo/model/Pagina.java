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

    // Relacionamentos com os componentes do CMS
    @OneToOne(mappedBy = "pagina", cascade = CascadeType.ALL)
    private Biografia biografia;

    @OneToMany(mappedBy = "pagina", cascade = CascadeType.ALL)
    private List<Contato> contatos;

    @OneToMany(mappedBy = "pagina", cascade = CascadeType.ALL)
    private List<Accordion> accordions;

    @OneToMany(mappedBy = "pagina", cascade = CascadeType.ALL)
    private List<BotaoCta> botoesCta;
}