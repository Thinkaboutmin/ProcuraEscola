

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
public class FirebaseCadastroELoginCorpo {
    final public String email;
    
    // Transforma esse campo em password em JSON
    @SerializedName(value = "password")
    final public String senha;
    
    // Transforma esse campo em returnSecureToken em JSON
    @SerializedName(value = "returnSecureToken")
    final public boolean retornaTokenSeguro;
    
    /**
     * \brief Constroi este modelo, com valores não modificaveis.
     * 
     * 
     * 
     * @param email Email do usuário para login.
     * @param senha Senha do usuário para login
     * @param retornaTokenSeguro Se retorna o token de refresh (Sempre true por
     *                           padrão)
     */
    public FirebaseCadastroELoginCorpo(
            String email,
            String senha,
            Boolean retornaTokenSeguro
    ) {
        this.email = email;
        this.senha = senha;
        if (retornaTokenSeguro == null) {
            this.retornaTokenSeguro = true;
        } else {
            this.retornaTokenSeguro = retornaTokenSeguro;
        }        
    }
}
