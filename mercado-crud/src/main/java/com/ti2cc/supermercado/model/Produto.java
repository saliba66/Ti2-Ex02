package com.ti2cc.supermercado.model;

import java.time.LocalDateTime;

public class Produto {
    private Integer id;        // pode ser null antes de inserir
    private String nome;
    private String marca;
    private String categoria;
    private double preco;
    private int estoque;
    private boolean ativo;
    private LocalDateTime criadoEm;

    public Produto() {}

    public Produto(Integer id, String nome, String marca, String categoria, double preco, int estoque, boolean ativo, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    public Produto(String nome, String marca, String categoria, double preco, int estoque, boolean ativo) {
        this(null, nome, marca, categoria, preco, estoque, ativo, null);
    }

    // getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    @Override
    public String toString() {
        return String.format("Produto{id=%s, nome='%s', marca='%s', categoria='%s', preco=%.2f, estoque=%d, ativo=%s, criadoEm=%s}",
                id, nome, marca, categoria, preco, estoque, ativo, criadoEm);
    }
}
