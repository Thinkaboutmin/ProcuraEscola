/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.ui.procuraEscola;

import ProcuraMelhorEscola.data.DadosLivres.CidadeDoEstado;
import ProcuraMelhorEscola.data.DadosLivres.EscolaDaLista;
import ProcuraMelhorEscola.data.DadosLivres.InicioListaEscola;
import ProcuraMelhorEscola.ui.ControlaTela;
import ProcuraMelhorEscola.session.Sessao;
import ProcuraMelhorEscola.ui.TelasEnum;
import ProcuraMelhorEscola.utils.ControlaThreads;
import ProcuraMelhorEscola.utils.SnippetsSwing;
import ProcuraMelhorEscolar.providers.EducacaoDadosAberto;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import retrofit2.Response;

/**
 *
 * @author jomar
 */
public class ListaDeEscolasUI extends javax.swing.JPanel implements ItemListener {
    private final Sessao sessao;
    private final ControlaTela controlaTela;
    
    private final CidadeDoEstado semEstadoSelecionado = new CidadeDoEstado(
            -1,
            "Selecione o Estado"
    );
    
    /**
     * Creates new form ListaDeEscolasUI
     * @param controlaTela
     * @param sessao
     */
    public ListaDeEscolasUI(ControlaTela  controlaTela, Sessao sessao,
                            String extraData) {
        this.sessao = sessao;
        this.controlaTela = controlaTela;                       
        
        this.initComponents();
        
        // Cria uma coluna e depois remove ela para manter os dados.
        TableColumn coluna = new TableColumn();
        coluna.setIdentifier("Id");
        
        
        ((DefaultTableModel) this.escolaTabela.getModel()).addColumn(coluna);
        
        
        var cModel = this.escolaTabela.getColumnModel();
        cModel.removeColumn(
                cModel.getColumn(4)
        );
        
        
        this.adicionarEscutaNosEstados();       
        
        
        if (!this.sessao.estaLogado()) {
            this.controlaTela.mudarTelaPara(TelasEnum.LOGIN, "");
        }
                
        this.cidadeComboBox.addItem(semEstadoSelecionado);                        
    }
    
    
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        this.cidadeComboBox.removeAllItems();
        
        if (ie.getItem().toString().equals("Selecione o Estado")) {
            this.cidadeComboBox.removeAllItems();
            this.cidadeComboBox.addItem(this.semEstadoSelecionado);
            
            return;
        } 
        
        this.cidadeComboBox.addItem(
            new CidadeDoEstado(
                -1,
                "Buscando cidades"
            )
        );                        
        ControlaThreads.executor.submit(() -> {
            SnippetsSwing.alterarEstadoComponentes(       
                Arrays.asList(
                    this.cidadeComboBox,
                    this.estadosComboBox,
                    this.procurarBtn
                ), 
                false
            );
            
            var chamada = EducacaoDadosAberto.pegarCidadesDeUmEstado(
                    ie.getItem().toString()
            );
            
            
            Response<List<CidadeDoEstado>> resp = null;
            try {
                resp = chamada.execute();
            } catch (IOException ex) {
                Logger.getLogger(ListaDeEscolasUI.class.getName()).log(Level.SEVERE, null, ex);                
            }
                        
            SwingUtilities.invokeLater(() -> {
                this.cidadeComboBox.removeAllItems();
                this.cidadeComboBox.addItem(this.semEstadoSelecionado);
            });
            if (resp != null && resp.isSuccessful()) {
                final var body = resp.body();
                if (body != null) {
                    SwingUtilities.invokeLater(
                        () -> {                        
                            body.forEach((CidadeDoEstado cidadeEstado) -> {
                                this.cidadeComboBox.addItem(cidadeEstado);
                            });                        

                        }
                    );
                }                
            }
            
            SnippetsSwing.alterarEstadoComponentes(       
                Arrays.asList(
                    this.cidadeComboBox,
                    this.estadosComboBox,
                    this.procurarBtn
                ), 
                true
            );
        });
                
    }
    
    private void adicionarEscutaNosEstados() {
        this.estadosComboBox.addItemListener(this);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        nomeEscolaField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        estadosComboBox = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        cidadeComboBox = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        voltarBtn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        procurarBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel20 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        escolaTabela = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        detalhesBtn = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(200, 200, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(210, 410));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel4.setBackground(jPanel1.getBackground());
        jPanel4.setPreferredSize(new java.awt.Dimension(210, 23));
        jPanel4.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Filtros");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel4);

        jPanel5.setBackground(jPanel1.getBackground());
        jPanel5.setPreferredSize(new java.awt.Dimension(210, 55));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setBackground(jPanel1.getBackground());
        jPanel7.setPreferredSize(new java.awt.Dimension(210, 55));
        jPanel7.setRequestFocusEnabled(false);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nome da Escola");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(100, 100, 100))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(10, 10, 10))
        );

        jPanel5.add(jPanel7);

        jPanel3.setBackground(jPanel1.getBackground());
        jPanel3.setPreferredSize(new java.awt.Dimension(210, 30));

        nomeEscolaField.setPreferredSize(new java.awt.Dimension(188, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(nomeEscolaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(nomeEscolaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel5.add(jPanel3);

        jPanel1.add(jPanel5);

        jPanel6.setBackground(jPanel1.getBackground());
        jPanel6.setPreferredSize(new java.awt.Dimension(210, 75));
        jPanel6.setRequestFocusEnabled(false);
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setBackground(jPanel1.getBackground());

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Estado");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addGap(148, 148, 148))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(10, 10, 10))
        );

        jPanel6.add(jPanel8);

        jPanel9.setBackground(jPanel1.getBackground());
        jPanel9.setMinimumSize(new java.awt.Dimension(213, 30));

        estadosComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Estado", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        estadosComboBox.setPreferredSize(new java.awt.Dimension(188, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(estadosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(estadosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel6.add(jPanel9);

        jPanel1.add(jPanel6);

        jPanel10.setBackground(jPanel1.getBackground());
        jPanel10.setPreferredSize(new java.awt.Dimension(210, 65));
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel11.setBackground(jPanel10.getBackground());
        jPanel11.setPreferredSize(new java.awt.Dimension(187, 34));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Cidade");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addGap(148, 148, 148))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addGap(10, 10, 10))
        );

        jPanel10.add(jPanel11);

        jPanel12.setBackground(jPanel10.getBackground());

        cidadeComboBox.setPreferredSize(new java.awt.Dimension(188, 30));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cidadeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cidadeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel10.add(jPanel12);

        jPanel1.add(jPanel10);

        jPanel17.setBackground(jPanel1.getBackground());
        jPanel17.setPreferredSize(new java.awt.Dimension(210, 10));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel17);

        jPanel14.setBackground(jPanel1.getBackground());
        jPanel14.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel14.setName(""); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(210, 20));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));

        jPanel15.setBackground(jPanel1.getBackground());
        jPanel15.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel15.setPreferredSize(new java.awt.Dimension(85, 50));

        voltarBtn.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        voltarBtn.setText("Voltar");
        voltarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voltarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(voltarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(voltarBtn)))
        );

        jPanel14.add(jPanel15);

        jPanel16.setBackground(jPanel1.getBackground());
        jPanel16.setPreferredSize(new java.awt.Dimension(85, 50));

        procurarBtn.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        procurarBtn.setText("Procurar");
        procurarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                procurarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(procurarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(procurarBtn)))
        );

        jPanel14.add(jPanel16);

        jPanel1.add(jPanel14);

        add(jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel13.setBackground(jPanel1.getBackground());
        jPanel13.setPreferredSize(new java.awt.Dimension(466, 50));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 0, 0));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setAlignmentX(0.0F);
        jSeparator1.setAlignmentY(0.0F);
        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 100));
        jPanel13.add(jSeparator1);

        jPanel20.setBackground(jPanel13.getBackground());
        jPanel20.setMinimumSize(new java.awt.Dimension(456, 50));
        jPanel20.setPreferredSize(new java.awt.Dimension(456, 100));
        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 55));
        jLabel5.setText("Escolas");
        jPanel20.add(jLabel5);

        jPanel13.add(jPanel20);

        jPanel2.add(jPanel13);

        escolaTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Escola", "Estado", "Cidade", "Ativa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        escolaTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                escolaTabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(escolaTabela);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel18);

        jPanel19.setBackground(jPanel1.getBackground());
        jPanel19.setPreferredSize(new java.awt.Dimension(0, 50));
        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.LINE_AXIS));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel19.add(jSeparator2);

        detalhesBtn.setText("Detalhes");
        detalhesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detalhesBtnMouseClicked(evt);
            }
        });
        jPanel19.add(detalhesBtn);

        jPanel2.add(jPanel19);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void voltarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voltarBtnMouseClicked
        this.controlaTela.mudarTelaPara(TelasEnum.MENU, "");
    }//GEN-LAST:event_voltarBtnMouseClicked

    private void procurarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_procurarBtnMouseClicked
        this.nomeEscolaField.setText(this.nomeEscolaField.getText().trim());
        
        String estadoSelecionado;
        estadoSelecionado = this.estadosComboBox.getSelectedItem().toString();
        
        CidadeDoEstado cidadeSelecionada;
        cidadeSelecionada = (CidadeDoEstado)
                                          this.cidadeComboBox.getSelectedItem();
        
        
        // Evita procurar a escola caso não tenha dados suficientes para
        // filtrar.
        if (
            this.nomeEscolaField.getText().length() <= 3 &&
            estadoSelecionado.length() != 2 &&
            cidadeSelecionada.codigo == -1
        ) {
            return;
        }        
        
        // Nulifica valores que não vão ser utilizados na busca.
        if (estadoSelecionado.length() > 2) {
           estadoSelecionado = null;
        }
        
        final Long codigoDaCidade;
        if (cidadeSelecionada.codigo != -1) {
            codigoDaCidade = cidadeSelecionada.codigo;
        } else {
            codigoDaCidade = null;
        }
        
        final String estadoSelecionadoFinal = estadoSelecionado;
        ControlaThreads.executor.submit(() -> {
            SnippetsSwing.alterarEstadoComponente(
                this.procurarBtn, 
                false
            );
            
            var chamada = EducacaoDadosAberto.listarEscolasBuscaAvancada(
                    this.nomeEscolaField.getText(),
                    estadoSelecionadoFinal,
                    codigoDaCidade
            );
            
            Response<InicioListaEscola> resp = null;
            try {
                resp = chamada.execute();
            } catch (IOException ex) {
                Logger.getLogger(ListaDeEscolasUI.class.getName()).log(
                        Level.SEVERE, 
                        null, 
                        ex
                );
            }
            
            if (resp != null && resp.isSuccessful()) {
                final InicioListaEscola dados = resp.body();
                if (dados != null) {
                    final DefaultTableModel model;
                    model = (DefaultTableModel)this.escolaTabela.getModel();
                    
                    SwingUtilities.invokeLater(() -> {
                        model.setRowCount(0);
                        
                        dados.escolas.forEach((EscolaDaLista escola) -> {                                                                                           
                            model.addRow(Arrays.asList(
                                escola.nome,
                                escola.estado,
                                escola.cidade,
                                escola.situacaoFuncionamentoTxt,
                                (Integer)escola.cod
                            ).toArray());
                        });                    
                    });
                }                                
            }
            
            SnippetsSwing.alterarEstadoComponente(
                this.procurarBtn, 
                true
            );
        });
    }//GEN-LAST:event_procurarBtnMouseClicked

    private void escolaTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_escolaTabelaMouseClicked
        // TODO add your handling code here:
        
        final var linhaSelecionada = this.escolaTabela.getSelectedRow();
        
        if (linhaSelecionada >= 0) {
            this.procurarBtn.setEnabled(true);
        } else {
            this.procurarBtn.setEnabled(false);
        }                
    }//GEN-LAST:event_escolaTabelaMouseClicked

    private void detalhesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detalhesBtnMouseClicked
        // TODO add your handling code here:
        var model = this.escolaTabela.getModel();
        
        final var linhaSelecionada = this.escolaTabela.getSelectedRow();
        
        Integer id = (Integer)model.getValueAt(linhaSelecionada, 4);
        
        if (id != null) {                                    
            JFrame jframe = new JFrame("Detalhes da escola");
            
            jframe.getContentPane().add(
                new DetalhesEscolaUI(
                    this.controlaTela,
                    this.sessao,
                    id.toString()
                )
            );
            
            jframe.pack();
            
            jframe.setVisible(true);
            
        }        
    }//GEN-LAST:event_detalhesBtnMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<CidadeDoEstado> cidadeComboBox;
    private javax.swing.JButton detalhesBtn;
    private javax.swing.JTable escolaTabela;
    private javax.swing.JComboBox<String> estadosComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nomeEscolaField;
    private javax.swing.JButton procurarBtn;
    private javax.swing.JButton voltarBtn;
    // End of variables declaration//GEN-END:variables

}
