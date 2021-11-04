/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.utils;

import java.io.IOException;
import java.util.function.Function;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * \brief Classe que possibilita adição de alguma lógica necessária para 
 *        converter um objeto JSON complexo.
 * 
 * Esta classe não é vista pelo usuário (nós nesse caso rs), onde todo 
 * tratamento ocorre com as mesmas implementações realizadas pelo Call,
 * não necessitando mudar nenhum código além de implementar a conversão.
 * 
 * A conversão é feita pela implementação desta classe, onde deve ser provido 
 * uma função de conversão. Vide o exemplo abaixo:
 * 
 *     Call<int> AlgumaChamada = api.buscaInt();
 *     EsperaDados<int, string> converteAqui = new EsperaDadosApi<>(
 *          
 *          (int a) -> {return Integer.toString(a);}
 *     )
 * 
 * 
 * @author jomar
 * @param <T> Objeto tratado a ser retornado pela implementação.
 * @param <J> Objeto retornado pelo Call
 */
public class EsperaDadosAPI<T, J> implements Call<T> {
    private final Call<J> objetoDaChamada;    
    private final Function<J, T> hookDeConversao;
    
    public EsperaDadosAPI (
            Call<J> objetoDaChamada,
            Function<J, T> hookDeConversao
    ) {
        this.objetoDaChamada = objetoDaChamada;
        this.hookDeConversao = hookDeConversao;
    }

    @Override
    public Response execute() throws IOException {
        Response<J> resposta = this.objetoDaChamada.execute();
        T valor;
        // Cria uma nova resposta de acordo com o resultado da API.
        Response<T> novaResposta;
        if (resposta.isSuccessful()) {
            valor = this.hookDeConversao.apply(resposta.body());
            novaResposta = Response.success(valor, resposta.raw());
            
            return novaResposta;
        }
        
        novaResposta = Response.error(resposta.errorBody(), resposta.raw());
        
        return novaResposta;
    }

    @Override
    public void enqueue(Callback clbck) {
        this.objetoDaChamada.enqueue(clbck);
    }

    @Override
    public boolean isExecuted() {
        return this.objetoDaChamada.isExecuted();
    }

    @Override
    public void cancel() {
        this.objetoDaChamada.cancel();
    }

    @Override
    public boolean isCanceled() {
        return this.objetoDaChamada.isCanceled();
    }

    @Override
    public Call clone() {
        return this.objetoDaChamada.clone();
    }

    @Override
    public Request request() {
        return this.objetoDaChamada.request();
    }

    @Override
    public Timeout timeout() {
        return this.objetoDaChamada.timeout();
    }
}
