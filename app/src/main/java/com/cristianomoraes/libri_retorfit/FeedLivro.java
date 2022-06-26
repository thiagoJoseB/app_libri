package com.cristianomoraes.libri_retorfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cristianomoraes.libri_retorfit.model.Item;
import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.remote.APIUtil;
import com.cristianomoraes.libri_retorfit.remote.RouterInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedLivro extends AppCompatActivity {

    ////19passo aula de recyclerView
    RouterInterface routerInterface;
    List<Livro> list = new ArrayList<Livro>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_livro);

        ///20  passo aula de recyclerView
        //// arquivo de activity feedLivro
        routerInterface = APIUtil.getUsuarioInterface();
        recyclerView = findViewById(R.id.recyclerview);


        ////21 passo aula de recyclerView
        ///***RECEBE OS DADOS DE LIVROS VINDOS DA APIREST**///
        ///pega os dados da api
        Call<List<Livro>> call = routerInterface.getLivros();

        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {

                List<Item> itens = new ArrayList<>();
                list = response.body();

                ///22
                for(int i = 0; i< list.size(); i++){

                    itens.add(new Item(0 , list.get(i)));

                }

                recyclerView.setAdapter(new LivroAdapter(itens));

            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {

                Log.d("API-ERRO",t.getMessage());

            }
        });


    }///FIM DO METODO ONCREATE

    //**04 passo aula de recyclerView*//
    //**CLASSE DE ADAPTER DO RECYCLERVIEW**//




    /**09 passo aula de recyclerView*///
                                ///14 passa a LivroViewHolder
    private  class LivroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        ///os dados sao distinguidos , que vem do retroft
        List<Item> itens;

                           //// items , dados
        public LivroAdapter(List<Item> itens) {
            this.itens = itens;

        }///FIM DO METODO CONSTRUTOR DE LIVRO ADAPTER


        /////15 metdodos
        /// montar a estrutura de views
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ///18
            ///*INFLA A ESTRUTURA XML E OS DADOS REFERENTES A LIVRO*/////
            if(viewType == 0){
                return new LivroAdapter.LivroViewHolder(
                        LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_container_livro,parent, false)
                );
            }

            return null;
        }


        ///pegar os dados
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            ///17 passo aula de recyclerView
            ////**RECUPERA OS DADOS DE LIVRO**/////
            if(getItemViewType(position) == 0){

                Livro livro = (Livro) itens.get(position).getObject();
                ((LivroAdapter.LivroViewHolder) holder).setLivroData(livro);
            }

//            ////**RECUPERA OS DADOS DE HQ**/////
//            if(getItemViewType(position) == 1){}
//
//            ////**RECUPERA OS DADOS DE MANGA**/////
//            if(getItemViewType(position) == 2){}
        }


        ///contagem dos items , da api
        @Override
        public int getItemCount() {
            return itens.size();
        }

        ////16 passo aula de recyclerView
        ////***RECUPERA O TIPO DE OBJETO DE ITEM***////
        public int getItemViewCount(int position){
                        /// posicao do objeto /// tipo dele livro , hq, manga
            return itens.get(position).getType();

        }

        //**CLASSE DE VIEWHOLDER DA RECYCLERVIEW**//

        /**10 passo aula de recyclerView*///
        class LivroViewHolder extends RecyclerView.ViewHolder{

            ///11
            private TextView txtTitulo, txtDescricao;
            private  int cod_livro;


            ///10metodo construtor
            public LivroViewHolder(@NonNull View itemView) {
                super(itemView);

                ///12 pegar da View oq eu quero
                txtTitulo = itemView.findViewById(R.id.txtCardTituloLivro);
                txtDescricao = itemView.findViewById(R.id.txtCardDescricaoLivro);


                //***TRATAMENTO DE CLIQUE PARA ALTERACAO E EXCLUSAO DE LIVROS ***///
                ///02 passo aula de excluir items
                itemView.setOnClickListener(view -> {

                    /**
                     * CHAMA DOIS METODOS
                     * setMessagem -> Configura a mensagem da instrucao para usuario
                     *
                     * setPossitiveButton -
                     *
                     * setNegativeButton
                     *
                     *
                     *
                     *
                     *
                     * **/
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(FeedLivro.this)
                            .setMessage("ESCOLHA A ACAO DESEJADA EXECUTAR")
                            .setNegativeButton("ALTERAR",(dialog1, wicht) ->{
                                ///06 alterar

                                Intent intent = new Intent(FeedLivro.this, EditarLivro.class);

                                /// 07 mandar o texto para aoutra activity
                                intent.putExtra("cod_livro" , cod_livro);
//                                intent.putExtra("cod_" , cod_livro);



                                startActivity(intent);

                            })
                            .setPositiveButton("DELETAR" ,(dialog1, wicht) ->{

                                ///PASSO 03 DA AULA DE EXCLUIR
                                routerInterface = APIUtil.getUsuarioInterface();

                                Call<Livro> call = routerInterface.deleteLivro(cod_livro);

                                ///PASSO 04 DA AULA DE EXCLUIR
                                call.enqueue(new Callback<Livro>() {
                                    @Override
                                    public void onResponse(Call<Livro> call, Response<Livro> response) {
                                        Toast.makeText(FeedLivro.this,
                                                "Livro excluido com sucesso",Toast.LENGTH_SHORT).show();

                                        ///05 quando o arquivo é apagado , atualiza e a tela fica limpa
                                        startActivity(new Intent(FeedLivro.this,FeedLivro.class));


                                    }

                                    @Override
                                    public void onFailure(Call<Livro> call, Throwable t) {

                                    }
                                });



                            });

                    alertDialog.show();

                });









            }///FIM DO METODO CONSTRUTOR DE LIVROVIEWHOLDER

            ///13 receber um livro
            public void setLivroData(Livro livro){


                ///ejetar os dados
                txtTitulo.setText(livro.getTitulo());
                txtDescricao.setText(livro.getDescricao());
                cod_livro = livro.getCod_livro();
            }



        }///FIM DA CLASSE  DE LIVROVIEWHOLDER


    }///FIM DA CLASSE LIVRO ADAPTER



}///// FIM DA CLASSE FEEDLIVRO









