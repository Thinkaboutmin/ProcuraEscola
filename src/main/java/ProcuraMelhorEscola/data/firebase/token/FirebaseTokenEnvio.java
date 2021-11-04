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
public class FirebaseTokenEnvio {
    
    @SerializedName(value = "grant_type")
    final public String tipoDePermissao = "refresh_token";
    
    @SerializedName(value = "refresh_token")
    final public String tokenDeAtualizacao;
    
    public FirebaseTokenEnvio(
            String tokenDeAtualizacao
    ) {
        this.tokenDeAtualizacao = tokenDeAtualizacao;
    }
}
