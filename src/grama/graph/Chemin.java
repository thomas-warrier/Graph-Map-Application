/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author twarr
 */
public class Chemin {

    private List<Lien> chemin;

    public List<Lien> getChemin() {
        return chemin;
    }

    public void setChemin(List<Lien> chemin) {
        this.chemin = chemin;
    }

    public Chemin() {
        chemin = new ArrayList();
    }
    
    public void addLienToChemin(Lien lien){
        chemin.add(lien);
    }
    
    public int getKilometrageChemin(Chemin chemin){
        int kilometrageChemin = 0;
        for (Lien lien : getChemin()){
           kilometrageChemin+= lien.getKilometrage();
        }
        return kilometrageChemin;
    }
    
    

}
