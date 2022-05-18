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
 * @author virgile
 */
public class Lien implements Drawable {

    public enum Type {
        AUTOROUTE('A'),
        NATIONAL('N'),
        DEPARTEMENTAL('D'),
        ALL('*'),
        NONE('\0');

        private final char representativeChar;

        Type(char c) {
            this.representativeChar = c;
        }

        public char getRepresentativeChar() {
            return representativeChar;
        }

        public boolean is(Type type) {
            return this == type || type == ALL || this == ALL;
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
     * @return la distance un km du lien
     */
    public int getKilometrage() {
        return kilometrage;
    }
    
    /**
     * 
     * @param node le (@link Noeud) de départ
     * @return Le (@link Noeud) d'arriver (en partant du noeud "node")
     */
    public Noeud getDstADepartDe(Noeud node) {//si plusieur le qqlq return
        if (node.equals(destination)) {
            return depart;
        } else {
            return destination;
        }
    }

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
    
    public Color whichColorLink(Lien lien){
        Type typeLien = lien.getTypeLien();
        if (typeLien.equals(Type.AUTOROUTE)){
            return Color.ORANGE;
        }
        if (typeLien.equals(Type.DEPARTEMENTAL)){
            return Color.MAGENTA;
        }
        if (typeLien.equals(Type.NATIONAL)){
            return Color.GRAY;
        }
        return Color.BLACK;
    }
  
    
    @Override
    public void draw(Graphics g, Vector2D center, Font font) {
        if (depart.getLastLocation() == null || destination.getLastLocation() == null) {
            return;
        }
        Vector2D debut = new Vector2D(depart.getLastLocation().x, depart.getLastLocation().y);
        Vector2D arriver = new Vector2D(destination.getLastLocation().x, destination.getLastLocation().y);
        Vector2D line = new Vector2D(destination.getLastLocation().x - depart.getLastLocation().x, destination.getLastLocation().y - depart.getLastLocation().y);
        Vector2D rayon = line.unitaire().mul((Noeud.DIAMETRE / 2.0));

        debut = debut.add(rayon);
        arriver = arriver.sub(rayon);
        g.setColor(whichColorLink(this));
        g.drawLine((int) debut.x, (int) debut.y, (int) arriver.x, (int) arriver.y);

        center = debut.add(line.div(4));
        StringFormater.drawCenteredString(g, this.typeLien.getRepresentativeChar() + ", " + this.getKilometrage(), center, font);
         g.setColor(Color.BLACK);
    }
}
