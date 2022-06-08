package grama.ihm.view;

import grama.ihm.MainInterface;
import grama.ihm.Updatable;

/**
 * Panel dont les panels form héritent
 * @author twarr
 */
public abstract class InfoAbstractPanel extends javax.swing.JPanel implements Updatable {
    private final MainInterface parent;

    public InfoAbstractPanel(MainInterface parent) {
        this.parent = parent;
    }

    @Override
    public MainInterface getParent() {
        return parent;
    }
}
