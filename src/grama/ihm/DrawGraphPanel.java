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
import javax.swing.JPanel;

/**
 *
 * @author wwwazz
 */
public class DrawGraphPanel extends JPanel {

    private final Graph graph;
    private Noeud.Type typeNoeud;
    private Lien.Type typeLien;

    private Noeud selectedNode;

    public DrawGraphPanel(Graph graph, Font font) {
        this.graph = graph;
        typeNoeud = Noeud.Type.ALL;
        typeLien = Lien.Type.ALL;

        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(200, 200));

        setFont(font);

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("clicked");
                Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
                for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
                    if (noeud.getLastLocation() == null) {
                        continue;
                    }
                    if (noeud.getLastLocation().sub(mousePos).norm() <= Noeud.DIAMETRE / 2) {//on click Noeud
                        System.out.println(noeud);
                        selectedNode = noeud;
                    }
                }
                repaint();
            }
        });
    }

    public DrawGraphPanel(Graph graph, Font font, Noeud.Type typeNoeud, Lien.Type typeLien) {
        this.graph = graph;
        this.typeNoeud = typeNoeud;
        this.typeLien = typeLien;

        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(200, 200));

        setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //ANTIALIASING
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.clearRect(0, 0, getWidth(), getHeight());//clear les anciens dessins
        //draw les noeuds et liens entre ces noeuds
        double angleRot = (2 * Math.PI) / graph.getListNoeud().size();

        Vector2D center = new Vector2D(getWidth() / 2, getHeight() / 2);
        Vector2D rayon = new Vector2D(0, -1.0 * (Math.min(getWidth() / 2, getHeight() / 2) - Noeud.DIAMETRE / 2));//oriente vers le haut pour placer le 1er noeud

        for (Noeud noeud : graph.getListNoeudOfType(typeNoeud)) {
            Vector2D pos = center.add(rayon);

            if (noeud == selectedNode) {
                g.setColor(Color.yellow);
                noeud.draw(g, pos, getFont());
                g.setColor(Color.BLACK);
            } else {
                noeud.draw(g, pos, getFont());
            }

            rayon = rayon.rotateOf(angleRot);
        }
        for (Lien lien : graph.getListLienOfType(typeLien)) {
            lien.draw(g, null, getFont());
        }
    }

}
