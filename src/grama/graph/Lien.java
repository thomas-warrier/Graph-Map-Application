package grama.graph;

import grama.exceptions.MauvaisTypeException;

import java.util.Objects;

public class Lien {

    public enum Type {
        AUTOROUTE('A'),
        NATIONAL('N'),
        DEPARTEMENTAL('D'),
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
            return super.toString().toLowerCase();
        }
    }

    private final int kilometrage;
    private final Noeud destination;
    private final Noeud depart;
    private Type typeLien; //A : autoroute ; N : national ; D : départemental

    public Lien(Type typeLien, int kilometrage, Noeud depart, Noeud destination) {
        this.typeLien = typeLien;
        this.kilometrage = kilometrage;
        this.destination = destination;
        this.depart = depart;
    }

    public Type getTypeLien() {
        return typeLien;
    }

    public void setTypeLien(Type typeLien) {
        if (typeLien != Type.NONE && typeLien != Type.ALL) {
            this.typeLien = typeLien;
        } else {
            throw new MauvaisTypeException();
        }

    }

    public int getKilometrage() {
        return kilometrage;
    }

    public Noeud getDstADepartDe(Noeud node) {//si plusieur le qqlq return
        if (node.equals(destination)) {
            return depart;
        } else {
            return destination;
        }
    }

    public Noeud[] getDstAndDepart() {
        Noeud[] both = {depart, destination};
        return both;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lien lien = (Lien) o;
        return typeLien == lien.typeLien && kilometrage == lien.kilometrage && (Objects.equals(destination, lien.destination)
                && Objects.equals(depart, lien.depart) || Objects.equals(destination, lien.depart) && Objects.equals(depart, lien.destination));
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeLien, kilometrage, destination, depart);
    }

    @Override
    public String toString() {
        return typeLien + "," + kilometrage + " = " + depart + "->" + destination;
    }
}
