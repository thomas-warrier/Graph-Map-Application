/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.exceptions;

import grama.graph.Noeud;

/**
 *
 * @author wwwazz
 */
public class CheminImpossibleErreur extends Exception {

    private final Noeud depart, arriver;
    private final Noeud.Type typeNoeud;
    private final int nombreDeCeType;

    public CheminImpossibleErreur(Noeud depart, Noeud arriver, Noeud.Type typeNoeud, int nombreDeCeType, String message) {
        super(message);
        this.depart = depart;
        this.arriver = arriver;
        this.typeNoeud = typeNoeud;
        this.nombreDeCeType = nombreDeCeType;
    }

    public CheminImpossibleErreur(Noeud depart, Noeud arriver, Noeud.Type typeNoeud) {
        this(depart, arriver, typeNoeud, 1, "Impossible d'allez de " + depart + " à " + arriver + " en passant par un lieu de type " + typeNoeud);
    }

    public CheminImpossibleErreur(Noeud depart, Noeud arriver, Noeud.Type typeNoeud, int nombreDeCeType) {
        this(depart, arriver, typeNoeud, 1, "Impossible d'allez de " + depart + " à " + arriver + " en passant par " + nombreDeCeType + " lieu de type " + typeNoeud);
    }
}
