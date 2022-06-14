
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
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        openFileButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bonjour bienvenue sur notre application de Graph-Map");
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Veuillez charger un fichier pour commencer.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Vous disposez du menu <Vue> en haut dans le menu bar pour choisir l'option que vous souhaitez.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(jLabel3, gridBagConstraints);

        openFileButton.setText("Open");
        openFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });
        jPanel1.add(openFileButton, new java.awt.GridBagConstraints());

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        getMainInterface().openFile();
    }//GEN-LAST:event_openFileButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton openFileButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
          
    }
}
