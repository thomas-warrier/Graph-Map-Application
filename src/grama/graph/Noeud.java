package grama.graph;

import grama.exceptions.MauvaisTypeException;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Noeud {

    private final List<Lien> listLien;
    private char typeLieu;  // V : ville , L : loisir , R : restaurant
    private final String nom;

    public Noeud(char newTypeLieu, String newNom) {
        listLien = new LinkedList<>();
        this.typeLieu = newTypeLieu;
        this.nom = newNom;
    }

    public void setTypeLieu(char typeLieu) {
        if (typeLieu == 'V' || typeLieu == 'L' || typeLieu == 'R') {
            this.typeLieu = typeLieu;
        } else {
            throw new MauvaisTypeException();
        }
    }

    public char getTypeLieu() {
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
    public List<Noeud> getVoisinsOfType(char typeVoisins) {
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
}
