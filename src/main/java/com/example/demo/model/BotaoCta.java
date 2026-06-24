package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "botao_cta")
public class BotaoCta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;

    @Column(name = "texto_exibicao") private String textoExibicao;
    @Column(name = "link_destino") private String linkDestino;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
}