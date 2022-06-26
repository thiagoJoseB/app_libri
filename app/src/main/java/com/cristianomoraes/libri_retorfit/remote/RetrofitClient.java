package com.cristianomoraes.libri_retorfit.remote;
//// 15 class RetrofitClient



/////16
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/// 17 RETROFIT
public class RetrofitClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient(String url){

        if(retrofit == null){
            /*CRIA E CONFIGURA UM OBJETO GSON**/
            Gson gson = new GsonBuilder()
                        .setLenient()
                    .create();

            /*CRIA E CONFIGURA O OBJETO DERETROFIT**/
            retrofit = new Retrofit.Builder()
                           .baseUrl(url)
                         .addConverterFactory(GsonConverterFactory.create(gson))
                           .build();

        }

        return retrofit;

    }







}
