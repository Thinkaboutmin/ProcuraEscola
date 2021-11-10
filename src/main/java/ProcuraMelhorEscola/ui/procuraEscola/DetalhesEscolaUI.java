/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcuraMelhorEscola.ui.procuraEscola;

import ProcuraMelhorEscola.data.DadosLivres.DetalheEscola;
import ProcuraMelhorEscola.session.Sessao;
import ProcuraMelhorEscola.ui.ControlaTela;
import ProcuraMelhorEscola.utils.ControlaThreads;
import ProcuraMelhorEscolar.providers.EducacaoDadosAberto;
import ProcuraMelhorEscolar.providers.Firebase;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import retrofit2.Response;

/**
 *
 * @author jomar
 */
public class DetalhesEscolaUI extends javax.swing.JPanel {
    private final Sessao sessao;
    private final ControlaTela controlaTela;
    private String escolaId = null;
    

    /**
     * Creates new form DetalhesEscola
     * @param controlaTela
     * @param sessao
     * @param extraData
     */
    public DetalhesEscolaUI(ControlaTela controlaTela, Sessao sessao, 
                            String extraData) {
        this.sessao = sessao;
        this.controlaTela = controlaTela;
        
        this.escolaId = extraData;
        this.buscarDetalhes();
        
        initComponents();
        this.verificarSeEstaFavoritado();
    }

    private void buscarDetalhes() {
        ControlaThreads.executor.submit(() -> {
            var chamada = EducacaoDadosAberto.detalhesEscola(this.escolaId);
            
            Response<DetalheEscola> resposta = null;
            try {
                resposta = chamada.execute();
            } catch (IOException exc) {
                Logger.getLogger(DetalhesEscolaUI.class.getName()).log(
                        Level.SEVERE,
                        exc,
                        null
                );
            }
            
            if (resposta != null && resposta.isSuccessful()) {
                final var body = resposta.body();
                
                SwingUtilities.invokeLater( () -> {                    
                    this.preencherDadosEscola(body);
                    this.preencherDadosInfraBasica(body);
                    this.preencherDadosDependencias(body);
                });
            }
        });
        
    }
    
    
    private void preencherDadosEscola(DetalheEscola detalhes) { 
        this.setarLabel(this.tituloEscola, detalhes.nome);
        
        this.setarLabel(this.municipioEscola, detalhes.nomeMunicipio);
        this.setarLabel(this.estadoEscola, detalhes.siglaUf);
        this.setarLabel(this.regulamentadaEscola, detalhes.regulamentadaTxt);       
        this.setarLabel(this.enderecoEscola, detalhes.endereco);
        this.setarLabel(this.situacaoEscola, detalhes.situacaoFuncionamentoTxt);                        
    }
    
    private void preencherDadosInfraBasica(DetalheEscola detalhes) {
        this.abaRedePublicaCheck.setSelected(detalhes.aguaPublica);
        this.abaPocoArtesianoCheck.setSelected(detalhes.aguaPocoArtesiano);
        this.abaInexistenteCheck.setSelected(detalhes.aguaInexistente);
        this.abaFonteCheck.setSelected(detalhes.aguaRio);
        this.abaCisternaCheck.setSelected(detalhes.aguaCacimba);
        
        this.enerGeradorCheck.setSelected(detalhes.energiaGerador);
        this.enerInexistenteCheck.setSelected(detalhes.energiaInexistente);
        this.enerOutrosCheck.setSelected(detalhes.energiaOutros);
        this.enerRedePublicaCheck.setSelected(detalhes.energiaPublica);
        
        this.destLColPerCheck.setSelected(detalhes.lixoColetaPeriodica);
        this.destLEnterraCheck.setSelected(detalhes.lixoEnterra);
        this.destLJogaAreaCheck.setSelected(detalhes.lixoJogaOutraArea);
        this.destLOutrosCheck.setSelected(detalhes.lixoOutros);
        this.destLQueimaCheck.setSelected(detalhes.lixoQueima);
        this.destLReciclaCheck.setSelected(detalhes.lixoRecicla);
        
        this.esgtFossaCheck.setSelected(detalhes.esgotoFossa);
        this.esgtInexistenteCheck.setSelected(detalhes.esgotoInexistente);
        this.esgtRedePublicaCheck.setSelected(detalhes.esgotoPublico);                       
    }
    
    private void preencherDadosDependencias(DetalheEscola detalhes) {
        this.depDiretoria.setSelected(detalhes.salaDiretoria);
        this.depProf.setSelected(detalhes.salaProfessores);
        this.depLabInfo.setSelected(detalhes.laboratorioInformatica);
        this.depLabCien.setSelected(detalhes.laboratorioCiencias);
        this.depAtenEduEsp.setSelected(detalhes.atendimentoEspecial);
        this.depQuadraCoberta.setSelected(detalhes.quadraCoberta);
        this.depCozinha.setSelected(detalhes.cozinha);
        this.depBiblioteca.setSelected(detalhes.biblioteca);
        this.depSalaDeLeitura.setSelected(detalhes.salaLeitura);
        this.depParque.setSelected(detalhes.parqueInfantil);
        this.depQuadraDesc.setSelected(detalhes.quadraDescoberta);
        this.depBerc.setSelected(detalhes.bercario);
        this.depBanheiroFora.setSelected(detalhes.sanitarioForaPredio);
        this.depBanheiro.setSelected(detalhes.sanitarioDentroPredio);
        this.depBanheiroInfa.setSelected(detalhes.sanitarioEducInfant);
        this.depBanheiroMobiReduz.setSelected(detalhes.sanitarioPNE);
        this.depDepAlunosDef.setSelected(detalhes.dependenciasPNE);
        this.depSecretaria.setSelected(detalhes.secretaria);
        this.depBanChuveiro.setSelected(detalhes.banheiroChuveiro);
        this.depRefeitorio.setSelected(detalhes.refeitorio);
        this.depDespensa.setSelected(detalhes.despensa);
        this.depAlmoxarifado.setSelected(detalhes.almoxarifado);
        this.depAuditorio.setSelected(detalhes.auditorio);
        this.depPatioCoberto.setSelected(detalhes.patioCoberto);
        this.depPatioDescoberto.setSelected(detalhes.patioDescoberto);
        this.depAlojAluno.setSelected(detalhes.alojamentoAluno);
        this.depAlojProf.setSelected(detalhes.alojamentoProfessor);
        this.depAreaVerde.setSelected(detalhes.areaVerde);
        this.depLavanderia.setSelected(detalhes.lavanderia);
    }
    
    private void verificarSeEstaFavoritado() {
        if (this.sessao.estaLogado() && this.escolaId != null) {
            final var localId = this.sessao.pegarDados().pegarLocalId();
            final String caminho;
            caminho = "/users/" + localId + "/escolas/" + this.escolaId
                    + "/favoritada";
                                                            
            ControlaThreads.executor.submit(() -> {
                final var chamada = Firebase.pegarDados(
                    caminho,
                    this.sessao.pegarDados().pegarTokenId()
                );
                Response<JsonElement> resposta = null;
                
                try {
                    resposta = chamada.execute();
                } catch(IOException exc) {
                    Logger.getLogger(DetalhesEscolaUI.class.getName()).log(
                        Level.SEVERE,
                        exc,
                        null
                    );
                }
                
                
                if (resposta != null && resposta.isSuccessful()) {
                    final var resultado = resposta.body();
                    SwingUtilities.invokeLater(() -> {
                        if (resultado == null || !resultado.getAsBoolean()) {
                            this.favoritoCheck.setSelected(false);
                        } else {
                            this.favoritoCheck.setSelected(true);
                        }
                    });                    
                }
            });
        }
    }
    
    private void setarLabel(JLabel label, String textoExtra) {
        label.setText(
                label.getText() + textoExtra
        );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        tituloEscola = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        tipoEscola = new javax.swing.JLabel();
        enderecoEscola = new javax.swing.JLabel();
        municipioEscola = new javax.swing.JLabel();
        estadoEscola = new javax.swing.JLabel();
        regulamentadaEscola = new javax.swing.JLabel();
        situacaoEscola = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        abaRedePublicaCheck = new javax.swing.JCheckBox();
        abaPocoArtesianoCheck = new javax.swing.JCheckBox();
        abaCisternaCheck = new javax.swing.JCheckBox();
        abaFonteCheck = new javax.swing.JCheckBox();
        abaInexistenteCheck = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        enerRedePublicaCheck = new javax.swing.JCheckBox();
        enerGeradorCheck = new javax.swing.JCheckBox();
        enerOutrosCheck = new javax.swing.JCheckBox();
        enerInexistenteCheck = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        destLColPerCheck = new javax.swing.JCheckBox();
        destLQueimaCheck = new javax.swing.JCheckBox();
        destLJogaAreaCheck = new javax.swing.JCheckBox();
        destLReciclaCheck = new javax.swing.JCheckBox();
        destLEnterraCheck = new javax.swing.JCheckBox();
        destLOutrosCheck = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        esgtRedePublicaCheck = new javax.swing.JCheckBox();
        esgtFossaCheck = new javax.swing.JCheckBox();
        esgtInexistenteCheck = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        depDiretoria = new javax.swing.JCheckBox();
        depProf = new javax.swing.JCheckBox();
        depLabInfo = new javax.swing.JCheckBox();
        depLabCien = new javax.swing.JCheckBox();
        depAtenEduEsp = new javax.swing.JCheckBox();
        depQuadraCoberta = new javax.swing.JCheckBox();
        depCozinha = new javax.swing.JCheckBox();
        depBiblioteca = new javax.swing.JCheckBox();
        depSalaDeLeitura = new javax.swing.JCheckBox();
        depParque = new javax.swing.JCheckBox();
        depQuadraDesc = new javax.swing.JCheckBox();
        depBerc = new javax.swing.JCheckBox();
        depBanheiroFora = new javax.swing.JCheckBox();
        depBanheiro = new javax.swing.JCheckBox();
        depBanheiroInfa = new javax.swing.JCheckBox();
        depBanheiroMobiReduz = new javax.swing.JCheckBox();
        depDepAlunosDef = new javax.swing.JCheckBox();
        depSecretaria = new javax.swing.JCheckBox();
        depBanChuveiro = new javax.swing.JCheckBox();
        depRefeitorio = new javax.swing.JCheckBox();
        depDespensa = new javax.swing.JCheckBox();
        depAlmoxarifado = new javax.swing.JCheckBox();
        depAuditorio = new javax.swing.JCheckBox();
        depPatioCoberto = new javax.swing.JCheckBox();
        depPatioDescoberto = new javax.swing.JCheckBox();
        depAlojAluno = new javax.swing.JCheckBox();
        depAlojProf = new javax.swing.JCheckBox();
        depAreaVerde = new javax.swing.JCheckBox();
        depLavanderia = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        favoritoCheck = new javax.swing.JCheckBox();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setBackground(new java.awt.Color(200, 200, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(671, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        tituloEscola.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        tituloEscola.setForeground(new java.awt.Color(51, 51, 51));
        tituloEscola.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(tituloEscola, java.awt.BorderLayout.CENTER);

        add(jPanel1);

        jPanel4.setPreferredSize(new java.awt.Dimension(671, 350));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setPreferredSize(new java.awt.Dimension(800, 800));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel10.setFont(new java.awt.Font("FreeSans", 1, 12)); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(686, 200));
        jPanel10.setRequestFocusEnabled(false);

        tipoEscola.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        tipoEscola.setText("Tipo: ");

        enderecoEscola.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        enderecoEscola.setText("Endereço: ");

        municipioEscola.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        municipioEscola.setText("Município: ");

        estadoEscola.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        estadoEscola.setText("Estado: ");

        regulamentadaEscola.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        regulamentadaEscola.setText("Regulamentada: ");

        situacaoEscola.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        situacaoEscola.setText("Situação: ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoEscola)
                    .addComponent(enderecoEscola)
                    .addComponent(municipioEscola)
                    .addComponent(estadoEscola)
                    .addComponent(regulamentadaEscola)
                    .addComponent(situacaoEscola))
                .addContainerGap(693, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipoEscola)
                .addGap(18, 18, 18)
                .addComponent(enderecoEscola)
                .addGap(18, 18, 18)
                .addComponent(municipioEscola)
                .addGap(18, 18, 18)
                .addComponent(estadoEscola)
                .addGap(18, 18, 18)
                .addComponent(regulamentadaEscola)
                .addGap(18, 18, 18)
                .addComponent(situacaoEscola)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel10);

        jPanel9.setPreferredSize(new java.awt.Dimension(653, 600));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(200, 200, 200));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel8.setText("Infraestrutura Básica");

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel9.setText("Abastecimento de Água");

        abaRedePublicaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        abaRedePublicaCheck.setForeground(new java.awt.Color(255, 255, 255));
        abaRedePublicaCheck.setText("Rede Pública");
        abaRedePublicaCheck.setEnabled(false);

        abaPocoArtesianoCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        abaPocoArtesianoCheck.setForeground(new java.awt.Color(255, 255, 255));
        abaPocoArtesianoCheck.setText("Poço Artesiano");
        abaPocoArtesianoCheck.setEnabled(false);
        abaPocoArtesianoCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abaPocoArtesianoCheckActionPerformed(evt);
            }
        });

        abaCisternaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        abaCisternaCheck.setForeground(new java.awt.Color(255, 255, 255));
        abaCisternaCheck.setText("Cacimba / Cisterna / Poço");
        abaCisternaCheck.setEnabled(false);

        abaFonteCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        abaFonteCheck.setForeground(new java.awt.Color(255, 255, 255));
        abaFonteCheck.setText("Fonte / Rio / Iguarapé / Riacho / Córrego");
        abaFonteCheck.setEnabled(false);

        abaInexistenteCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        abaInexistenteCheck.setForeground(new java.awt.Color(255, 255, 255));
        abaInexistenteCheck.setText("Inexistente");
        abaInexistenteCheck.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel10.setText("Energia");

        enerRedePublicaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        enerRedePublicaCheck.setForeground(new java.awt.Color(255, 255, 255));
        enerRedePublicaCheck.setText("Rede Pública");
        enerRedePublicaCheck.setEnabled(false);

        enerGeradorCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        enerGeradorCheck.setForeground(new java.awt.Color(255, 255, 255));
        enerGeradorCheck.setText("Gerador");
        enerGeradorCheck.setEnabled(false);

        enerOutrosCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        enerOutrosCheck.setForeground(new java.awt.Color(255, 255, 255));
        enerOutrosCheck.setText("Outros");
        enerOutrosCheck.setEnabled(false);

        enerInexistenteCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        enerInexistenteCheck.setForeground(new java.awt.Color(255, 255, 255));
        enerInexistenteCheck.setText("Inexistente");
        enerInexistenteCheck.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel11.setText("Destinação do Lixo");

        destLColPerCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        destLColPerCheck.setForeground(new java.awt.Color(255, 255, 255));
        destLColPerCheck.setText("Coleta Peródica");
        destLColPerCheck.setEnabled(false);

        destLQueimaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        destLQueimaCheck.setForeground(new java.awt.Color(255, 255, 255));
        destLQueimaCheck.setText("Queima");
        destLQueimaCheck.setEnabled(false);

        destLJogaAreaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        destLJogaAreaCheck.setForeground(new java.awt.Color(255, 255, 255));
        destLJogaAreaCheck.setText("Joga em outra Área");
        destLJogaAreaCheck.setEnabled(false);

        destLReciclaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        destLReciclaCheck.setForeground(new java.awt.Color(255, 255, 255));
        destLReciclaCheck.setText("Recicla");
        destLReciclaCheck.setEnabled(false);

        destLEnterraCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        destLEnterraCheck.setForeground(new java.awt.Color(255, 255, 255));
        destLEnterraCheck.setText("Enterra");
        destLEnterraCheck.setEnabled(false);

        destLOutrosCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        destLOutrosCheck.setForeground(new java.awt.Color(255, 255, 255));
        destLOutrosCheck.setText("Outros");
        destLOutrosCheck.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        jLabel12.setText("Esgoto Sanitário");

        esgtRedePublicaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        esgtRedePublicaCheck.setForeground(new java.awt.Color(255, 255, 255));
        esgtRedePublicaCheck.setText("Rede Pública");
        esgtRedePublicaCheck.setEnabled(false);

        esgtFossaCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        esgtFossaCheck.setForeground(new java.awt.Color(255, 255, 255));
        esgtFossaCheck.setText("Fossa");
        esgtFossaCheck.setEnabled(false);

        esgtInexistenteCheck.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        esgtInexistenteCheck.setForeground(new java.awt.Color(255, 255, 255));
        esgtInexistenteCheck.setText("Inexistente");
        esgtInexistenteCheck.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(abaRedePublicaCheck)
                                    .addComponent(abaPocoArtesianoCheck)
                                    .addComponent(abaFonteCheck)
                                    .addComponent(abaCisternaCheck))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(enerRedePublicaCheck)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(enerGeradorCheck, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(enerOutrosCheck)
                                    .addComponent(enerInexistenteCheck)))
                            .addComponent(abaInexistenteCheck))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(destLEnterraCheck)
                            .addComponent(destLReciclaCheck)
                            .addComponent(destLOutrosCheck)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(destLColPerCheck)
                                    .addComponent(destLQueimaCheck)
                                    .addComponent(destLJogaAreaCheck))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(esgtInexistenteCheck)
                                    .addComponent(esgtFossaCheck)
                                    .addComponent(esgtRedePublicaCheck)
                                    .addComponent(jLabel12))))))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abaRedePublicaCheck)
                    .addComponent(enerRedePublicaCheck)
                    .addComponent(destLColPerCheck)
                    .addComponent(esgtRedePublicaCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abaPocoArtesianoCheck)
                    .addComponent(enerGeradorCheck)
                    .addComponent(destLQueimaCheck)
                    .addComponent(esgtFossaCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abaCisternaCheck)
                    .addComponent(enerOutrosCheck)
                    .addComponent(destLJogaAreaCheck)
                    .addComponent(esgtInexistenteCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abaFonteCheck)
                    .addComponent(enerInexistenteCheck)
                    .addComponent(destLReciclaCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abaInexistenteCheck)
                    .addComponent(destLEnterraCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destLOutrosCheck)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel9);

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator4.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel13.setText("Dependências Existentes");

        depDiretoria.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depDiretoria.setText("Sala de Diretoria");
        depDiretoria.setEnabled(false);

        depProf.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depProf.setText("Sala de Professores");
        depProf.setToolTipText("");
        depProf.setEnabled(false);

        depLabInfo.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depLabInfo.setText("Laboratório de Informática");
        depLabInfo.setEnabled(false);

        depLabCien.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depLabCien.setText("Laboratório de Ciências");
        depLabCien.setEnabled(false);

        depAtenEduEsp.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depAtenEduEsp.setText("Atendimento Educacional Especializado");
        depAtenEduEsp.setEnabled(false);

        depQuadraCoberta.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depQuadraCoberta.setText("Quadra Coberta");
        depQuadraCoberta.setEnabled(false);

        depCozinha.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depCozinha.setText("Cozinha");
        depCozinha.setEnabled(false);

        depBiblioteca.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBiblioteca.setText("Biblioteca");
        depBiblioteca.setEnabled(false);

        depSalaDeLeitura.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depSalaDeLeitura.setText("Sala de Leitura");
        depSalaDeLeitura.setEnabled(false);

        depParque.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depParque.setText("Parque Infantil");
        depParque.setEnabled(false);

        depQuadraDesc.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depQuadraDesc.setText("Quadra Descoberta");
        depQuadraDesc.setEnabled(false);

        depBerc.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBerc.setText("Berçário");
        depBerc.setEnabled(false);

        depBanheiroFora.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBanheiroFora.setText("Banheiro fora do prédio");
        depBanheiroFora.setEnabled(false);

        depBanheiro.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBanheiro.setText("Banheiro dentro do prédio");
        depBanheiro.setEnabled(false);

        depBanheiroInfa.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBanheiroInfa.setText("Banheiro adequado à educação infantil");
        depBanheiroInfa.setEnabled(false);

        depBanheiroMobiReduz.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBanheiroMobiReduz.setSelected(true);
        depBanheiroMobiReduz.setText("banheiro adequado a alunos com deficiência ou mobilidade reduzida");
        depBanheiroMobiReduz.setEnabled(false);

        depDepAlunosDef.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depDepAlunosDef.setText("Dependências e vias adequadas a alunos com deficiência ou \nmobilidade reduzida");
        depDepAlunosDef.setEnabled(false);

        depSecretaria.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depSecretaria.setText("Sala de Secretaria");
        depSecretaria.setEnabled(false);

        depBanChuveiro.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depBanChuveiro.setText("Banheiro com chuveiro");
        depBanChuveiro.setEnabled(false);

        depRefeitorio.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depRefeitorio.setText("Refeitório");
        depRefeitorio.setEnabled(false);

        depDespensa.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depDespensa.setText("Despensa");
        depDespensa.setEnabled(false);

        depAlmoxarifado.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depAlmoxarifado.setText("Almoxarifado");
        depAlmoxarifado.setEnabled(false);

        depAuditorio.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depAuditorio.setText("Auditório");
        depAuditorio.setEnabled(false);

        depPatioCoberto.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depPatioCoberto.setText("Pátio Coberto");
        depPatioCoberto.setEnabled(false);

        depPatioDescoberto.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depPatioDescoberto.setText("Pátio Descoberto");
        depPatioDescoberto.setEnabled(false);

        depAlojAluno.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depAlojAluno.setText("Alojamento de Aluno");
        depAlojAluno.setEnabled(false);

        depAlojProf.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depAlojProf.setText("Alojamento de Professor");
        depAlojProf.setEnabled(false);

        depAreaVerde.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depAreaVerde.setText("Área Verde");
        depAreaVerde.setToolTipText("");
        depAreaVerde.setEnabled(false);

        depLavanderia.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        depLavanderia.setText("Lavanderia");
        depLavanderia.setEnabled(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(depQuadraDesc)
                            .addComponent(depParque)
                            .addComponent(jLabel13))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(depSalaDeLeitura)
                            .addComponent(depBiblioteca)
                            .addComponent(depCozinha)
                            .addComponent(depQuadraCoberta)
                            .addComponent(depAtenEduEsp)
                            .addComponent(depLabCien)
                            .addComponent(depLabInfo)
                            .addComponent(depProf)
                            .addComponent(depDiretoria))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(depBanheiroFora)
                            .addComponent(depBanheiro)
                            .addComponent(depBanheiroInfa)
                            .addComponent(depBanheiroMobiReduz)
                            .addComponent(depDepAlunosDef)
                            .addComponent(depSecretaria)
                            .addComponent(depBanChuveiro)
                            .addComponent(depRefeitorio)
                            .addComponent(depBerc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(depDespensa)
                            .addComponent(depAlmoxarifado)
                            .addComponent(depAuditorio)
                            .addComponent(depPatioCoberto)
                            .addComponent(depPatioDescoberto)
                            .addComponent(depAlojAluno)
                            .addComponent(depAlojProf)
                            .addComponent(depAreaVerde)
                            .addComponent(depLavanderia))
                        .addGap(108, 108, 108))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(depDiretoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depProf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depLabInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depLabCien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depAtenEduEsp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depQuadraCoberta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depCozinha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depBiblioteca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depSalaDeLeitura))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(depDespensa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depAlmoxarifado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depAuditorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depPatioCoberto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depPatioDescoberto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depAlojAluno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depAlojProf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depAreaVerde)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depLavanderia))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(depBerc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depBanheiroFora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depBanheiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depBanheiroInfa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depBanheiroMobiReduz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depDepAlunosDef)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depSecretaria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depBanChuveiro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(depRefeitorio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(depParque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(depQuadraDesc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel8);

        jScrollPane1.setViewportView(jPanel5);

        jPanel4.add(jScrollPane1);

        add(jPanel4);

        jPanel6.setBackground(new java.awt.Color(200, 200, 200));
        jPanel6.setPreferredSize(new java.awt.Dimension(671, 40));

        favoritoCheck.setBackground(jPanel6.getBackground());
        favoritoCheck.setFont(new java.awt.Font("Liberation Sans", 1, 12)); // NOI18N
        favoritoCheck.setForeground(new java.awt.Color(0, 0, 0));
        favoritoCheck.setText("Favoritado");
        favoritoCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                favoritoCheckItemStateChanged(evt);
            }
        });
        favoritoCheck.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                favoritoCheckStateChanged(evt);
            }
        });
        favoritoCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoritoCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(561, Short.MAX_VALUE)
                .addComponent(favoritoCheck)
                .addGap(26, 26, 26))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(favoritoCheck)
                .addContainerGap())
        );

        add(jPanel6);
    }// </editor-fold>//GEN-END:initComponents

    private void abaPocoArtesianoCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abaPocoArtesianoCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abaPocoArtesianoCheckActionPerformed

    private void favoritoCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoritoCheckActionPerformed
        // TODO add your handling code here:
        final var valorAEnviar = 
                this.favoritoCheck.isSelected() ? "true": "false";
        
        
        if (this.sessao.estaLogado()) {                    
            final var token = this.sessao.pegarDados().pegarLocalId();
            final String caminho;
            caminho = "/users/" + token + "/escolas/" + this.escolaId + 
                      "/favoritada";
            
            ControlaThreads.executor.submit(() -> {
                boolean falhou = false;
                
                var chamada = Firebase.gravadorDados(
                    caminho, 
                    this.sessao.pegarDados().pegarTokenId(),
                    valorAEnviar);
                
                Response<JsonElement> resposta = null;
                
                try {
                    resposta = chamada.execute();
                } catch(IOException exc) {
                    Logger.getLogger(DetalhesEscolaUI.class.getName()).log(
                            Level.WARNING,
                            exc,
                            null
                    );
                    falhou = true;                    
                }
                
                if (resposta == null || !resposta.isSuccessful()) {                    
                    falhou = true;
                }
                    
                    
                if (falhou) {
                    SwingUtilities.invokeLater(() -> {
                        this.favoritoCheck.setSelected(
                                !this.favoritoCheck.isSelected()
                        );
                    });
                }
            });
        }
    }//GEN-LAST:event_favoritoCheckActionPerformed

    private void favoritoCheckStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_favoritoCheckStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_favoritoCheckStateChanged

    private void favoritoCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_favoritoCheckItemStateChanged
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_favoritoCheckItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox abaCisternaCheck;
    private javax.swing.JCheckBox abaFonteCheck;
    private javax.swing.JCheckBox abaInexistenteCheck;
    private javax.swing.JCheckBox abaPocoArtesianoCheck;
    private javax.swing.JCheckBox abaRedePublicaCheck;
    private javax.swing.JCheckBox depAlmoxarifado;
    private javax.swing.JCheckBox depAlojAluno;
    private javax.swing.JCheckBox depAlojProf;
    private javax.swing.JCheckBox depAreaVerde;
    private javax.swing.JCheckBox depAtenEduEsp;
    private javax.swing.JCheckBox depAuditorio;
    private javax.swing.JCheckBox depBanChuveiro;
    private javax.swing.JCheckBox depBanheiro;
    private javax.swing.JCheckBox depBanheiroFora;
    private javax.swing.JCheckBox depBanheiroInfa;
    private javax.swing.JCheckBox depBanheiroMobiReduz;
    private javax.swing.JCheckBox depBerc;
    private javax.swing.JCheckBox depBiblioteca;
    private javax.swing.JCheckBox depCozinha;
    private javax.swing.JCheckBox depDepAlunosDef;
    private javax.swing.JCheckBox depDespensa;
    private javax.swing.JCheckBox depDiretoria;
    private javax.swing.JCheckBox depLabCien;
    private javax.swing.JCheckBox depLabInfo;
    private javax.swing.JCheckBox depLavanderia;
    private javax.swing.JCheckBox depParque;
    private javax.swing.JCheckBox depPatioCoberto;
    private javax.swing.JCheckBox depPatioDescoberto;
    private javax.swing.JCheckBox depProf;
    private javax.swing.JCheckBox depQuadraCoberta;
    private javax.swing.JCheckBox depQuadraDesc;
    private javax.swing.JCheckBox depRefeitorio;
    private javax.swing.JCheckBox depSalaDeLeitura;
    private javax.swing.JCheckBox depSecretaria;
    private javax.swing.JCheckBox destLColPerCheck;
    private javax.swing.JCheckBox destLEnterraCheck;
    private javax.swing.JCheckBox destLJogaAreaCheck;
    private javax.swing.JCheckBox destLOutrosCheck;
    private javax.swing.JCheckBox destLQueimaCheck;
    private javax.swing.JCheckBox destLReciclaCheck;
    private javax.swing.JLabel enderecoEscola;
    private javax.swing.JCheckBox enerGeradorCheck;
    private javax.swing.JCheckBox enerInexistenteCheck;
    private javax.swing.JCheckBox enerOutrosCheck;
    private javax.swing.JCheckBox enerRedePublicaCheck;
    private javax.swing.JCheckBox esgtFossaCheck;
    private javax.swing.JCheckBox esgtInexistenteCheck;
    private javax.swing.JCheckBox esgtRedePublicaCheck;
    private javax.swing.JLabel estadoEscola;
    private javax.swing.JCheckBox favoritoCheck;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel municipioEscola;
    private javax.swing.JLabel regulamentadaEscola;
    private javax.swing.JLabel situacaoEscola;
    private javax.swing.JLabel tipoEscola;
    private javax.swing.JLabel tituloEscola;
    // End of variables declaration//GEN-END:variables
}
