/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.calcule.matrix;


/**
 *
 * @author twarr
 */
import grama.graph.Chemin;
import grama.graph.Graph;


public class MatriceChemin extends Matrix<Chemin>{
    
    public MatriceChemin(Chemin[][] array) {
        super(array);
    }
    
    public MatriceChemin(Graph graph){
        super(graph.getListNoeud().size(),null);
    }

    @Override
    public void init(int length, Chemin defaut) {
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    
}
