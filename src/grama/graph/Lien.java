package grama.graph;

import java.util.Objects;

public class Lien {
    private final int kilometrage;
    private final Noeud destination;
    private final Noeud depart;
    private char typeLien; //A : autoroute ; N : national ; D : départemental

    public Lien(char typeLien, int kilometrage, Noeud depart, Noeud destination) {
        this.typeLien = typeLien;
        this.kilometrage = kilometrage;
        this.destination = destination;
        this.depart = depart;
    }

    public char getTypeLien() {
        return typeLien;
    }

    public void setTypeLien(char typeLien) {
        if (typeLien == 'A' || typeLien == 'N' || typeLien == 'D') this.typeLien = typeLien;
    }

    public Noeud getDestination() {
        return destination;
    }

    public Noeud getDepart() {
        return depart;
    }

    public int getKilometrage() {
        return kilometrage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lien lien = (Lien) o;
        return typeLien == lien.typeLien && kilometrage == lien.kilometrage && (Objects.equals(destination, lien.destination) && Objects.equals(depart, lien.depart) || Objects.equals(destination, lien.depart) && Objects.equals(depart, lien.destination));
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeLien, kilometrage, destination, depart);
    }

    @Override
    public String toString() {
        return typeLien + ", " + kilometrage + " to " + destination;
    }
}
