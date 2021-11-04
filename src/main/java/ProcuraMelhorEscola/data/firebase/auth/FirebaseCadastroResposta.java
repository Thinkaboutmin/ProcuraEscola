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
public class FirebaseCadastroResposta {
    final public String idToken;
    
    final public String email;
    
    @SerializedName(value = "refreshToken")
    final public String tokenDeRefresh;
    
    @SerializedName(value = "expiresIn")
    final public long expiraEm;
    
    @SerializedName(value = "localId")
    final public String idLocal;

    public FirebaseCadastroResposta(String idToken, String email, String tokenDeRefresh, long expiraEm, String idLocal) {
        this.idToken = idToken;
        this.email = email;
        this.tokenDeRefresh = tokenDeRefresh;
        this.expiraEm = expiraEm;
        this.idLocal = idLocal;
    }
    
    
}
