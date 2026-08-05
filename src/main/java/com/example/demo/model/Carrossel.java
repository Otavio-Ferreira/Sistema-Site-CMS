package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carrossel")
public class Carrossel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;

    // Relacionamento 1:N com as imagens que compõem o carrossel
    @OneToMany(mappedBy = "carrossel", cascade = CascadeType.ALL)
    private List<ImagemCarrossel> imagens;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public List<ImagemCarrossel> getImagens() { return imagens; }
    public void setImagens(List<ImagemCarrossel> imagens) { this.imagens = imagens; }
}