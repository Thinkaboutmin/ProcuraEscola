/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola;

import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroELoginCorpo;
import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroResposta;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenEnvio;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenResposta;
import ProcuraMelhorEscolar.providers.FirebaseAuthAPI;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 * @author jomar
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(
            "https://identitytoolkit.googleapis.com/v1/"
        ).addConverterFactory(GsonConverterFactory.create())
        .build();
        
        FirebaseAuthAPI api = retrofit.create(FirebaseAuthAPI.class);
        
        var corpo = new FirebaseTokenEnvio(
                "AFxQ4_rFifNIXq2PVwQgWOoOO0HrT_4-8Rw6QH0Pw1xwc6QoH-_IkUkLsHL4L-NBHmSccOv8LmpCNBkncPmDjWlFXBvyxIPvBga0JP7g5WhOmrsut8u1nxjrj6NGrdo7zgA4W18UW59H3vj1UROWc_6sYCzLaxZ3MfnIxbvSwjHpES0Q8B3Fhcn9gmng3SNPlWfYGCFw0co4U32P-_sFc934yax8Jv8BB-9AyCiknXjnBbbtUljutpU"
        );
        
        var call =  api.sessaoValida(
                "AIzaSyDJ4g9Kd3zLQfgSkH1hJy-U9RyN8m9obAw", 
                corpo
        );
        
        var data = call.execute();
        
        
        if (data.isSuccessful()) {
            var answer = data.body();
                       
            System.out.println(answer.tokenDeAtualizacao);
        } else {
            var answer = data.errorBody();
            
            System.out.println(answer.string());
        }
        
        
    }
    
}
