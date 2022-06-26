package com.cristianomoraes.libri_retorfit.remote;

//// INTERFACE

///12 criar o pacote remote , com interface RouterInterface


///13 requisicao , como vai ser feita , onde os dados vao estar
import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RouterInterface {

    /** 14 --MAPEAMENTO DA ROTA DE INSERCAO DE USUARIOS**/
    ///ROTA
    ///Verbo http
    /////@POST("/usuario/inserirUsuario")

    @POST("/usuario/cadastrarUsuario")
    ////como os dados vao chegar na rota Requisicao Call
    /// madar pelo body um objeto de dados usuario
    Call<Usuario> addUsuario(@Body Usuario usuario);

    /** 14 --MAPEAMENTO DA ROTA DE Livros**/
    @POST("/livro/cadastrarLivro")
    Call<Livro>addLivro(@Body Livro livro);



    //**03 passo aula de recyclerView*//
    ///// List para receber os dados de livro
    @GET("/livro/listarLivro")
    Call<List<Livro>>getLivros();


    ///01 passo aula de excluir items
    @DELETE("/livro/excluirLivro/{cod_livro}")
    Call<Livro> deleteLivro(@Path("cod_livro") int cod_livro);


    ////passo 01 para a aula de editar
    @GET("/livro/listarLivroId/{cod_livro}")
    Call<List<Livro>>getLivrosId(@Path("cod_livro") int cod_livro);


    ////passo 07 para a aula de editar
    @PUT("/livro/alterarLivro")
    Call<Livro> uptadeLivro(@Body Livro livro);





}
