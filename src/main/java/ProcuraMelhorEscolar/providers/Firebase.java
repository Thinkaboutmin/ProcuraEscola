/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscolar.providers;

import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroELoginCorpo;
import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroResposta;
import ProcuraMelhorEscola.data.firebase.auth.FirebaseLoginResposta;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenEnvio;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenResposta;
import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author jomar
 */
public abstract class Firebase {
    
    /**
     * \brief Retrofit com o link de autenticação para contas com senha e email
     */
    static final private Retrofit retrofitAuth = new Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create()).
            baseUrl("https://identitytoolkit.googleapis.com/v1/").
            build();
    
    /**
     * \brief Retrofit com o link de autenticação por token (pós login).
     */
    static final private Retrofit retrofitToken = new Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create()).
            baseUrl("https://securetoken.googleapis.com/v1/").
            build();
                
    static final private Retrofit retrofitRealtimeDatabase = new Retrofit
            .Builder().addConverterFactory(GsonConverterFactory.create()).
            baseUrl("https://procuraescola-4b9c4-default-rtdb.firebaseio.com").
            build();
    
    static final private FirebaseAuthAPI firebaseAuthAPI = retrofitAuth.create(
            FirebaseAuthAPI.class
    );
    
    
    static final private FirebaseTokenAPI firebaseTokenAPI 
            = retrofitToken.create(FirebaseTokenAPI.class);
    
    static final private FirebaseRealtimeDatabaseAPI firebaseRealtimeDatabaseAPI 
            = retrofitRealtimeDatabase.create(FirebaseRealtimeDatabaseAPI.class);
            
    
    static public Call<FirebaseCadastroResposta> cadastrarUsuario(
            String apiKey,
            FirebaseCadastroELoginCorpo dados) {
        return firebaseAuthAPI.cadastrarUsuario(apiKey, dados);
    }
    
    
    static public Call<FirebaseLoginResposta> logarUsuario(
            String apiKey,
            FirebaseCadastroELoginCorpo dados) {
        return firebaseAuthAPI.logarUsuario(apiKey, dados);
    }    
    
    static public Call<FirebaseTokenResposta> sessaoValida(
            String apiKey,
            FirebaseTokenEnvio dados) {
        return firebaseTokenAPI.sessaoValida(apiKey, dados);
    }
    
    static public Call<JsonElement> pegarDados(
            String caminho,
            String tokenAutenticacao) {
        return firebaseRealtimeDatabaseAPI.pegarDados(
                caminho, 
                tokenAutenticacao
        );
    }
    
    static public Call<JsonElement> gravadorDados(
            String caminho,
            String tokenAutenticacao,
            String dados) {
        return firebaseRealtimeDatabaseAPI.gravarDados(
                caminho, 
                tokenAutenticacao, 
                dados
        );
    }
}
