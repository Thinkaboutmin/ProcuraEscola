package ProcuraMelhorEscolar.providers;

import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenEnvio;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenResposta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * \brief Interface para a API Firebase Auth - Token da Google.
 * 
 * @author jomar
 */
public interface FirebaseTokenAPI {
    /**
     * \brief Verifica se a sessão do usuário está válida. Caso esteja, estende
     *        o tempo de expiração.
     * 
     * Mais informações em 
     * < 
     * https://firebase.google.com/docs/reference/rest/
     *                                          auth?hl=en#section-refresh-token
     * >
     * 
     * @param apiKey Chave da API
     * @param dados Corpo da API que vai conter o token de atualização.
     * @return Uma chamada para a API. Se tudo der certo, da um refresh na 
     *         sessão
     */
    @POST("token") 
    Call<FirebaseTokenResposta> sessaoValida(
            @Query("key") String apiKey,
            @Body() FirebaseTokenEnvio dados);
}
