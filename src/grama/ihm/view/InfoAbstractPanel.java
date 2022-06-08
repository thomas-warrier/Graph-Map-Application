package grama.ihm.view;

import grama.ihm.MainInterface;
import grama.ihm.Updatable;

/**
 * Panel dont les panels form héritent
 * @author twarr
 */
public abstract class InfoAbstractPanel extends javax.swing.JPanel implements Updatable {
    private final MainInterface mainInterface;

    public InfoAbstractPanel(MainInterface parent) {
        this.mainInterface = parent;
    }

    
    public MainInterface getMainInterface() {
        return mainInterface;
    }
}
