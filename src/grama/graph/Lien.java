package grama.graph;

import grama.calcule.vector.Vector2D;
import grama.exceptions.MauvaisTypeException;
import grama.formater.StringFormater;
import grama.ihm.Drawable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.util.Objects;

/**
 * Classe representant un lien d'un graph
 *
 *
 */
public class Lien implements Drawable {

    /**
     * cette énumération contient les différents type,contient leur noms ainsi que la couleur qui leur est associée
     */
    public enum Type {
        AUTOROUTE('A', new Color(0, 140, 100), 4),
        NATIONALE('N', new Color(23, 42, 58), 2),
        DEPARTEMENTALE('D', new Color(78, 133, 141), 1),
        AUTOROUTENATIONALE('B', Color.BLACK, 6),
        AUTOROUTEDEPARTEMENTALE('C', Color.BLACK, 5),
        DEPARTEMENTALENATIONALE('D', Color.BLACK, 3),
        ALL('*', Color.BLACK, 7),
        NONE('\0', Color.black, 0);

        private final char representativeChar;
        private final Color colorLien;
        private final int representativeByte;

        private Type(char representativeChar, Color colorLien, int representativeByte) {
            this.representativeChar = representativeChar;
            this.colorLien = colorLien;
            this.representativeByte = representativeByte;
        }

        public char getRepresentativeChar() {
            return representativeChar;
        }

        public Color getColorLien() {
            return colorLien;
        }

        /**
         * cette méthode permet de vérifier si un lien est du type passé en paramétre
         *
         * @param t le type qui doit englober le type du noeud courrant
         * @return boolean,true si il est du type souhaité,false sinon
         */
        public boolean estDeType(Type t) {
            return t != null && ((representativeByte & t.representativeByte) != 0 || representativeByte == 0 && t.representativeByte == 0);
        }

        public Type or(Type t) {
            return getOfType(representativeByte | t.representativeByte);
        }

        public Type getOfType(int b) {
            for (Type t : Type.values()) {
                if (t.representativeByte == b) {
                    return t;
                }
            }
            return NONE;
        }

        public static Type getType(char c) {
            for (Type t : Type.values()) {
                if (t.representativeChar == c) {
                    return t;
                }
            }
            return Type.NONE;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private final int kilometrage;
    private final Noeud destination;
    private final Noeud depart;
    private Type typeLien; //A : autoroute ; N : national ; D : départemental

    public Lien(Type typeLien, int kilometrage, Noeud depart, Noeud destination) {
        this.typeLien = typeLien;
        this.kilometrage = kilometrage;
        this.destination = destination;
        this.depart = depart;
    }

    /**
     *
     * @return le type du lien
     */
    public Type getTypeLien() {
        return typeLien;
    }

    /**
     * pour changer le type d'un lien
     *
     * @param typeLien le nouveau type de lien
     */
    public void setTypeLien(Type typeLien) {
        if (typeLien != Type.NONE && typeLien != Type.ALL) {
            this.typeLien = typeLien;
        } else {
            throw new MauvaisTypeException();
        }

    }

    /**
     *
     * @return la distance en km du lien
     */
    public int getKilometrage() {
        return kilometrage;
    }

    /**
     * @param node le {@link Noeud} de départ
     * @return Le {@link Noeud} d'arriver (en partant du noeud "node")
     */
    public Noeud getDstADepartDe(Noeud node) {
        if (node.equals(destination)) {
            return depart;
        } else {
            return destination;
        }
    }

    /**
     *
     * @return un tableau de noeuds contenant le noeud de depart et d'arriver
     */
    public Noeud[] getDstAndDepart() {
        Noeud[] both = {depart, destination};
        return both;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lien lien = (Lien) o;
        return typeLien == lien.typeLien && kilometrage == lien.kilometrage && (Objects.equals(destination, lien.destination)
                && Objects.equals(depart, lien.depart) || Objects.equals(destination, lien.depart) && Objects.equals(depart, lien.destination));
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeLien, kilometrage, destination, depart);
    }

    @Override
    public String toString() {
        return typeLien + "," + kilometrage + " = " + depart + "->" + destination;
    }

    @Override
    public void draw(Graphics g, Vector2D centre, Font font, Color highlight) {
        if (depart.getLastLocation() == null || destination.getLastLocation() == null) {
            return;
        }

        Vector2D debut = new Vector2D(depart.getLastLocation().x, depart.getLastLocation().y);
        Vector2D arriver = new Vector2D(destination.getLastLocation().x, destination.getLastLocation().y);
        Vector2D line = new Vector2D(destination.getLastLocation().x - depart.getLastLocation().x, destination.getLastLocation().y - depart.getLastLocation().y);
        Vector2D rayon = line.unitaire().mul((Noeud.DIAMETRE / 2.0));

        debut = debut.add(rayon);
        arriver = arriver.sub(rayon);
        if (highlight != null) {
            g.setColor(highlight);
        } else {
            g.setColor(getTypeLien().getColorLien());
        }
        g.drawLine((int) debut.x, (int) debut.y, (int) arriver.x, (int) arriver.y);

        centre = debut.add(line.div(4));
        g.setColor(Color.BLACK);
        StringFormater.drawCenteredString(g, this.typeLien.getRepresentativeChar() + ", " + this.getKilometrage(), centre, font);
        g.setColor(Color.BLACK);
    }
}
