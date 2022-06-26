package com.cristianomoraes.libri_retorfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cristianomoraes.libri_retorfit.model.Usuario;
import com.cristianomoraes.libri_retorfit.remote.APIUtil;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuario extends AppCompatActivity {

    /// 20 Recuperar os viwes

    EditText txtNome;
    EditText txtSobrenome;
    EditText txtEmail;
    EditText txtLogin;
    EditText txtSenha;
    Button btnInserir;

    RouterInterface routerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);


        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        btnInserir = findViewById(R.id.btnCadastrarUsuario);



        routerInterface = APIUtil.getUsuarioInterface();

        btnInserir.setOnClickListener(view -> {


            /// 21 pegar o construtor vazio
            Usuario usuario = new Usuario();


            /// 22
            usuario.setNome(txtNome.getText().toString());
            usuario.setSobrenome(txtSobrenome.getText().toString());
            usuario.setEmail(txtEmail.getText().toString());
            usuario.setLogin(txtLogin.getText().toString());
            usuario.setSenha(txtSenha.getText().toString());
            usuario.setFoto("foto.png");


            /// 24 chama o metodo
            addUsuario(usuario);

        });




    } /// FIM DO METODO onCREATE

    ///23 metodo addUsuario
    public void addUsuario(Usuario usuario){

                           /// verbo , rota
        Call<Usuario> call = routerInterface.addUsuario(usuario);

        call.enqueue(new Callback<Usuario>() {
            ///se deu certo
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){

                    Toast.makeText(CadastroUsuario.this,
                                  "USUARIO INSERIDO",
                                   Toast.LENGTH_LONG).show();
                }

            }


            ///se deu erro
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

                Log.d("API-ERRO",t.getMessage());

            }
        });






    }

}////FIM DA CLASSE



