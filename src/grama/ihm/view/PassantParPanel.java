/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package grama.ihm.view;

import grama.calcule.matrix.FloydWarshall;
import grama.calcule.matrix.MatriceChemin;
import grama.exceptions.CheminImpossibleErreur;
import grama.graph.Chemin;
import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;
import grama.ihm.DrawGraphPanel;
import grama.ihm.Drawable;
import grama.ihm.MainInterface;
import grama.ihm.WarningDialog;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.html.HTMLDocument;

/**
 * Permet de trouver le chemin le plus cours entre 2 neouds en passant par certain type de noeuds
 *
 * @author twarr
 */
public class PassantParPanel extends InfoAbstractPanel {

    private LinkedList<JComboBox<Noeud.Type>> listeType;
    private LinkedList<JSpinner> listeNombreType;
    private MatriceChemin cheminMatrice;

    /**
     * Creates new form PassantParPanel
     *
     * @param parent la fenetre parente
     */
    public PassantParPanel(MainInterface parent) {
        super(parent);
        initComponents();
        listeType = new LinkedList<>();
        listeNombreType = new LinkedList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adderPanel = new javax.swing.JPanel();
        rechercheButton = new javax.swing.JButton();
        addElementButton = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Chemin passant par");
        add(jLabel2);

        adderPanel.setLayout(new javax.swing.BoxLayout(adderPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(adderPanel);

        add(jScrollPane1);

        rechercheButton.setText("Chercher");
        rechercheButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheButtonActionPerformed(evt);
            }
        });
        add(rechercheButton);

        addElementButton.setText("Ajouter un élément");
        addElementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addElementButtonActionPerformed(evt);
            }
        });
        add(addElementButton);
    }// </editor-fold>//GEN-END:initComponents

    private void addElementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addElementButtonActionPerformed
        // TODO add your handling code here:
        DrawGraphPanel graphPanel = getMainInterface().getDrawGraphPanel();
        if (graphPanel != null && graphPanel.getGraph() != null) {
            addBoxInLayout(graphPanel.getGraph());
        }

    }//GEN-LAST:event_addElementButtonActionPerformed

    private void rechercheButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheButtonActionPerformed
        // TODO add your handling code here:
        DrawGraphPanel graphPanel = getMainInterface().getDrawGraphPanel();
        if (graphPanel != null && graphPanel.getGraph() != null) {
            graphPanel.setHighlited(null);

            if (cheminMatrice == null) {
                cheminMatrice = new MatriceChemin(graphPanel.getGraph());
            }
            List listeTypeTraitement = new LinkedList<Noeud.Type>();
            Iterator<JComboBox<Noeud.Type>> iteraType = listeType.iterator();
            Iterator<JSpinner> iteraQuantiter = listeNombreType.iterator();
            JComboBox<Noeud.Type> jcombobox;
            JSpinner jspinner;
            while (iteraType.hasNext() && iteraQuantiter.hasNext()) {
                jcombobox = iteraType.next();
                if (jcombobox.getSelectedItem() != Noeud.Type.NONE) {
                    jspinner = iteraQuantiter.next();
                    for (int i = 0; i < (int) jspinner.getValue(); i++) {
                        listeTypeTraitement.add(jcombobox.getSelectedItem());
                    }
                }
            }
            Noeud depart = graphPanel.getSelectedNodes().get(0);
            Noeud arriver = graphPanel.getSelectedNodes().get(1);
            graphPanel.getPanelLegende().NoeudCorrespondVisible(false);
            graphPanel.getPanelLegende().cheminVisible(true);

            try {
                if (depart != null && arriver != null) {
                    Chemin chemins = cheminMatrice.getCheminBetween(depart, arriver, listeTypeTraitement, FloydWarshall.getInstanceKilometrage());
                    List<Drawable> liens = new LinkedList<>();
                    for (Lien lien : chemins.getChemin()) {
                        liens.add((Drawable) lien);
                    }

                    graphPanel.setHighlited(liens);
                    graphPanel.repaint();
                }
            } catch (CheminImpossibleErreur ex) {
                WarningDialog.showWarningDialog(getMainInterface(), ex.getMessage());
            }
        }
    }//GEN-LAST:event_rechercheButtonActionPerformed

    public void addBoxInLayout(Graph graphmap) {
        SpinnerNumberModel model = new SpinnerNumberModel(1, 0, graphmap.getListNoeud().size(), 1);
        JComboBox listeChoix = new JComboBox<Noeud.Type>();
        JSpinner nombre = new javax.swing.JSpinner(model);

        JPanel panelDisposition = new JPanel();
        panelDisposition.setLayout(new java.awt.GridBagLayout());

        listeChoix.addItem(Noeud.Type.VILLE);
        listeChoix.addItem(Noeud.Type.RESTAURANT);
        listeChoix.addItem(Noeud.Type.LOISIR);
        listeChoix.addItem(Noeud.Type.NONE);
        listeChoix.setSelectedItem(Noeud.Type.NONE);
        adderPanel.add(panelDisposition);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 15);
        panelDisposition.add(nombre, c);
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        panelDisposition.add(listeChoix, c);

        listeType.add(listeChoix);
        listeNombreType.add(nombre);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addElementButton;
    private javax.swing.JPanel adderPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton rechercheButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        DrawGraphPanel graphPanel = getMainInterface().getDrawGraphPanel();
        if (graphPanel != null && graphPanel.getGraph() != null) {
            graphPanel.setLinkSelectable(false);
        }
    }
}
