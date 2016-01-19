/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packVue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import packControleur.ContrSuppr;
import packControleur.ControleurAbstrait;

import packModele.Enseignant;
import packModele.QCM;

/**
 *
 * @author Admin
 */
public class VueEnseignant extends javax.swing.JPanel {
    private final Enseignant enseignant;  
    private VueDetails vueDetails;
    
    private final ContrSuppr contrSuppr;
    
    public VueEnseignant(Enseignant enseignant, ControleurAbstrait contrSuppr) {
        this.enseignant = enseignant;
        this.contrSuppr = (ContrSuppr) contrSuppr;
                
        initComponents();
        labelBienvenue.setText("Bienvenue, " + enseignant.prenommer() + " " + enseignant.nommer());
        boutonCreation.addActionListener(new Ecouteur_AccesGestion());
        tableauQCM.addMouseListener(new Ecouteur_AccesGestion());
        
        // Récupération des QCM dans la JTable
        remplirTableau();
    }
    
    public void remplirTableau() {
        System.out.println("CONSTRUCTION DU TABLEAU " + enseignant.getQCM().size());
        int i = 0;
        tableauQCM.getTableHeader().setReorderingAllowed(false);
        tableauQCM.getTableHeader().setResizingAllowed(false);
        
        // Régler la taille de certaines colonnes
        tableauQCM.getColumnModel().getColumn(0).setPreferredWidth(200);
        tableauQCM.getColumnModel().getColumn(1).setPreferredWidth(90);
        
        // Centrer la dernière colonne à l'aide d'une affectation de rendu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setForeground(Color.decode("#D90115"));
        tableauQCM.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        // Remplir le tableau via les données des QCM
        for (QCM qcm : enseignant.getQCM()) {
            tableauQCM.setValueAt(qcm, i, 0);
            tableauQCM.setValueAt(qcm.getClasse(), i, 1);
            tableauQCM.setValueAt(qcm.getEtat(), i, 2);
            tableauQCM.setValueAt("Supprimer", i, 3);

            i++;
        }
        
        tableauQCM.setValueAt(null, i, 0);
        tableauQCM.setValueAt(null, i, 1);
        tableauQCM.setValueAt(null, i, 2);
        tableauQCM.setValueAt(null, i, 3);
    }
    
    public class Ecouteur_AccesGestion implements ActionListener, MouseListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == boutonCreation) {
                enseignant.notifyObservateurs("creerQCM");
            } else if (e.getSource() == tableauQCM) {
                ;
            }
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            if ((me.getSource() == tableauQCM) && (me.getClickCount() == 2)) {
                int ligne = tableauQCM.rowAtPoint(me.getPoint());
                int colonne = tableauQCM.columnAtPoint(me.getPoint());

                if ((colonne == 0) && (tableauQCM.getValueAt(ligne, 0) != null)) {
                    QCM questionnaire = (QCM) tableauQCM.getValueAt(ligne, colonne);
                    vueDetails = new VueDetails((JFrame) SwingUtilities.getWindowAncestor(VueEnseignant.this), questionnaire);
                }
                
                if ((colonne == 3) && (tableauQCM.getValueAt(ligne, 3) != null)) {
                    QCM questionnaire = (QCM) tableauQCM.getValueAt(ligne, 0);
                    ArrayList<String> donnees = new ArrayList<>();
                    
                    donnees.add(Integer.toString(questionnaire.getId()));
                    contrSuppr.donnerEnseignant(enseignant);
                    contrSuppr.control(donnees);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
            ;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            ;
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            ;
        }

        @Override
        public void mouseExited(MouseEvent me) {
            ;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new JLabel();
        jLabel1 = new JLabel();
        labelBienvenue = new JLabel();
        labelSousTitre = new JLabel();
        boutonCreation = new JButton();
        jScrollPane2 = new JScrollPane();
        tableauQCM = new JTable();
        jSeparator1 = new JSeparator();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packVue/image/bandeau_bas.jpg"))); // NOI18N

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/packVue/image/bandeau_bas.jpg"))); // NOI18N

        labelBienvenue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelBienvenue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBienvenue.setText("Bienvenue, [enseignant]");

        labelSousTitre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSousTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSousTitre.setText("Créez ou consultez l'état de vos QCM.");

        boutonCreation.setText("Créer un QCM");
        boutonCreation.setBackground(new java.awt.Color(255, 204, 0));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tableauQCM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "QCM", "Classe", "État", "Supprimer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
               @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tableauQCM.setSelectionBackground(new java.awt.Color(255, 204, 0));
        tableauQCM.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(tableauQCM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelBienvenue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSousTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(boutonCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelBienvenue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSousTitre)
                .addGap(40, 40, 40)
                .addComponent(boutonCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonCreation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelBienvenue;
    private javax.swing.JLabel labelSousTitre;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableauQCM;
    // End of variables declaration//GEN-END:variables
}
