/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * \brief Contém um pool de threads para ser utilizado durante a aplicação.
 * 
 * @author jomar
 */
public class ControlaThreads {
    public static ExecutorService executor = Executors.newSingleThreadExecutor();
}
