/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscolar.providers;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author jomar
 */
public interface FirebaseRealtimeDatabaseAPI {
    // 
    
    /**
     * 
     * @param caminho
     * @param tokenAutenticacao
     * @return 
     */
    @GET("{caminho}.json")    
    public Call<JsonElement> pegarDados(
            @Path("caminho") String caminho,
            @Query("auth") String tokenAutenticacao
    );
    
    /**
     * 
     * @param caminho
     * @param tokenAutenticacao
     * @param dados
     * @return 
     */
    @PUT("{caminho}.json")
    public Call<JsonElement> gravarDados(
            @Path("caminho") String caminho,
            @Query("auth") String tokenAutenticacao,
            @Body() String dados
    );
    
}
