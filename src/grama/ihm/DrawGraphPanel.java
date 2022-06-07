package grama.ihm;

import grama.calcule.vector.Vector2D;
import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Panel pour dessiner un Graph
 *
 * 
 */
public class DrawGraphPanel extends JPanel implements MouseMotionListener {

    private final Graph graph;
    private Noeud.Type typeNoeud;
    private Lien.Type typeLien;

    private Noeud toMove;

    private List<Noeud> selectedNodes;
    private int currSelectedNode;

    private List<Drawable> highlited;

    private Dimension prevSizePanel;
    private Vector2D scaleOffset, offsetForLocation, lastMouseLocation;
    private int nbrSelectabelNodes;

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
        offsetForLocation = new Vector2D(0, 0);

        Dimension dimenstion = new Dimension(300, 300);
        this.setMaximumSize(dimenstion);
        setSize(dimenstion);
        setPreferredSize(dimenstion);

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
        scaleOffset = new Vector2D(1, 1);
        initNoeudsLocation();

        this.addMouseMotionListener(this);
        this.addMouseListener(new java.awt.event.MouseAdapter() {//pour la selection des noeuds

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    Noeud clicked = getNoeudAtPos(new Vector2D(evt.getX(), evt.getY()));
                    if (clicked != null) {
                        selectedNodes.set(currSelectedNode++ % nbrSelectabelNodes, clicked);
                    } else {
                        setNbrSelectableNode(nbrSelectabelNodes);
                    }
                    repaint();
                    parentFrame.update();
                }
            }

            @Override
            public void mousePressed(MouseEvent evt) {

                Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
                switch (evt.getButton()) {
                    case MouseEvent.BUTTON1:
                        Noeud clicked = getNoeudAtPos(mousePos);
                        if (clicked != null) {
                            toMove = clicked;
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                        break;
                    case MouseEvent.BUTTON2:
                        lastMouseLocation = mousePos;
                        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                switch (evt.getButton()) {
                    case MouseEvent.BUTTON1:
                        toMove = null;
                        break;
                    case MouseEvent.BUTTON2:
                        lastMouseLocation = null;
                        offsetForLocation = new Vector2D(0, 0);
                        break;
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                setScaleOffset(getScaleOffset().add(new Vector2D(0.05, 0.05).mul(e.getWheelRotation()).mul(-1)));
                System.out.println(getScaleOffset());
                repaint();
            }
        });

    }

    /**
     * 
     * @param pos l'emplacement où on veux chercher le noeud
     * @return le Noeud à l'emplacement pos si existant, sinon null
     */
    private Noeud getNoeudAtPos(Vector2D pos) {
        for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
            if (noeud.getLastLocation() == null) {
                continue;
            }
            if (noeud.getLastLocation().sub(pos).norm() <= Noeud.DIAMETRE / 2) {//on click Noeud
                return noeud;
            }
        }
        return null;
    }

    /**
     * 
     * @return le décalager d'échelle par rapport à la dèrnière actualisation
     */
    public Vector2D getScaleOffset() {
        return scaleOffset;
    }

    public Graph getGraph() {
        return graph;
    }

    /*##########POUR LA SCALE###########*/
    /**
     * met le scale offset à la taille désirer
     * @param s l'échelle
     */
    private void setScaleOffset(Vector2D s) {
        scaleOffset = s;
    }

    /**
     * 
     * @param prev les précédente dimmension du panel
     * @param nouveau les nouvelle dimension du panel
     * @return une échelle calculer en fonction de la taille de la fenêtre 
     */
    private Vector2D calculeScale(Dimension prev, Dimension nouveau) {
        double scaleX = nouveau.getWidth() / prev.getWidth();
        double scaleY = nouveau.getHeight() / prev.getHeight();
        Vector2D scale = new Vector2D(1, 1);
        if (!Double.isInfinite(scaleX)) {
            scale.x = scaleX;
        }
        if (!Double.isInfinite(scaleY)) {
            scale.y = scaleY;
        }
        return scale;
    }

    /*##########POUR L'AFFICHAGE###########*/
    /**
     * place les noeuds en cercle
     */
    public void initNoeudsLocation() {
        Vector2D center = new Vector2D(getWidth() / 2, getHeight() / 2);
        Vector2D rayon = new Vector2D(0, -1.0 * (Math.min(getWidth() / 2, getHeight() / 2) - Noeud.DIAMETRE / 2));//oriente vers le haut pour placer le 1er noeud
        double angleRot = (2 * Math.PI) / graph.getListNoeudOfType(typeNoeud).size();//anlge de rotation pour dessiner les neouds en cercle
        
        System.out.println("r : " + new Vector2D(1, 0).getVecWithOrientation(Math.PI/2));
        for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
//            System.out.println("rotation : " + rayon.getOrientation());
            //position du Noeud est au centre + le vec rayon (qui à la bonne direction en fonction de l'angle)
            noeud.setLastLocation(center.add(rayon).add(offsetForLocation));

            rayon = rayon.rotateOf(angleRot);//fait tourner le vec rayon
        }
    }

    /**
     * dessine le graphe au centre du panel (en cercle)
     *
     * @param g l'objet graphique
     */
    @Override
    protected void paintComponent(Graphics g) {
        //ANTIALIASING
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.clearRect(0, 0, getWidth(), getHeight());//clear les anciens dessins

        setScaleOffset(getScaleOffset().add(calculeScale(prevSizePanel, getSize())).sub(new Vector2D(1, 1)));
        prevSizePanel = getSize();

        //draw les noeuds et liens entre ces noeuds (uniquement)
        drawNoeuds(g);
        drawLien(g);

        setScaleOffset(new Vector2D(1, 1));

    }

    /**
     * dessine les liens en fonction des liens qu'on demande d'afficher
     * @param g l'objet graphique
     */
    public void drawLien(Graphics g) {
        for (Lien lien : graph.getListLienOfType(typeLien)) {

            Color color = null;
            if (highlited != null && highlited.contains(lien)) {
                color = Color.yellow;
            }
            lien.draw(g, null, getFont(), color);//affiche en fonction des position des neouds qu'il relie s'il on été affiché
        }
    }

    /**
     * dessine les Noeuds en fonction des Noeuds qu'on demande d'afficher
     * @param g l'objet graphique
     */
    public void drawNoeuds(Graphics g) {
        for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {

            noeud.getLastLocation().x *= scaleOffset.x;
            noeud.getLastLocation().y *= scaleOffset.y;
            noeud.setLastLocation(noeud.getLastLocation().add(offsetForLocation));

            Color color = null;
            if (isSelected(noeud)) {
                color = Color.yellow;
            } else if (highlited != null && highlited.contains(noeud)) {
                color = Color.cyan;
            }
            noeud.draw(g, noeud.getLastLocation(), getFont(), color);

            g.setColor(Color.BLACK);
        }
    }
    /**
     * Permet de surligner les élément présent dans la liste
     * @param highlited la nouvelle liste des objet {@link Drawable} à surligné
     */
    public void setHighlited(List<Drawable> highlited) {
        this.highlited = highlited;
    }

    /*##########POUR LA SELECTION###########*/
    /**
     * change le nombre maximal de noeuds séléctionner en même temps
     * @param n nombre maximal de noeuds séléctionnable
     */
    public void setNbrSelectableNode(int n) {
        this.selectedNodes = new ArrayList<>();
        this.nbrSelectabelNodes = n;
        for (int i = 0; i < nbrSelectabelNodes; i++) {
            selectedNodes.add(null);
        }
        setHighlited(null);
    }

    /**
     * 
     * @return La liste des Noeuds séléctionner
     */
    public List<Noeud> getSelectedNodes() {
        return selectedNodes;
    }

    /**
     * 
     * @param noeud le noeud dont on veux savoir s'il est séléctionné
     * @return true ssi le noeud est séléctionné
     */
    public boolean isSelected(Noeud noeud) {
        for (Noeud node : this.selectedNodes) {
            if (noeud == node) {
                return true;
            }
        }
        return false;
    }

    /**
     * permet de déplacer un neoud en par appuis, déplacer, relacher
     * @param evt le MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent evt) {
        Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
        if (toMove != null) {// on veux déplacer un noeud
            toMove.setLastLocation(mousePos);
            repaint();
        } else if (lastMouseLocation != null) {//on veux se déplacer sur le graph
            offsetForLocation = mousePos.sub(lastMouseLocation);
            lastMouseLocation = mousePos;
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
