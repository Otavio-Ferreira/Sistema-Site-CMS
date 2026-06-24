package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plano")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_plano", nullable = false)
    private String nomePlano;

    @Column(name = "valor_mensal", nullable = false)
    private BigDecimal valorMensal;

    private String descricao;
}