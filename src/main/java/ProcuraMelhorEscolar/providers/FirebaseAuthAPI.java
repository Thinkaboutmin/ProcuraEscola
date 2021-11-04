/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscolar.providers;

import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroELoginCorpo;
import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroResposta;
import ProcuraMelhorEscola.data.firebase.auth.FirebaseLoginResposta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * \brief Interface para a API Firebase Auth - Auth da Google.
 * 
 * 
 * @author jomar
 */
public interface FirebaseAuthAPI {    
    /**
     * \brief Cadastra um usuário no Firebase com email e senha.
     * 
     * O login é controlado principalmente pelo token de atualização.
     * A razão disso é por causa da natureza de uma API REST.
     * 
     * Mais informações disponíveis em:
     * 
     * <
     * https://firebase.google.com/docs/reference/rest/
     *                                  auth?hl=en#section-create-email-password
     * >
     * 
     * @param apiKey Chave da API Firebase
     * @param dados Dados para ser enviado a API no corpo da requisição.
     * @return Uma chamada do Firebase com o retorno do FirebaseCadastroResposta
     */
    @POST("./accounts:signUp")
    Call<FirebaseCadastroResposta> cadastrarUsuario(
            @Query("key") String apiKey,
            @Body() FirebaseCadastroELoginCorpo dados            
    );
    
    
    /**
     * \brief Loga o usuário ao Firebase.
     * 
     * O login é controlado principalmente pelo token de atualização.
     * A razão disso é por causa da natureza de uma API REST.
     * 
     * @param apiKey Chave da API Firebase
     * @param dados Dados para ser enviado a API no corpo da requisição.
     * @return Uma chamada do Firebase com o retorno do FirebaseLoginResposta
     */
    @POST("./accounts:signIn")
    Call<FirebaseLoginResposta> logarUsuario(
            @Query("key") String apiKey,
            @Body() FirebaseCadastroELoginCorpo dados
    );    
}
