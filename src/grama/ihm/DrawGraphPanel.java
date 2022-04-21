package grama.ihm;

import grama.calcule.vector.Vector2D;
import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

    public DrawGraphPanel(Graph graph, Font font) {
        this.graph = graph;
        typeNoeud = Noeud.Type.ALL;
        typeLien = Lien.Type.ALL;

        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(200, 200));

        setFont(font);
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
        //lisage
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        //draw les noeuds et liens entre ces noeuds
        double angleRot = (2 * Math.PI) / graph.getListNoeud().size();
        int rayon = Math.min(getWidth() / 2, getHeight() / 2) - Noeud.DIAMETRE / 2;

        Vector2D center = new Vector2D(getWidth() / 2, getHeight() / 2);

        for (int i = 0; i < graph.getListNoeudOfType(typeNoeud).size(); i++) {

            double x = center.x + Math.sin(angleRot * i) * rayon;
            double y = center.y - Math.cos(angleRot * i) * rayon;
            graph.getListNoeudOfType(typeNoeud).get(i).draw(g, new Vector2D(x, y), getFont());
        }
        for (Lien lien : graph.getListLienOfType(typeLien)) {
            lien.draw(g, new Vector2D(0, 0), getFont());
        }

    }

}
