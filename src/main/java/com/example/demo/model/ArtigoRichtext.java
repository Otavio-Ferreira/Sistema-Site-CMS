package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "artigo_richtext")
public class ArtigoRichtext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;

    @Column(nullable = false)
    private String titulo;

    @Lob
    @Column(name = "conteudo_html")
    private String conteudoHtml;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getConteudoHtml() { return conteudoHtml; }
    public void setConteudoHtml(String conteudoHtml) { this.conteudoHtml = conteudoHtml; }
    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDate dataPublicacao) { this.dataPublicacao = dataPublicacao; }
}