package ProcuraMelhorEscola.adapters.session;


import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroResposta;
import ProcuraMelhorEscola.data.firebase.token.FirebaseTokenResposta;
import ProcuraMelhorEscola.session.DataDaSessao;

/**
 * \brief Adapta os dados recebidos de outros POJOs.
 * 
 * @author jomar
 */
public class AdaptadorDataDaSessao implements DataDaSessao {
    private String email;
    private String localId;
    private String idToken;
    private String tokenDeAtualizacao;
    private long tempoDeExpiracao;  

    @Override
    public String pegarEmail() {
        return email;
    }

    @Override
    public String pegarLocalId() {
        return localId;
    }

    @Override
    public String pegarTokenDeAtualizacao() {
        return tokenDeAtualizacao;
    }
    
    @Override
    public String pegarTokenId() {
        return this.idToken;
    }

    @Override
    public long pegarTempoDeExpiracao() {
        return tempoDeExpiracao;
    }
    
    /**
     * \brief Adapta um objeto FirebaseCadastroResposta
     * 
     * @param resposta Objeto para ser adaptado
     */
    public void adaptar(FirebaseCadastroResposta resposta) {
        this.email = resposta.email;
        this.tempoDeExpiracao = resposta.expiraEm;
        this.localId = resposta.idLocal;
        this.tokenDeAtualizacao = resposta.tokenDeRefresh;
        this.idToken = resposta.idToken;
    }
    
    /**
     * \brief Adapta um objeto FirebaseTokenResposta
     * 
     * @param resposta objeto a ser adaptado.
     */
    public void adaptar(FirebaseTokenResposta resposta) {
        this.tokenDeAtualizacao = resposta.tokenDeAtualizacao;
        this.localId = resposta.usuarioId;
        this.tempoDeExpiracao = resposta.expiraEm;
        this.idToken = resposta.tokenId;
    }
    
    
    public void limparDados() {
        this.email = "";
        this.tempoDeExpiracao = 0;
        this.localId = "";
        this.tokenDeAtualizacao = "";
    }
    
}
