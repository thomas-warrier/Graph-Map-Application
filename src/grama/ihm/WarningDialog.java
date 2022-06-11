/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.ihm;

import java.awt.Component;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author wwwazz
 */
public class WarningDialog {
    /**
     * show a dialog window for a warning
     *
     * @param parent the parent Component of this new dialog window
     * @param msg the message do display in this new dialog window
     */
    public static void showWarningDialog(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Grama", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * show a dialog window for warn about the read right of a file
     *
     * @param file the file with wrong right.
     */
    public static void showReadRightWarning(Component parent, File file) {
        WarningDialog.showWarningDialog(parent, file.getAbsolutePath() + "\nVous n'avez pas l'autorisation d'ouvrir ce fichier. Consultez le propriétaire du fichier ou un administrateur pour obtenir cette autorisation");

    }
}
