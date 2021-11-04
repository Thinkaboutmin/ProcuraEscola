/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscolar.providers;

import ProcuraMelhorEscola.data.DadosLivres.DetalheEscola;
import com.google.gson.JsonArray;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * \brief Interface de chamadas da API Educação Dados Aberto.
 * 
 * @author jomar
 */
public interface EducacaoDadosAbertoAPI {
    /**
     * \brief Busca várias escolas
     *
     * 
     * Importante: A busca só é realizada caso o filtro tenha mais 
     * de 3 caracteres
     * 
     * 
     * @param nomeDasEscolas Filtro pelo nomes das escola
     * @return Objeto JSON com a lista de objetos.
     */
    @GET("escolas")
    Call<JsonArray> listarEscolas(@Query("nome") String nomeDasEscolas);
    
    
    /**
     * \brief Busca mais detalhes sobre uma escola
     * 
     * @param codigoEscola Código da escola.
     * @return Uma chamada da API que retorna DetalheEscola.
     */
    @GET("escola/{codEscola}")
    Call<DetalheEscola> detalhesEscola(@Path("codEscola") String codigoEscola);
}