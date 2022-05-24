package grama.ihm;

import grama.calcule.vector.Vector2D;
import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * Panel pour dessiner un Graph
 *
 * @author virgile
 */
public class DrawGraphPanel extends JPanel implements MouseMotionListener {

    private final Graph graph;
    private Noeud.Type typeNoeud;
    private Lien.Type typeLien;

    private Noeud selectedNode;
    private Noeud toMove;

    private Noeud[] selectedNodes;
    private int currSelectedNode;

    
    private Dimension prevSizePanel;
    private double scale;

    

    /**
     * instansie un panel pour dessiner un graph
     *
     * @param parentFrame la fenêtre parente (pour mettre à jours les info)
     * @param graph le graph à dessiner
     * @param font la policer d'écriture
     * @param typeNoeud le type des noeud à afficher
     * @param typeLien le type des lien à afficher (entre les noeuds affiché)
     */
    public DrawGraphPanel(Updatable parentFrame, Graph graph, Font font, Noeud.Type typeNoeud, Lien.Type typeLien) {
        this.graph = graph;
        this.typeNoeud = typeNoeud;
        this.typeLien = typeLien;
        
        this.init(parentFrame, font);
    }
    /**
     * instansie un panel pour dessiner un graph
     *
     * @param parentFrame la fenêtre parente (pour mettre à jours les info)
     * @param graph le graph à dessiner
     * @param font la policer d'écriture
     */
    public DrawGraphPanel(Updatable parentFrame, Graph graph, Font font) {
        this(parentFrame, graph, font, Noeud.Type.ALL, Lien.Type.ALL);
    }

    /**
     * Inisialise le panel
     *
     * @param parentFrame la fenêtre parente (pour mettre à jours les info)
     * @param font la policer d'écriture
     */
    private void init(Updatable parentFrame, Font font) {
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(200, 200));

        setFont(font);
        setNbrSelectableNode(1);

        prevSizePanel = getSize();
        scale = 1.0;
        initNoeudsLocation();

        this.addMouseMotionListener(this);
        this.addMouseListener(new java.awt.event.MouseAdapter() {//pour la selection des noeuds

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                
                Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
                for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
                    if (noeud.getLastLocation() == null) {
                        continue;
                    }
                    if (noeud.getLastLocation().sub(mousePos).norm() <= Noeud.DIAMETRE / 2) {//on click Noeud
                        selectedNodes[currSelectedNode++ % selectedNodes.length] = noeud;
                    }
                }
                repaint();
                parentFrame.update();
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                
                Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
                for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
                    if (noeud.getLastLocation() == null) {
                        continue;
                    }
                    if (noeud.getLastLocation().sub(mousePos).norm() <= Noeud.DIAMETRE / 2) {//on click Noeud
                        toMove = noeud;
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                
                toMove = null;
            }

        });

    }

    /**
     * dessine le graphe au centre du panel (en cercle)
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        //ANTIALIASING
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.clearRect(0, 0, getWidth(), getHeight());//clear les anciens dessins

        //draw les noeuds et liens entre ces noeuds (uniquement)
        drawNoeuds(g);
        drawLien(g);
        
        prevSizePanel = getSize();
    }

    public void drawLien(Graphics g) {
        for (Lien lien : graph.getListLienOfType(typeLien)) {
            lien.draw(g, null, getFont());//affiche en fonction des position des neouds qu'il relie s'il on été affiché
        }
    }

    public void initNoeudsLocation() {
        Vector2D center = new Vector2D(getWidth() / 2, getHeight() / 2);
        Vector2D rayon = new Vector2D(0, -1.0 * (Math.min(getWidth() / 2, getHeight() / 2) - Noeud.DIAMETRE / 2));//oriente vers le haut pour placer le 1er noeud
        double angleRot = (2 * Math.PI) / graph.getListNoeud().size();//anlge de rotation pour dessiner les neouds en cercle
        for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
            //position du Noeud est au centre + le vec rayon (qui à la bonne direction en fonction de l'angle)
            noeud.setLastLocation(center.add(rayon));

            rayon = rayon.rotateOf(angleRot);//fait tourner le vec rayon
        }
        
    }

    public void drawNoeuds(Graphics g) {
        for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
            if (isSelected(noeud)) {
                g.setColor(Color.yellow);
                noeud.draw(g, noeud.getLastLocation(), getFont());
                g.setColor(Color.BLACK);
            } else {
                noeud.draw(g, noeud.getLastLocation(), getFont());
            }
        }
    }

    public Graph getGraph() {
        return graph;
    }

    /*##########POUR LA SELECTION###########*/
    public Noeud getSelectedNode() {
        return selectedNode;
    }

    public void setNbrSelectableNode(int n) {
        this.selectedNodes = new Noeud[n];
        this.currSelectedNode = 0;
    }

    public Noeud[] getSelectedNodes() {
        return selectedNodes;
    }

    public boolean isSelected(Noeud noeud) {
        for (Noeud node : this.selectedNodes) {
            if (noeud == node) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        if (toMove == null) {
            return;
        }
        Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
        toMove.setLastLocation(mousePos);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
