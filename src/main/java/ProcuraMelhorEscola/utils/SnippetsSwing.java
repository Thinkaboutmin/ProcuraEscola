/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.utils;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * \brief Conjunto de operações que são realizadas 
 *        no EDT (Event Dispatch Thread).
 * 
 * Aqui vamos ter alguns métodos estáticos que podem ser utilizados em
 * threads não EDTs.
 * 
 * Todas os métodos vão utilizar o invokeAndWait, ou seja, a função vai parar
 * o thread do contexto até o término da ação no EDT.
 * 
 * @author jomar
 */
public abstract class SnippetsSwing {
    
    /**
     * \brief Altera os estados do botões para habilitado ou desabilitado.
     * 
     * @param cmps Botões a terem os estados alterados.
     * @param estado Estado para alterar os botões.
     */
    public static void alterarEstadoComponentes(List<Component> cmps, boolean estado) {
        if (SwingUtilities.isEventDispatchThread()) {
            cmps.forEach(cmp -> {
                cmp.setEnabled(estado);
            });
            
            return;
        }
        
        try {
            SwingUtilities.invokeAndWait(() -> {
                    SnippetsSwing.alterarEstadoComponentes(cmps, estado);
                }
            );
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(SnippetsSwing.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
    /**
     * \brief Altera o estado do botão para habilitado ou desabilitado.
     * 
     * Está método é um utilitario que direciona para o método
     * \ref alterarEstadoBotoes(List<Component> btns, boolean estado)
     * 
     * @param cmp Botão a ter o estado alterado.
     * @param estado Estado para alterar o botão.
     */
    public static void alterarEstadoComponente(Component cmp, boolean estado) {
        SnippetsSwing.alterarEstadoComponentes(Arrays.asList(cmp) , estado);
    }
}
