package grama.graph;

import grama.exceptions.MauvaisType;

public class Lien {
    private char typeLien; //A : autoroute ; N : national ; D : départemental
    private final int kilometrage;

    private final Noeud destination;

    public Lien(char typeLien, int kilometrage, Noeud destination) {
        this.typeLien = typeLien;
        this.kilometrage = kilometrage;
        this.destination = destination;
    }

    public void setTypeLien(char typeLien) {
        if(typeLien == 'A' || typeLien == 'N' || typeLien == 'D'){
            this.typeLien = typeLien;
        }
        else
        {
            throw new MauvaisType();
        }

    }

    public char getTypeLien() {
        return typeLien;
    }

    public int getKilometrage() {
        return kilometrage;
    }
}
