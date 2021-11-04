package ProcuraMelhorEscolar.providers;

import ProcuraMelhorEscola.data.DadosLivres.DetalheEscola;
import ProcuraMelhorEscola.data.DadosLivres.EscolaDaLista;
import ProcuraMelhorEscola.data.DadosLivres.InicioListaEscola;
import ProcuraMelhorEscola.utils.EsperaDadosAPI;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
        Call<JsonArray> resultAPI =
                educacaoDadosAbertoAPI.listarEscolas(nomeDasEscolas);
        
      EsperaDadosAPI<InicioListaEscola, JsonArray> conversor = 
              new EsperaDadosAPI<>(
                resultAPI,
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
}
