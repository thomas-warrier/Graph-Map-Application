/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package grama.ihm.view;

import grama.calcule.matrix.FloydWarshall;
import grama.calcule.matrix.MatriceChemin;
import grama.graph.Chemin;
import grama.graph.Lien;
import grama.graph.Noeud;
import grama.ihm.DrawGraphPanel;
import grama.ihm.Drawable;
import grama.ihm.MainInterface;
import java.awt.CheckboxGroup;
import java.util.LinkedList;
import java.util.List;

/**
 * Panel qui permet d'afficher le chemin le plus cours entre 2 noeuds
 * @author virgile
 */
public class CheminGraphPanel extends InfoAbstractPanel {

    private MatriceChemin matriceChemin;

    public CheminGraphPanel(MainInterface parent) {
        super(parent);
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nbrSaut = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nbrKilometre = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 40));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chemin");
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        nbrSaut.setText("null");
        jPanel1.add(nbrSaut, java.awt.BorderLayout.EAST);

        jLabel3.setText("nombre de sauts :");
        jPanel1.add(jLabel3, java.awt.BorderLayout.WEST);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("nombre de kilomètre :");
        jPanel2.add(jLabel2, java.awt.BorderLayout.WEST);

        nbrKilometre.setText("null");
        jPanel2.add(nbrKilometre, java.awt.BorderLayout.EAST);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void update() {
        DrawGraphPanel graphPanel = getMainInterface().getDrawGraphPanel();
        nbrSaut.setText("0");
        nbrKilometre.setText("0");
        if (graphPanel != null && graphPanel.getGraph() != null) {
            if (matriceChemin == null) {
                matriceChemin = new MatriceChemin(graphPanel.getGraph());
            }

            Noeud depart = graphPanel.getSelectedNodes().get(0);
            Noeud arriver = graphPanel.getSelectedNodes().get(1);
            graphPanel.getPanelLegende().NoeudCorrespondVisible(false);
            graphPanel.getPanelLegende().cheminVisible(true);

            if (depart != null && arriver != null) {
                Chemin chemins = matriceChemin.getCheminBetween(depart, arriver);
                List<Drawable> liens = new LinkedList<>();
                for (Lien lien : chemins.getChemin()) {
                    liens.add((Drawable) lien);
                }

                graphPanel.setHighlited(liens);

                nbrSaut.setText(String.valueOf(chemins.getChemin().size()));
                nbrKilometre.setText(String.valueOf(chemins.getKilometrageChemin()));
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nbrKilometre;
    private javax.swing.JLabel nbrSaut;
    // End of variables declaration//GEN-END:variables
}
