/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.data.DadosLivres;

/**
 *
 * @author jomar
 */
public class CidadeDoEstado {
    public final long codigo;
    public final String nome;

    public CidadeDoEstado(long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }            
    
    @Override
    public String toString() {
        return this.nome;        
    }
}
