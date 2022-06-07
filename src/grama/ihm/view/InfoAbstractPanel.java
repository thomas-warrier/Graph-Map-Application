/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.ihm.view;

import grama.ihm.MainInterface;
import grama.ihm.Updatable;

/**
 * panel dont les panels form héritent
 * @author twarr
 */
public abstract class InfoAbstractPanel  extends javax.swing.JPanel implements Updatable {
    private MainInterface parent;

    public InfoAbstractPanel(MainInterface parent) {
        this.parent = parent;
    }

    public MainInterface getParent() {
        return parent;
    }
    
    
}
