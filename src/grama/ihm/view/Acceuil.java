/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package grama.ihm.view;

import grama.ihm.MainInterface;

/**
 *
 * @author twarr
 */
public class Acceuil extends InfoAbstractPanel{

    /**
     * Creates new form Acceuil
     */
    public Acceuil(MainInterface parent) {
        super(parent);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setLayout(new java.awt.GridLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTextPane1.setEditable(false);
        jTextPane1.setText("Bonjour bienvenue sur notre application de Graph-Map,\n\nveuillez charger un fichier pour commencer.\n\nVous disposez du menu <vue> en haut dans la menue bar pour choisir l'option que vous souhaitez");
        jScrollPane1.setViewportView(jTextPane1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        
    }
}