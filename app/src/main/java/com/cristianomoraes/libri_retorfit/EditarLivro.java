package com.cristianomoraes.libri_retorfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.remote.APIUtil;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarLivro extends AppCompatActivity {

    ///02 aula de editar
    RouterInterface routerInterface;
    List<Livro> list = new ArrayList<Livro>();



    EditText txtTitulo;
    EditText txtDescricao;
    EditText txtFoto;
    Button btnEditar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);


        ///03 aula de editar
        txtTitulo = findViewById(R.id.txtEditarTitulo);
        txtDescricao = findViewById(R.id.txtEditarLivroDescricao);
        txtFoto = findViewById(R.id.txtEditarFoto);
        btnEditar = findViewById(R.id.btnEditarLivro);


        ///04aula de editar
        routerInterface = APIUtil.getUsuarioInterface();


        //passo 08 receber o dados da outra tela
        /**RECEBE O DADO DE CODIGO DE LIVRO A PARTIR DA ACTIVITY FEEDLIVRO***/
        int cod_livro = getIntent().getExtras().getInt("cod_livro");


        ///05 aula de editar
        Call<List<Livro>> call = routerInterface.getLivrosId(cod_livro);


        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {

                ///06 aula de editar
                list = response.body();

                txtTitulo.setText(list.get(0).getTitulo());
                txtDescricao.setText(list.get(0).getDescricao());
                txtFoto.setText(list.get(0).getImagem());


                ////passo 08 para a aula de editar
                btnEditar.setOnClickListener(view -> {

                    Livro livro = new Livro();

                    livro.setCod_livro(cod_livro);
                    livro.setTitulo(txtTitulo.getText().toString());
                    livro.setDescricao(txtDescricao.getText().toString());
                    livro.setImagem(txtFoto.getText().toString());

                    livro.setTblUsuarioCodUsuario(list.get(0).getTblUsuarioCodUsuario());

                    ////passo 09 para a aula de editar
                    Call<Livro> callUpdade = routerInterface.uptadeLivro(livro);

                    ////passo 10 para a aula de editar
                    callUpdade.enqueue(new Callback<Livro>() {
                        @Override
                        public void onResponse(Call<Livro> call, Response<Livro> response) {

                            Toast.makeText(EditarLivro.this, "LIVRO FOI EDITADO  ", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(EditarLivro.this, FeedLivro.class));
                        }



                        @Override
                        public void onFailure(Call<Livro> call, Throwable t) {


                        }
                    });
                });

            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {

            }
        });
    }


}