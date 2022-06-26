package com.cristianomoraes.libri_retorfit.remote;


//// 18 criar uma class dentro da remote

//////APIUTIL

///19
public class APIUtil {

    public APIUtil() {
    }

    public static final String API_URL = "http://10.107.144.14:3000";

    public static RouterInterface getUsuarioInterface(){

                             //// onde esta API
        return RetrofitClient.getClient(API_URL)
                      //// AS  ROTAS
                      .create(RouterInterface.class);




    }


}
