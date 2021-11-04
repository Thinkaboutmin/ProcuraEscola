/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.ui;

/**
 *
 * @author jomar
 */
public interface ControlaTela {
    /**
     * \brief Muda a tela do sistema.
     * 
     * @param telaAMudar Tela para trocar
     * @param dados dados para ser passado entre as telas.
     */
    public void mudarTelaPara(TelasEnum telaAMudar, String dados);
}