/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.ui;

import ProcuraMelhorEscola.session.Sessao;
import ProcuraMelhorEscola.ui.login.CadastroUI;
import ProcuraMelhorEscola.ui.login.LoginUI;
import ProcuraMelhorEscola.ui.procuraEscola.MenuUI;
import ProcuraMelhorEscola.ui.splash.SplashUI;
import javax.swing.JFrame;

/**
 *
 * @author jomar
 */
public class TelaPrincipal extends JFrame implements ControlaTela {
    private TelasEnum telaAtual;
    
    public TelaPrincipal(String nome) {
        super(nome);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.telaAtual = TelasEnum.SPLASH;
        this.getContentPane().add(new SplashUI(
                this,
                Sessao.pegarInstancia()
        ));
        
        this.pack();
    }
    
    public void executar() {
        this.setVisible(true);
    }

    @Override
    public void mudarTelaPara(TelasEnum telaAMudar, String dados) {
        this.getContentPane().removeAll();
        
        
        this.telaAtual = telaAMudar;
        switch(telaAMudar) {
            case SPLASH:
                this.getContentPane().add(new SplashUI(
                        this,
                        Sessao.pegarInstancia()
                ));
                break;
            case LOGIN:
                this.getContentPane().add(new LoginUI(
                        this,
                        Sessao.pegarInstancia()
                ));
                break;
            case CADASTRO:
                this.getContentPane().add(new CadastroUI(
                        this,
                        Sessao.pegarInstancia()
                ));
                break;
            case MENU:
                this.getContentPane().add(new MenuUI(
                        this,
                        Sessao.pegarInstancia()
                ));
            default:
                throw new AssertionError(telaAMudar.name());
            
        }
        
                
        this.invalidate();
        this.repaint();
        this.pack();
    }
}
