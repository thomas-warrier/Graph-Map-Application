/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.graph;

import java.util.ArrayList;
import java.util.List;


public class Chemin {

    private List<Lien> chemin;

    public List<Lien> getChemin() {
        return chemin;
    }

    public void setChemin(List<Lien> chemin) {
        this.chemin = chemin;
    }
 /**
  * pour ajouter un Chemin entier
  * @param other le chemin à ajouter au chemin courant
  */
    public void append(Chemin other) {
        getChemin().addAll(other.getChemin());
    }
    /**
     * sert a retourner le Chemin inverse
     * @return Chemin
     */
    public Chemin reversed() {
        Chemin rev = new Chemin();
        for (int i = chemin.size() - 1; i >= 0; i--) {
            rev.addLienToChemin(chemin.get(i));
        }
        return rev;
    }

    public Chemin() {
        chemin = new ArrayList();
    }
    /**
     * prend en paramétre un lien et l'ajoute a la liste de lien qui est un attribut de Chemin 
     * @param lien le lien à ajouté au chemin
     */
    public void addLienToChemin(Lien lien) {
        chemin.add(lien);
    }
/**
 * on additione tout les kilométrages des liens pour avoir le kilométrage total d'un chemin (un Chemin est une liste de lien)
 * @return un int qui est le kilométrage total du chemin
 */
    public int getKilometrageChemin() {
        int kilometrageChemin = 0;
        for (Lien lien : getChemin()) {
            kilometrageChemin += lien.getKilometrage();
        }
        return kilometrageChemin;
    }

    @Override
    public String toString() {
        String str = "";
        for (Lien lien : chemin) {
            str += "(" + lien + "), ";
        }
        return str;
    }

}
