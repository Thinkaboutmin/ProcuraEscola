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
     * @return Chamada com retorno de objeto JSON contendo lista de objetos.
     */
    @GET("escolas")
    Call<JsonArray> listarEscolas(@Query("nome") String nomeDasEscolas);
    
    /**
     * \brief Busca várias escolas porém mais opções de filtragem.
     * 
     * A busca pode ser feita passando o nome da escola ou o estado ou a
     * cidade. Porém, algum dos três tem que ser passado para ser aceito pela
     * API.
     * 
     * @param nomeDasEscolas Filtro pelo nomes das escolas.
     * @param estado Filtro pelo estado das escolas
     * @param cidade Filtro pela cidade das escolas.
     * @return Chamada com retorno de objeto JSON contendo lista de objetos.
     */
    @GET("escolas/buscaavancada")
    Call<JsonArray> listarEscolasBuscaAvancada(            
        @Query("nome") String nomeDasEscolas,
        @Query("estado") String estado,
        @Query("cidade") Long cidade
    );
    
    
    /**
     * \brief Busca mais detalhes sobre uma escola
     * 
     * @param codigoEscola Código da escola.
     * @return Uma chamada da API que retorna DetalheEscola.
     */
    @GET("escola/{codEscola}")
    Call<DetalheEscola> detalhesEscola(@Path("codEscola") String codigoEscola);
    
    
    /**
     * \brief Busca as cidades de um estado
     * 
     * 
     * @param estado Sigla do estado a pegar as cidades.
     * @return Uma chamada da API que retorna a lista de cidades. 
     */
    @GET("cidades/{estado}")
    Call<JsonArray> pegarCidadesDeUmEstado(@Path("estado") String estado);
    
}