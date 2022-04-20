package grama.graph;

import grama.exceptions.MauvaisTypeException;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Noeud {
    
    public enum Type {
        VILLE('V'),
        LOISIR('L'),
        RESTAURANT('R'),
        ALL('+'),
        NONE('-');
        
        private final char representativeChar;
        
        Type(char c) {
            this.representativeChar = c;
        }
        
        public char getRepresentativeChar() {
            return representativeChar;
        }
        
        public boolean isType(Type t) {
            return this == t || t == Type.ALL;
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
            return Character.toString(representativeChar);
        }
        
    }
    
    private final List<Lien> listLien;
    private Type typeLieu;  // V : ville , L : loisir , R : restaurant
    private final String nom;
    
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
}
