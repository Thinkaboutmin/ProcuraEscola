/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.ui.login;

import ProcuraMelhorEscola.data.firebase.auth.FirebaseCadastroELoginCorpo;
import ProcuraMelhorEscola.session.Sessao;
import ProcuraMelhorEscola.ui.ControlaTela;
import ProcuraMelhorEscola.ui.TelasEnum;
import ProcuraMelhorEscola.utils.ControlaThreads;
import ProcuraMelhorEscola.utils.SnippetsSwing;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author jomar
 */
public class LoginUI extends javax.swing.JPanel {
    private final ControlaTela controlaTela;
    private final Sessao sessao;
    
    private volatile boolean realizandoAcao = false;
    
    /**
     * Creates new form LoginUI
     * @param controlaTela
     * @param sessao
     */
    public LoginUI(ControlaTela controlaTela, Sessao sessao, String extraData) {
        initComponents();
        
        this.controlaTela = controlaTela;
        this.sessao = sessao;        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cadastrarBtn = new java.awt.Button();
        loginBtn = new java.awt.Button();
        campoSenha = new javax.swing.JPasswordField();

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setName("Header"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("Login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(157, 157, 157))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Email");
        jLabel2.setName("UserLabel"); // NOI18N

        campoEmail.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        campoEmail.setToolTipText("Digite o seu usu??rio para login aqui");
        campoEmail.setName("UserField"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Senha");

        cadastrarBtn.setActionCommand("cadastrarBtn");
        cadastrarBtn.setBackground(new java.awt.Color(242, 242, 242));
        cadastrarBtn.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        cadastrarBtn.setLabel("Cadastrar");
        cadastrarBtn.setName("LoginBtn"); // NOI18N
        cadastrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarBtnMouseClicked(evt);
            }
        });

        loginBtn.setActionCommand("loginBtn");
        loginBtn.setBackground(new java.awt.Color(242, 242, 242));
        loginBtn.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        loginBtn.setLabel("Login");
        loginBtn.setName("LoginBtn"); // NOI18N
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnMouseClicked(evt);
            }
        });

        campoSenha.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        campoSenha.setToolTipText("Digite a senha para login aqui");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(cadastrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoEmail)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(campoSenha)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cadastrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarBtnMouseClicked
        this.controlaTela.mudarTelaPara(TelasEnum.CADASTRO, "");
    }//GEN-LAST:event_cadastrarBtnMouseClicked

    private void loginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseClicked
        if (this.realizandoAcao) {
            return;
        }
        
        this.campoEmail.setText(
                this.campoEmail.getText().trim()
        );
        this.campoSenha.setText(
                this.campoSenha.getText().trim()
        );
        
        if (
            this.campoEmail.getText().isEmpty() ||
            this.campoSenha.getText().isEmpty()
           ) {
            return;
        }

        ControlaThreads.executor.submit(
            () -> {
                this.realizandoAcao = true;
                SnippetsSwing.alterarEstadoComponente(
                    this.loginBtn, false
                );
                                
                try {
                    var dados = new FirebaseCadastroELoginCorpo(
                            this.campoEmail.getText(),
                            this.campoSenha.getText(),
                            true
                    );
                    
                    var erro = this.sessao.logar(dados);
                    if (!erro.teveErro) {
                        // Contexto desta execu????o est?? fora do contexto do EDT.
                        SwingUtilities.invokeLater(() -> {
                            this.controlaTela.mudarTelaPara(
                                TelasEnum.MENU, ""
                            );
                        });                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoginUI.class.getName()).log(
                        Level.WARNING, null, ex
                    );                    
                }
                
                SnippetsSwing.alterarEstadoComponente(
                    this.loginBtn, true
                );
                this.realizandoAcao = false;
            }
        );                        
    }//GEN-LAST:event_loginBtnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button cadastrarBtn;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Button loginBtn;
    // End of variables declaration//GEN-END:variables
}
