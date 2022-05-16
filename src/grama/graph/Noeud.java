package grama.graph;

import grama.calcule.vector.Vector2D;
import grama.exceptions.MauvaisTypeException;
import grama.formater.StringFormater;
import grama.ihm.Drawable;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Noeud implements Drawable {

    public static int DIAMETRE = 50;

    public enum Type {
        VILLE('V'),
        LOISIR('L'),
        RESTAURANT('R'),
        ALL('*'),
        NONE('\0');

        private final char representativeChar;

        Type(char c) {
            this.representativeChar = c;
        }

        public char getRepresentativeChar() {
            return representativeChar;
        }

        public boolean is(Type t) {
            return this == t || t == ALL || this == ALL;
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

    private final List<Lien> listLien;
    private Type typeLieu;  // V : ville , L : loisir , R : restaurant
    private final String nom;

    //affichage
    private Vector2D lastLocation;

    public Noeud(Type newTypeLieu, String newNom) {
        listLien = new LinkedList<>();
        this.typeLieu = newTypeLieu;
        this.nom = newNom;
    }

    public void setTypeLieu(Type typeLieu) {
        if (typeLieu != Type.NONE && typeLieu != Type.ALL) {
            this.typeLieu = typeLieu;
        } else {
            throw new MauvaisTypeException();
        }
    }

    public String getNom() {
        return nom;
    }
    
    

    public Type getTypeLieu() {
        return typeLieu;
    }

    public List<Lien> getListLien() {
        return listLien;
    }
    

    /**
     * Voisins à 1-saut (voisin direct) d'un certein type
     *
     * @param typeVoisins le type des voisins qui doivent être récuperer
     * @return Liste des voisins direct du type typeVoisins
     */
    public List<Noeud> getVoisinsOfType(Type typeVoisins) {
        List<Noeud> voisins = new ArrayList<>();
        for (Lien lien : getListLien()) {
            if (lien.getDstADepartDe(this).getTypeLieu() == typeVoisins) {
                voisins.add(lien.getDstADepartDe(this));
            }
        }
        return voisins;
    }

    public void addLien(Lien lien) {
        if (!listLien.contains(lien)) {
            listLien.add(lien);
            lien.getDstADepartDe(this).addLien(lien);//doit aussi être ajouté dans l'autre sense
        }
    }

    public Integer getDistanceTo(Noeud node) {
        for (Lien lien : listLien) {
            if (lien.getDstADepartDe(this).equals(node)) {
                return lien.getKilometrage();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Noeud noeud = (Noeud) o;
        return typeLieu == noeud.typeLieu && Objects.equals(nom, noeud.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeLieu, nom);
    }

    @Override
    public String toString() {
        return typeLieu + ":" + nom;
    }

    @Override
    public void draw(Graphics g, Vector2D center, Font font) {
        g.drawOval((int) center.x - (DIAMETRE / 2), (int) center.y - (DIAMETRE / 2), DIAMETRE, DIAMETRE);
        StringFormater.drawCenteredString(g, this.typeLieu.getRepresentativeChar() + ", " + this.nom.substring(0, 2), center, font);

        lastLocation = center;
    }

    public Vector2D getLastLocation() {
        return lastLocation;
    }
    
    
    public List<Noeud> getVoisin2Dist(Noeud noeudArr){
        
    }

}
