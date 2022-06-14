/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package grama.ihm.view;

import grama.ihm.MainInterface;

/**
 * cette classe permet d'avoir un panel d'accueil pour l'utilisateur lorsqu'il lance notre application
 * @author twarr
 */
public class Acceuil extends InfoAbstractPanel{

    public Acceuil(MainInterface parent) {
        super(parent);
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bonjour bienvenue sur notre application de Graph-Map");
        add(jLabel1);

        jLabel2.setText("Veuillez charez un fichier pour commencer.");
        add(jLabel2);

        jLabel3.setText("Vous disposez du menu <Vue> en haut dans le menu bar pour choisir l'option que vous souhaitez");
        add(jLabel3);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
          
    }
}
