/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.data.firebase.auth;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jomar
 */
public class FirebaseLoginResposta extends FirebaseCadastroResposta {
    @SerializedName(value = "registered")
    final public boolean registrado;

    public FirebaseLoginResposta(
            boolean registrado,
            String idToken,
            String email,
            String tokenDeRefresh,
            long expiraEm,
            String idLocal) {
        super(idToken, email, tokenDeRefresh, expiraEm, idLocal);
        this.registrado = registrado;
    }    
}
