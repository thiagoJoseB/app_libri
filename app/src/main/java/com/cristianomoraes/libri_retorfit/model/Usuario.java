package com.cristianomoraes.libri_retorfit.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//// 06 -- criei o pacote model , com a class Usuario
public class Usuario {

    /// 07 private int cod_usuario;

    ///11 Mapear os elementos para quando for gson   /// MODEL
    @SerializedName("cod_usuario")
    @Expose
    private int cod_usuario;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("sobrenome")
    @Expose
    private String sobrenome;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("senha")
    @Expose
    private String senha;



    ///08 metodo para se nao tiver dados ainda (sem parametros e atributos)
    public Usuario() {
    }


    ///09 metodos para receber os dados que ja existem
    public Usuario(int cod_usuario, String nome, String sobrenome, String email, String foto, String login, String senha) {



        this.cod_usuario = cod_usuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.foto = foto;
        this.login = login;
        this.senha = senha;
    }


    ///10 metodos set e gets

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
