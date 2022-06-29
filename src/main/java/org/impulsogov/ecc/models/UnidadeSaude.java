package org.impulsogov.ecc.models;

public class UnidadeSaude {

    private Long id;
    private String cnes;
    private String nome;
    private String nomeFiltro;
    private String bairroNome;
    private Integer registroValido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFiltro() {
        return nomeFiltro;
    }

    public void setNomeFiltro(String nomeFiltro) {
        this.nomeFiltro = nomeFiltro;
    }

    public String getBairroNome() {
        return bairroNome;
    }

    public void setBairroNome(String bairroNome) {
        this.bairroNome = bairroNome;
    }

    public Integer getRegistroValido() {
        return registroValido;
    }

    public void setRegistroValido(Integer registroValido) {
        this.registroValido = registroValido;
    }
}

