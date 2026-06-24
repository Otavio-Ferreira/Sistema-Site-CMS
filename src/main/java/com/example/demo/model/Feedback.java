package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Lob
    @Column(name = "texto_avaliacao")
    private String textoAvaliacao;

    @Column(name = "data_feedback")
    private LocalDate dataFeedback;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public String getTextoAvaliacao() { return textoAvaliacao; }
    public void setTextoAvaliacao(String textoAvaliacao) { this.textoAvaliacao = textoAvaliacao; }
    public LocalDate getDataFeedback() { return dataFeedback; }
    public void setDataFeedback(LocalDate dataFeedback) { this.dataFeedback = dataFeedback; }
}