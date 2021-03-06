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
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author jomar
 */
public class CadastroUI extends javax.swing.JPanel {
    final private ControlaTela controlaTela;
    final private Sessao sessao;
    
    private volatile boolean realizandoAcao = false;
    
    
    /**
     * Creates new form LoginUI
     * @param controlaTela
     * @param sessao
     */
    public CadastroUI(ControlaTela controlaTela, Sessao sessao, 
                      String extraData) {
        this.controlaTela = controlaTela;
        this.sessao = sessao;
        initComponents();
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
        jLabel4 = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        campoConfirmarSenha = new javax.swing.JPasswordField();
        voltarBtn = new java.awt.Button();
        cadastrarBtn = new java.awt.Button();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setName("Header"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("Cadastro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setLabelFor(campoEmail);
        jLabel2.setText("Email");
        jLabel2.setName("UserLabel"); // NOI18N

        campoEmail.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        campoEmail.setToolTipText("Digite a o seu email aqui para cadastro.");
        campoEmail.setName("UserField"); // NOI18N
        campoEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Senha");

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Confirmar Senha");

        campoSenha.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N

        campoConfirmarSenha.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(campoConfirmarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoSenha)
                        .addComponent(campoEmail, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        campoEmail.getAccessibleContext().setAccessibleName("");

        voltarBtn.setActionCommand("voltarBtn");
        voltarBtn.setBackground(new java.awt.Color(242, 242, 242));
        voltarBtn.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        voltarBtn.setLabel("Voltar");
        voltarBtn.setName("VoltarBtn"); // NOI18N
        voltarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voltarBtnMouseClicked(evt);
            }
        });

        cadastrarBtn.setActionCommand("cadastrarBtn");
        cadastrarBtn.setBackground(new java.awt.Color(242, 242, 242));
        cadastrarBtn.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        cadastrarBtn.setLabel("Cadastrar");
        cadastrarBtn.setName("CadastrarBtn"); // NOI18N
        cadastrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(voltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(cadastrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cadastrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void campoEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailActionPerformed
        
    }//GEN-LAST:event_campoEmailActionPerformed

    private void cadastrarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarBtnMouseClicked
        if (realizandoAcao) {
            return;
        }                                 
        
        // Remove espa??os em branco dos campos.
        this.campoSenha.setText(this.campoSenha.getText().trim());
        this.campoEmail.setText(this.campoEmail.getText().trim());
        this.campoConfirmarSenha.setText(
                this.campoConfirmarSenha.getText().trim()
        );
        
        if (campoEmail.getText().isEmpty() ||
            campoSenha.getText().isEmpty() ||
            campoConfirmarSenha.getText().isEmpty() ||
            !campoSenha.getText().equals(campoConfirmarSenha.getText())) {
            return;
        }
                
        ControlaThreads.executor.submit(() -> {
            realizandoAcao = true;
            SnippetsSwing.alterarEstadoComponente(this.cadastrarBtn, false);
            
            try {
                // Dados a serem cadastrados no Firebase
                var dados = new FirebaseCadastroELoginCorpo(
                    campoEmail.getText(),
                    campoSenha.getText(),
                    null
                );
                
                var erro = sessao.registrar(dados);
                if (!erro.teveErro) {
                    SwingUtilities.invokeLater(() -> {
                        this.controlaTela.mudarTelaPara(TelasEnum.MENU, "");
                    });                    
                }                
            } catch(IOException ex) {
                Logger.getLogger(CadastroUI.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
            
            SnippetsSwing.alterarEstadoComponente(this.cadastrarBtn, true);
            realizandoAcao = false;
        });
    }//GEN-LAST:event_cadastrarBtnMouseClicked

    private void voltarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voltarBtnMouseClicked
        controlaTela.mudarTelaPara(TelasEnum.LOGIN, "");
    }//GEN-LAST:event_voltarBtnMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button cadastrarBtn;
    private javax.swing.JPasswordField campoConfirmarSenha;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Button voltarBtn;
    // End of variables declaration//GEN-END:variables
}
