package grama.ihm;

import grama.calcule.matrix.FloydWarshall;
import grama.exceptions.FormatFileException;
import grama.formater.StringFormater;
import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;
import grama.ihm.view.InfoAbstractPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import grama.ihm.view.VoisinDirect;
import grama.ihm.view.Comparaison;
import grama.ihm.view.Voisin2SautPanel;
import grama.ihm.view.CheminGraphPanel;
import grama.ihm.view.Acceuil;
import javax.swing.UIManager;

public class MainInterface extends javax.swing.JFrame implements Updatable {

    enum ViewMode {
        PRINCIPAL(0),
        AFFICHAGE(1),
        VOISIN_DIRECT(1),
        VOISIN2SAUT(1),
        COMPARAISON(2),
        CHEMIN(2);

        private final int nbrSelectableNode;

        private ViewMode(int a) {
            this.nbrSelectableNode = a;
        }

        public int getNbrSelectableNode() {
            return nbrSelectableNode;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

    }

    public DrawGraphPanel getDrawGraphPanel() {
        return drawGraphPanel;
    }

    private DrawGraphPanel drawGraphPanel;
    private ViewMode currMode = ViewMode.PRINCIPAL;

    private ButtonGroup groupView = new ButtonGroup();

    private void loadFile(File fileGraph) throws IOException {
        Graph graphmap = new Graph();

        graphmap.loadFromString(StringFormater.readFile(fileGraph));

        loadNewGraph(graphmap);
        update();
    }

    private void loadNewGraph(Graph graph) {
        drawGraphPanel = new DrawGraphPanel(this, graph, getFont(), Noeud.Type.ALL, Lien.Type.ALL);
        splitPanel.setRightComponent(drawGraphPanel);
        FloydWarshall.getInstanceKilometrage().initKilometrage(graph).resolve();
        FloydWarshall.getInstanceSaut().initSaut(graph).resolve();

        switchToMode(ViewMode.AFFICHAGE);
        affichageMenuItem.setSelected(true);

    }

    /**
     * Creates new form MainInterface
     */
    public MainInterface() {
        initComponents();
        Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/res/logo.png"));
        this.setSize(800, 600);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        this.setIconImage(icon);

        splitPanel.setRightComponent(null);
        splitPanel.setDividerLocation(0.25);

        groupView.add(principalMenuItem);
        groupView.add(affichageMenuItem);
        groupView.add(voisinDirectMenuItem);
        groupView.add(voisin2ndMenuItem);
        groupView.add(comparaisonMenuItem);
        groupView.add(cheminMenuItem);

        switchToMode(currMode);

        this.update();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPanel = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        acceuilPanel = new Acceuil(this);
        infoPanel = new grama.ihm.view.InfoGraphPanel(this);
        voisinDirectPanel = new VoisinDirect(this);
        voisin2Panel = new Voisin2SautPanel(this);
        comparaisonPanel = new Comparaison(this);
        cheminPanel = new CheminGraphPanel(this);
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        OpenMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        principalMenuItem = new javax.swing.JRadioButtonMenuItem();
        affichageMenuItem = new javax.swing.JRadioButtonMenuItem();
        voisinDirectMenuItem = new javax.swing.JRadioButtonMenuItem();
        voisin2ndMenuItem = new javax.swing.JRadioButtonMenuItem();
        comparaisonMenuItem = new javax.swing.JRadioButtonMenuItem();
        cheminMenuItem = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grama");
        setMinimumSize(new java.awt.Dimension(144, 144));
        setSize(new java.awt.Dimension(720, 480));

        splitPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        leftPanel.setLayout(new java.awt.CardLayout());

        acceuilPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acceuilPanelMouseClicked(evt);
            }
        });
        leftPanel.add(acceuilPanel, "principal");
        leftPanel.add(infoPanel, "affichage");
        leftPanel.add(voisinDirectPanel, "voisin_direct");
        leftPanel.add(voisin2Panel, "voisin2saut");
        leftPanel.add(comparaisonPanel, "comparaison");
        leftPanel.add(cheminPanel, "chemin");

        splitPanel.setLeftComponent(leftPanel);

        getContentPane().add(splitPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("Fichier");

        OpenMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        OpenMenuItem.setText("Ouvrir");
        OpenMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(OpenMenuItem);

        jMenuBar1.add(fileMenu);

        ViewMenu.setText("Vue");
        ViewMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewMenuActionPerformed(evt);
            }
        });

        principalMenuItem.setSelected(true);
        principalMenuItem.setText("principal");
        principalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                principalMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(principalMenuItem);

        affichageMenuItem.setText("affichage");
        affichageMenuItem.setEnabled(false);
        affichageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                affichageMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(affichageMenuItem);

        voisinDirectMenuItem.setSelected(true);
        voisinDirectMenuItem.setText("voisins direct");
        voisinDirectMenuItem.setEnabled(false);
        voisinDirectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voisinDirectMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(voisinDirectMenuItem);

        voisin2ndMenuItem.setSelected(true);
        voisin2ndMenuItem.setText("voisins 2 sauts");
        voisin2ndMenuItem.setEnabled(false);
        voisin2ndMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voisin2ndMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(voisin2ndMenuItem);

        comparaisonMenuItem.setSelected(true);
        comparaisonMenuItem.setText("comparaison");
        comparaisonMenuItem.setEnabled(false);
        comparaisonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comparaisonMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(comparaisonMenuItem);

        cheminMenuItem.setSelected(true);
        cheminMenuItem.setText("chemin");
        cheminMenuItem.setEnabled(false);
        cheminMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheminMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(cheminMenuItem);

        jMenuBar1.add(ViewMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OpenMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenMenuItemActionPerformed
        openFile();
    }//GEN-LAST:event_OpenMenuItemActionPerformed

    private void ViewMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMenuActionPerformed

    }//GEN-LAST:event_ViewMenuActionPerformed

    private void voisinDirectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voisinDirectMenuItemActionPerformed
        switchToMode(ViewMode.VOISIN_DIRECT);
    }//GEN-LAST:event_voisinDirectMenuItemActionPerformed

    private void affichageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_affichageMenuItemActionPerformed
        switchToMode(ViewMode.AFFICHAGE);
    }//GEN-LAST:event_affichageMenuItemActionPerformed

    private void voisin2ndMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voisin2ndMenuItemActionPerformed
        switchToMode(ViewMode.VOISIN2SAUT);
    }//GEN-LAST:event_voisin2ndMenuItemActionPerformed

    private void cheminMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheminMenuItemActionPerformed
        switchToMode(ViewMode.CHEMIN);
    }//GEN-LAST:event_cheminMenuItemActionPerformed

    private void comparaisonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comparaisonMenuItemActionPerformed
        switchToMode(ViewMode.COMPARAISON);
    }//GEN-LAST:event_comparaisonMenuItemActionPerformed

    private void principalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_principalMenuItemActionPerformed
        switchToMode(ViewMode.PRINCIPAL);
    }//GEN-LAST:event_principalMenuItemActionPerformed

    private void acceuilPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acceuilPanelMouseClicked
    }//GEN-LAST:event_acceuilPanelMouseClicked

    /**
     * show a dialog window for a warning
     *
     * @param parent the parent Component of this new dialog window
     * @param msg the message do display in this new dialog window
     */
    private void showWarningDialog(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Grama", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * show a dialog window for warn about the read right of a file
     *
     * @param file the file with wrong right.
     */
    private void showReadRightWarning(File file) {
        showWarningDialog(this, file.getAbsolutePath() + "\nVous n'avez pas l'autorisation d'ouvrir ce fichier. Consultez le propriétaire du fichier ou un administrateur pour obtenir cette autorisation");

    }

    /**
     * launch a graphical way to open a file
     */
    private void openFile() {
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.setMultiSelectionEnabled(false);
        fileOpen.setFileFilter(new FileNameExtensionFilter("fichier en .csv", "csv"));

        int res = fileOpen.showOpenDialog(this);
        File file = fileOpen.getSelectedFile();
        while (res == JFileChooser.APPROVE_OPTION && file != null && !file.canRead()) {
            showReadRightWarning(file);
            res = fileOpen.showOpenDialog(this);
            file = fileOpen.getSelectedFile();
        }

        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                loadFile(file);
            } catch (FormatFileException e) {
                showWarningDialog(this, "Erreur à la ligne : " + e.getLineNumber() + "\n" + e.getNonConforme());
            } catch (IOException ex) {
                showReadRightWarning(file);
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainInterface mainInterface = new MainInterface();
                mainInterface.setLocationRelativeTo(null);
                mainInterface.setVisible(true);
            }
        });
    }

    public static void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainInterface mainInterface = new MainInterface();
            mainInterface.setLocationRelativeTo(null);
            mainInterface.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem OpenMenuItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JPanel acceuilPanel;
    private javax.swing.JRadioButtonMenuItem affichageMenuItem;
    private javax.swing.JRadioButtonMenuItem cheminMenuItem;
    private javax.swing.JPanel cheminPanel;
    private javax.swing.JRadioButtonMenuItem comparaisonMenuItem;
    private javax.swing.JPanel comparaisonPanel;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JRadioButtonMenuItem principalMenuItem;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JPanel voisin2Panel;
    private javax.swing.JRadioButtonMenuItem voisin2ndMenuItem;
    private javax.swing.JRadioButtonMenuItem voisinDirectMenuItem;
    private javax.swing.JPanel voisinDirectPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        if (drawGraphPanel == null) {
            return;
        }
        if (null != currMode) {
            switch (currMode) {
                case AFFICHAGE:
                    ((InfoAbstractPanel) infoPanel).update();
                    break;
                case VOISIN2SAUT:
                    ((InfoAbstractPanel) voisin2Panel).update();
                    break;
                case CHEMIN:
                    ((InfoAbstractPanel) cheminPanel).update();
                    break;
                case VOISIN_DIRECT:
                    ((InfoAbstractPanel) voisinDirectPanel).update();
                    break;
                case COMPARAISON:
                    ((InfoAbstractPanel) comparaisonPanel).update();
                    break;
                default:
                    break;
            }
        }
        System.out.println("update : " + currMode);
    }

    public void switchToMode(ViewMode mode) {

        this.currMode = mode;
        if (leftPanel.getLayout() instanceof CardLayout && ((drawGraphPanel != null && drawGraphPanel.getGraph() != null) || mode == ViewMode.PRINCIPAL)) {

            CardLayout crd = (CardLayout) leftPanel.getLayout();
            crd.show(leftPanel, this.currMode.toString());
            if (mode != ViewMode.PRINCIPAL) {
                drawGraphPanel.setTypeLien(Lien.Type.ALL);
                drawGraphPanel.setTypeNoeud(Noeud.Type.ALL);

                drawGraphPanel.setNbrSelectableNode(currMode.getNbrSelectableNode());
                principalMenuItem.setEnabled(false);

                affichageMenuItem.setEnabled(true);
                cheminMenuItem.setEnabled(true);
                voisin2ndMenuItem.setEnabled(true);
                voisinDirectMenuItem.setEnabled(true);
                comparaisonMenuItem.setEnabled(true);
            }

            update();
            repaint();
        } else {
            principalMenuItem.setSelected(true);
            switchToMode(ViewMode.PRINCIPAL);

            affichageMenuItem.setEnabled(false);
            cheminMenuItem.setEnabled(false);
            voisin2ndMenuItem.setEnabled(false);
            voisinDirectMenuItem.setEnabled(false);
            comparaisonMenuItem.setEnabled(false);
        }
        splitPanel.setDividerLocation(0.25);
    }
}
