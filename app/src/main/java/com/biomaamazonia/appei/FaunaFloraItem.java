package com.biomaamazonia.appei;

public class FaunaFloraItem {
    private String titulo;
    private String descricao;
    private String imagemUrl;

    // Construtor
    public FaunaFloraItem(String titulo, String descricao, String imagemUrl) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
