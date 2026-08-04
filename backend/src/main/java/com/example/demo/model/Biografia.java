package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "biografia")
public class Biografia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;
    
    @Lob @Column(name = "conteudo_texto")
    private String conteudoTexto;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public String getConteudoTexto() { return conteudoTexto; }
    public void setConteudoTexto(String conteudoTexto) { this.conteudoTexto = conteudoTexto; }
}