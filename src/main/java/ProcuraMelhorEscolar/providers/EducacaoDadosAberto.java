package ProcuraMelhorEscolar.providers;

import ProcuraMelhorEscola.data.DadosLivres.CidadeDoEstado;
import ProcuraMelhorEscola.data.DadosLivres.DetalheEscola;
import ProcuraMelhorEscola.data.DadosLivres.EscolaDaLista;
import ProcuraMelhorEscola.data.DadosLivres.InicioListaEscola;
import ProcuraMelhorEscola.utils.EsperaDadosAPI;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * \brief Objeto auxiliar para a realização de chamadas da API DadosLivres.
 * 
 * Aqui vamos ter as mesmas funcionalidades implementadas pelo objeto 
 * EducaoDadosAbertoAPI, porém com certas modificações em relação aos objetos
 * retornados.
 * 
 * 
 * @author jomar
 */
public abstract class EducacaoDadosAberto {       
    private final static Retrofit retrofitEdu = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()).
            baseUrl("http://educacao.dadosabertosbr.com/api/").build();
    
    
    private final static EducacaoDadosAbertoAPI educacaoDadosAbertoAPI = 
            retrofitEdu.create(EducacaoDadosAbertoAPI.class);
    
    /**
     * \brief Lista as escolas.
     * 
     * Busca as escolas da API provida pelo atributo estático 
     * \ref educaoDadosAbertoAPI
     * 
     * @param nomeDasEscolas
     * @return Um chamada que deve retornar InicioListaEscola
     */
    static public Call<InicioListaEscola> listarEscolas(String nomeDasEscolas) {       
        Call<JsonArray> chamadaAPI =
                educacaoDadosAbertoAPI.listarEscolas(nomeDasEscolas);
        
      EsperaDadosAPI<InicioListaEscola, JsonArray> conversor;
      conversor = new EsperaDadosAPI<>(
                chamadaAPI,
                EducacaoDadosAberto::cvterChamadaListaEscolas
      );
               
      return conversor;
    }
    
    /**
     * \brief Lista as escolas porém com mais paramêtros de filtragem.
     * 
     * @param nomeDasEscolas Nome das escolas a serem filtradas
     * @param estadoDasEscolas Estado das escolas a serem filtradas
     * @param cidadeDasEscolas Cidade das escolas a serem filtradas
     * @return Uma chamada que deve retornar InicioListaEscola.
     */
    static public Call<InicioListaEscola> listarEscolasBuscaAvancada(
            String nomeDasEscolas,
            String estadoDasEscolas,
            Long cidadeDasEscolas) {
        
        
        Call<JsonArray> chamadaAPI =
                educacaoDadosAbertoAPI.listarEscolasBuscaAvancada(
                        nomeDasEscolas, estadoDasEscolas, cidadeDasEscolas
                );
        
        EsperaDadosAPI<InicioListaEscola, JsonArray> conversor;
        conversor = new EsperaDadosAPI<>(
                  chamadaAPI,
                  EducacaoDadosAberto::cvterChamadaListaEscolas
        );
        
        return conversor;
    }
    
    
    /**
     * \brief Detalha os dados de uma escola.
     * 
     * Busca uma escola a partir de seu código, mostrando ainda mais informações
     * sobre ela.
     * 
     * @param codigoEscola
     * @return Um chamada que deve retornar DetalheEscola.
     */
    static public Call<DetalheEscola> detalhesEscola(String codigoEscola) {
        return educacaoDadosAbertoAPI.detalhesEscola(codigoEscola);
    }
    
    
    /**
     * \brief Busca as cidades de um estado
     * 
     * As cidades buscadas vão possuir um código que pode ser utilizado em 
     * buscas mais robustas.
     * 
     * @param estado Estado para buscar as cidades
     * @return Uma chamada que deve retornar uma lista de cidades.
     */
    static public Call<List<CidadeDoEstado>> pegarCidadesDeUmEstado(
            String estado) {
        Call<JsonArray> chamadaAPI = educacaoDadosAbertoAPI.
                                   pegarCidadesDeUmEstado(estado.toLowerCase());
        
        EsperaDadosAPI<List<CidadeDoEstado>, JsonArray> conversor;
        conversor = new EsperaDadosAPI<>(
                chamadaAPI,
                EducacaoDadosAberto::cvterChamadaCidadesDeUmEstado
        );
        
        return conversor;
    }
            
    
    /**
     * \brief Converte a lista em um objeto POJO que faça sentido
     * 
     * A lista retornada pela API contém dois índices. O primeiro índice
     * vai conter o número de elementos da pesquisa interna no banco, mesmo que
     * esses não estejam presente no retorno (não foi encontrado nenhum meio
     * para paginar esses dados...)
     * 
     * Já o segundo índice contém os dados das escolas em uma outra lista.
     * 
     * 
     * Assim esses dados são convertidos para o objeto InicioListaEscola,
     * que faz mais sentido do que uma lista com dois itens.
     *
     * 
     * 
     * @param json O array JSON retornado pela API.
     * @return O objeto InicioListaEscola.
     */
    static private InicioListaEscola cvterChamadaListaEscolas(JsonArray json) {
        InicioListaEscola obj = new InicioListaEscola();
        
        obj.resultados = json.get(0).getAsInt();
        Gson gson = new Gson();
        obj.escolas = new ArrayList<>();
        // Tipo escolas necessita de uma classe de apoio para poder ser 
        // convertida corretamente em um ArrayList.
        Type tipoEscolas = new TypeToken<ArrayList<EscolaDaLista>>(){}.getType();
        obj.escolas = gson.fromJson(json.get(1).getAsJsonArray(),
                                    tipoEscolas);
        
        return obj;
    }
    
    
    /**
     * \brief Converte a lista de String retornado pela API em uma lista de
     *        CidadeDoEstado.
     * 
     * A conversão é feita dividindo a String em dois utilizando como divisor
     * o símbolo ":". A primeira parte contém o código e a segunda contém o 
     * nome da cidade.
     * 
     * @param json
     * @return 
     */
    static private List<CidadeDoEstado> cvterChamadaCidadesDeUmEstado(
            JsonArray json) {
        List<CidadeDoEstado> ret = new ArrayList<>();
        
        // Passa por cada elemento Json que deve ser uma String e adiciona ele
        // a lista de objetos.
        json.forEach((JsonElement element) -> {
            String[] split = element.getAsString().split(":");
            
            // Só vamos proceder se contermos dois elementos após a divisão.
            if (split.length == 2) {
                long codigo = Long.parseLong(split[0].trim());
                String cidade = split[1].trim();
                
                ret.add(
                    new CidadeDoEstado(
                        codigo,
                        cidade
                    )
                );                                                        
            }
        });
        
        return ret;
    }
}
