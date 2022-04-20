package grama.ihm;

import grama.graph.Graph;
import grama.graph.Lien;
import grama.graph.Noeud;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author wwwazz
 */
public class DrawGraphPanel extends JPanel {

    private final Graph graph;

    public DrawGraphPanel(Graph graph, Font font) {
        this.graph = graph;
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(200, 200));

        setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        double angleRot = (2 * Math.PI) / graph.getListNoeud().size();
        int rayon = Math.min(getWidth() / 2, getHeight() / 2) - Noeud.DIAMETRE / 2;

        Point center = new Point(getWidth() / 2, getHeight() / 2);

        for (int i = 0; i < graph.getListNoeud().size(); i++) {

            double x = center.x + Math.sin(angleRot * i) * rayon;
            double y = center.y - Math.cos(angleRot * i) * rayon;
            graph.getListNoeud().get(i).draw(g, (int) x, (int) y, getFont());
        }

        for (Lien lien : graph.getListLienOfType(Lien.Type.ALL)) {
            lien.draw(g, getFont());
        }

    }

}
