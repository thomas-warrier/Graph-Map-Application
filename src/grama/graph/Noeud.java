package grama.graph;

import grama.calcule.matrix.FloydWarshall;
import grama.calcule.matrix.FloydWarshall.Couple;
import grama.calcule.vector.Vector2D;
import grama.exceptions.MauvaisTypeException;
import grama.formater.StringFormater;
import grama.ihm.Drawable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Classe representant un sommet (Noeud) du graphe
 *
 * @author virgile
 */
public class Noeud implements Drawable {

    public static int DIAMETRE = 50;

    public enum Type {
        VILLE('V', new Color(204, 41, 54), 4),
        LOISIR('L', new Color(8, 65, 92), 2),
        RESTAURANT('R', new Color(56, 134, 151), 1),
        ALL('*', Color.BLACK, 7),
        VILLELOISIR('A', Color.BLACK, 6),
        VILLERESTAURANT('B', Color.BLACK, 5),
        LOISIRRESTAURANT('C', Color.BLACK, 3),
        NONE('\0', Color.white, 0);

        private final char representativeChar;
        private final Color colorNode;
        private int representativeByte;

        Type(char c, Color color, int representativeByte) {
            this.representativeChar = c;
            this.colorNode = color;
            this.representativeByte = representativeByte;
        }

        public Type getOfType(int b) {
            for (Type t : Type.values()) {
                if (t.representativeByte == b) {
                    return t;
                }
            }
            return NONE;
        }

        public char getRepresentativeChar() {
            return representativeChar;
        }

        public Color getColorNode() {
            return colorNode;
        }

        public boolean estDeType(Type t) {
            return t != null && ((representativeByte & t.representativeByte) != 0 || representativeByte == 0 && t.representativeByte == 0);
        }

        public Type or(Type t) {
            return getOfType(representativeByte | t.representativeByte);
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
        this.setTypeLieu(newTypeLieu);
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
     * Voisins à 1-saut (voisin direct) d'un certains type
     *
     * @param typeVoisins le type des voisins qui doivent être récuperé
     * @return Liste des voisins direct du type typeVoisins
     */
    public List<Noeud> getVoisinsOfType(Type typeVoisins) {
        List<Noeud> voisins = new ArrayList<>();
        for (Lien lien : getListLien()) {
            if (lien.getDstADepartDe(this).getTypeLieu().estDeType(typeVoisins)) {
                voisins.add(lien.getDstADepartDe(this));
            }
        }
        return voisins;
    }

    /**
     * méthode qui permet d'ajouter un lien a notre liste de lien
     *
     * @param lien le lien que l'ont souhaite ajouter
     *
     */
    public void addLien(Lien lien) {
        if (!listLien.contains(lien)) {
            listLien.add(lien);
            lien.getDstADepartDe(this).addLien(lien);//doit aussi être ajouté dans l'autre sens
        }
    }

    /**
     * cette méthode sert a vérifier si il s'agit d'un voisin direct et si c'est le cas d'obtenir sa distance en kilométre
     *
     * @param node doit être un voisin direct
     * @return un kiométrage ou null si ce n'est pas un voisin direct
     */
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

    /**
     *
     * @param g un objet graphic
     * @param center l'emplacement où centrer le noeud
     * @param font la police d'écriture
     * @param highlight la couleur de surlignage s'il y'en à pas alors mettre null
     */
    @Override
    public void draw(Graphics g, Vector2D center, Font font, Color highlight) {

        if (highlight != null) {
            g.setColor(highlight);
        } else {
            g.setColor(getTypeLieu().getColorNode());
        }
        g.fillOval((int) center.x - (DIAMETRE / 2), (int) center.y - (DIAMETRE / 2), DIAMETRE, DIAMETRE);
        if (highlight != null) {
            g.setColor(Color.BLACK);
            g.drawOval((int) center.x - (DIAMETRE / 2), (int) center.y - (DIAMETRE / 2), DIAMETRE, DIAMETRE);
        }

        if (highlight != null) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }

        StringFormater.drawCenteredString(g, this.typeLieu.getRepresentativeChar() + ", " + this.nom.substring(0, 2), center, font);

        lastLocation = center;
        g.setColor(Color.black);
    }

    public Vector2D getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Vector2D lastLocation) {
        this.lastLocation = lastLocation;
    }

    /**
     * cette méthode retourne une liste de noeuds contenant tout les voisins a deux distance
     *
     * @param graph le graph dans lequel est le noeud courrant
     * @param floydMatrice la matrice floydWarshall qui contient les distances en saut
     * @param typeNoeud dans le cas ou on veut uniquement les voisins d'un certains type
     * @return une liste de noeud contenant tout les voisins a deux distance
     */
    public List<Noeud> getVoisin2Dist(Graph graph, Type typeNoeud) {

        List<Noeud> noeuds = new LinkedList();
        noeuds.addAll(getVoisinsOfType(typeNoeud));
        for (Noeud noeud : getVoisinsOfType(Type.ALL)) {
            for (Noeud secondNoeud : noeud.getVoisinsOfType(typeNoeud)) {
                if (secondNoeud.getTypeLieu().estDeType(typeNoeud) && !secondNoeud.equals(this)
                        && !noeuds.contains(secondNoeud)) {
                    noeuds.add(secondNoeud);
                }
            }

        }
        return noeuds;
    }

    /**
     * on compare deux noeuds et on regarde lequel a le plus de voisins a deux distances d'un certains type.
     *
     * @param noeudA le premier noeud à comparer
     * @param noeudB le second noeud à comparer
     * @param graph le graph dans lequel ils sont tous les 2
     * @param floydMatrice la matrice floydWarshall des sauts
     * @param typeNoeud les types de noeud que l'on veux comparer (gastronomique, etc...)
     * @return un int,si le int est positif c'est le noeud A qui est le plus ouvert et si le int est négatif,c'est le noeudB qui est le plus ouvert.
     */
    public static int compareOpeningTo(Noeud noeudA, Noeud noeudB, Graph graph, FloydWarshall floydMatrice, Type typeNoeud) {

        int nb2DistA = noeudA.getVoisin2Dist(graph, typeNoeud).size();
        int nb2DistB = noeudB.getVoisin2Dist(graph, typeNoeud).size();

        return nb2DistA - nb2DistB;
    }

    /**
     * cette méthode récupère un lien entre l'objet courant et noeudArr
     *
     * @param noeudArr le noeud d'arriver du lien cherché
     * @return le lien entre le noeud courrant et le noeudArr
     */
    public Lien getLinkBetween(Noeud noeudArr) {
        for (Lien lien : this.getListLien()) {
            if (lien.getDstADepartDe(this).equals(noeudArr)) {
                return lien;
            }
        }
        return null;
    }

}
