/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.data.firebase.token;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jomar
 */
public class FirebaseTokenResposta {
    @SerializedName(value = "expires_in")
    final public long expiraEm;
    
    @SerializedName(value = "token_type")
    final public String tipoToken;
    
    @SerializedName(value = "refresh_token")
    final public String tokenDeAtualizacao;
    
    @SerializedName(value = "token_id")
    final public String tokenId;
    
    @SerializedName(value = "user_id")
    final public String usuarioId;
    
    @SerializedName(value = "project_id")
    final public String projetoId;

    public FirebaseTokenResposta(
            long expiraEm,
            String tipoToken,
            String tokenDeAtualizacao,
            String tokenId,
            String usuarioId,
            String projetoId) {
        this.expiraEm = expiraEm;
        this.tipoToken = tipoToken;
        this.tokenDeAtualizacao = tokenDeAtualizacao;
        this.tokenId = tokenId;
        this.usuarioId = usuarioId;
        this.projetoId = projetoId;
    }    
}
