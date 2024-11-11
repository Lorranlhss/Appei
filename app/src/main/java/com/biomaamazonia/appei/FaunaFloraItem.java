package com.biomaamazonia.appei;

public class FaunaFloraItem {
    private String titulo;
    private String descricao;
    private String imagemUrl;
    private String flora;
    private String fauna;

    // Construtor
    public FaunaFloraItem(String titulo, String descricao, String imagemUrl, String flora, String fauna) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.flora = flora;
        this.fauna = fauna;
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

    public String getFlora() {return flora;}

    public void setFlora(String flora) {
        this.flora = flora;
    }

    public String getFauna() {return fauna;}

    public void setFauna(String fauna) {
        this.fauna = fauna;
    }

}
