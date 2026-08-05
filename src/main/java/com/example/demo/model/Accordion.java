package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accordion")
public class Accordion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;

    @Column(name = "pergunta_titulo") private String perguntaTitulo;
    @Lob @Column(name = "resposta_conteudo") private String respostaConteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public String getPerguntaTitulo() { return perguntaTitulo; }
    public void setPerguntaTitulo(String perguntaTitulo) { this.perguntaTitulo = perguntaTitulo; }
    public String getRespostaConteudo() { return respostaConteudo; }
    public void setRespostaConteudo(String respostaConteudo) { this.respostaConteudo = respostaConteudo; }
}