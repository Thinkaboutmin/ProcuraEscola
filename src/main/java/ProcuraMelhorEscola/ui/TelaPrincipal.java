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
import ProcuraMelhorEscola.ui.procuraEscola.ListaDeEscolasUI;
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
                Sessao.pegarInstancia(),
                ""
        ));       
        
        this.pack();
    }
    
    public void executar() {
        this.setVisible(true);
    }

    @Override
    public void mudarTelaPara(TelasEnum telaAMudar, String dados) {
        if (telaAMudar == this.telaAtual) {
            return;
        }
        
        this.getContentPane().removeAll();        
        
        this.telaAtual = telaAMudar;
        switch(telaAMudar) {
            case SPLASH:
                this.getContentPane().add(new SplashUI(
                        this,
                        Sessao.pegarInstancia(),
                        dados
                ));
                break;
            case LOGIN:
                this.getContentPane().add(new LoginUI(
                        this,
                        Sessao.pegarInstancia(),
                        dados
                ));
                break;
            case CADASTRO:
                this.getContentPane().add(new CadastroUI(
                        this,
                        Sessao.pegarInstancia(),
                        dados
                ));
                break;
            case MENU:
                this.getContentPane().add(new MenuUI(
                        this,
                        Sessao.pegarInstancia(),
                        dados
                ));
                break;
            case LISTA_DE_ESCOLAS:
                this.getContentPane().add(new ListaDeEscolasUI(
                        this,
                        Sessao.pegarInstancia(),
                        dados
                    )
                );
                break;
              
            default:
                throw new AssertionError(telaAMudar.name());
            
        }
                     
        this.invalidate();
        this.validate();
        this.repaint();
        this.pack();
    }
}
