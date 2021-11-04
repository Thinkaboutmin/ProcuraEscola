/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.utils;

/**
 * \brief Simples classe para conter erros.
 * 
 * @param <T> Classe que vai conter os dados do erro.
 * @author jomar
 */
public class Erro<T> {
    public final boolean teveErro;
    
    public final T erro;

    public Erro(boolean teveErro, T erro) {
        this.teveErro = teveErro;
        this.erro = erro;
    }    
}
