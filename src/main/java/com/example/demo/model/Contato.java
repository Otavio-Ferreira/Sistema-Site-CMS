package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contato")
public class Contato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne @JoinColumn(name = "pagina_id", nullable = false)
    private Pagina pagina;
    
    @Column(name = "tipo_contato") private String tipoContato;
    @Column(name = "valor_contato") private String valorContato;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pagina getPagina() { return pagina; }
    public void setPagina(Pagina pagina) { this.pagina = pagina; }
    public String getTipoContato() { return tipoContato; }
    public void setTipoContato(String tipoContato) { this.tipoContato = tipoContato; }
    public String getValorContato() { return valorContato; }
    public void setValorContato(String valorContato) { this.valorContato = valorContato; }
}