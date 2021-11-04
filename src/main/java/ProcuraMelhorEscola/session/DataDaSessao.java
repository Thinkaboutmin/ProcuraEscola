/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.session;

import java.time.LocalDateTime;

/**
 * \brief Dados de uma sessão.
 * 
 * 
 * @author jomar
 */
public interface DataDaSessao {
    /**
     * \brief Pega o email da sessão.
     * @return o email
     */
    public String pegarEmail();
    
    /**
     * \brief Pega a identificação interna do usuário no Firebase.
     * @return o token
     */
    public String pegarTokenId();
    
    /**
     * \brief Pega o token de atualização. Necessário para controlar a sessão.
     * 
     * @return o token de atualização.
     */
    public String pegarTokenDeAtualizacao();
    
    
    /**
     * \brief Pega o token de atualização
     * 
     * @return o tempo para expirar a sessão em segundos.
     */
    public long pegarTempoDeExpiracao();
}
