/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.session;

import ProcuraMelhorEscola.adapters.session.AdaptadorDataDaSessao;
import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroELoginCorpo;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenEnvio;
import ProcuraMelhorEscola.utils.Configuracoes;
import ProcuraMelhorEscola.utils.Erro;
import ProcuraMelhorEscolar.providers.Firebase;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *  \brief Classe singleton que contém os dados do login do usuário.
 * 
 * Os dados do usuário são basicamente:
 *  - O email do usuário
 *  - O id local do projeto do Firebase do 
 * 
 * @author jomar
 */
public class Sessao {
    private static Sessao __singleton;
    
    
    // Adapta os dados recebidos do login e de uma atualização de sessão.
    private final AdaptadorDataDaSessao dadosDaSessao = 
                                                    new AdaptadorDataDaSessao();
    
    /**
     * Data e hora local do login. Utilizado para determinar se o usuário está
     * com a sessão válida ou não.
     */
    private LocalDateTime dataEHoraParaExpirar;
    
    // Controle para não ocorrer mais de uma chamada em outras threads.
    volatile private boolean acaoSendoRealizada = false;
    
    
    private Sessao() {}    
        
    
    /**
     * \brief Retorna os dados da sessão.
     * 
     * @return Dados da sessão.
     */
    public DataDaSessao pegarDados() {
        return this.dadosDaSessao;
    }
    
    /**
     * \brief Verificação básica se houve pelo menos alguma tentativa de login
     *        ou se usuário continua logado.
     * 
     *  A verificação é feita localmente, sem a utilização de uma API provida
     *  da internet.
     * 
     * @return Se o usuário está com a sessão válida ou não.
     */
    public boolean estaLogado() {
        if (this.dataEHoraParaExpirar == null) {
            return false;
        }
        
        
        return this.dataEHoraParaExpirar.isBefore(LocalDateTime.now());       
    }
    
    
    /**
     * \brief Loga o usuário.Atenção, esta chamada faz uma requisição para 
        a internet de forma síncrona.O login do usuário 
     *
     * @param dados Dados que vai ser utilizado no login.
     * @return Algum erro que pode ter acontencido durante o login.
     * @throws java.io.IOException
     */
    public Erro<String> logar(
        FirebaseCadastroELoginCorpo dados
    ) throws IOException {
        
        
        
        acaoSendoRealizada = true;
        
        var chamada = Firebase.logarUsuario(
                Configuracoes.chaveDaApi,
                dados);
        
        
        var resposta =  chamada.execute();
        
        
        if (!resposta.isSuccessful()) {
            acaoSendoRealizada = false;
            if (resposta.errorBody() != null) {
                return new Erro<> (
                        true,
                        resposta.errorBody().string()
                );
            }
            
            return new Erro<> (
                true,
                "Algum erro aconteceu."
            );    
        }
        
        acaoSendoRealizada = false;
        
        this.dadosDaSessao.adaptar(resposta.body());
        this.definirTempoDeLogin();
        
        return new Erro<>(false, "");
    }
    
    /**
     * \brief Cadastra um usuário e inicia a sua sessão
     * 
     * Cadastra o usuário no Firebase.
     * 
     * @param dados Dados para cadastrar o usuário.
     * @return Um erro caso algo inexperado tenha ocorrido.
     * @throws java.io.IOException
     */
    public Erro<String> registrar(FirebaseCadastroELoginCorpo dados) 
            throws IOException {
        if(acaoSendoRealizada)  {
            return new Erro<>(
               true,
               "Não é possível registrar o usuário no momento."
            );
        }
       
        var erroValidacao = this.validarDados(dados);
        if (erroValidacao.teveErro) {
            return erroValidacao;
        }
        
        acaoSendoRealizada = true;
        
        var chamada = Firebase.cadastrarUsuario(
                Configuracoes.chaveDaApi,
                dados
        );
        
        var resposta = chamada.execute();
        
        if (!resposta.isSuccessful()) {
            acaoSendoRealizada = false;
            
            if (resposta.errorBody() != null) {
                return new Erro<>(
                    true,
                    resposta.errorBody().string()
                );
            }
            
            return new Erro<> (
                    true,
                    "Ocorreu um erro e não foi possível cadastrar o usuário."
            );
        }              
        
        this.dadosDaSessao.adaptar(resposta.body());
        this.definirTempoDeLogin();
        
        acaoSendoRealizada = false;
        
        return new Erro<>(
                false,
                ""
        );
    }
    
    /**
     * \brief Tenta atualizar a sessão do usuário.
     * 
     * @param dados Dados a serem enviados para atualizar a sessão.
     * @throws IOException 
     * @return Erro caso tenha ocorrido.
     */
    public Erro<String> atualizarSessao(
            FirebaseTokenEnvio dados
    ) throws IOException {
        if (acaoSendoRealizada) {
            return new Erro<>(
                    true,
                    "Não é possível atualizar a sessão nesse momento."
            );
        }
        
        acaoSendoRealizada = true;
        
        var chamada = Firebase.sessaoValida(
                Configuracoes.chaveDaApi,
                dados
        );
        
        var resposta = chamada.execute();
        
        if (!resposta.isSuccessful()) {
            if (resposta.errorBody() != null) {
                return new Erro(
                        true,
                        resposta.errorBody().string()
                );
            }
            
            return new Erro(
                    true,
                    "Ocorreu um erro e não foi possível atualizar a sesão."
            );
        }
        
        
        this.dadosDaSessao.adaptar(resposta.body());
        this.definirTempoDeLogin();
        
        acaoSendoRealizada = false;
        
        return new Erro<>(false, "");
    }
    
    /**
     * \brief Realiza uma simples verificação se o dados do login e senha estão
     *        preenchidos.
     * 
     * @param dados Corpo da requisição.
     * @return Possível erro.
     */
    private Erro<String> validarDados(FirebaseCadastroELoginCorpo dados) {
        if (dados.email == null || dados.email.trim().isEmpty()) {
            return new Erro<>(
                    true,
                    "O email passado está vázio."
            );
        } else if (dados.senha == null || dados.senha.trim().isEmpty()) {
            return new Erro<>(
                    true,
                    "A senha passada está vázia."
            );
        }
        
        return new Erro<>(false, "");
    }
    
    private void definirTempoDeLogin() {
        this.dataEHoraParaExpirar = LocalDateTime.now();
        this.dataEHoraParaExpirar.plusSeconds(
                this.dadosDaSessao.pegarTempoDeExpiracao() - 20
        );
    }
    
    public static Sessao pegarInstancia() {
        if (__singleton == null) {
            __singleton = new Sessao();
        }
        
        return __singleton;
    }
}
