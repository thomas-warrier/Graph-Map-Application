package grama.calcule.vector;

import java.awt.Graphics;

/**
 * Permet de représenter des points ou des distances sur un plan 2D
 */
public class Vector2D {

    public double x;
    public double y;

    /**
     * créé un {@link Vector2D} avec x et y
     *
     * @param x
     * @param y
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * copie un autre {@link Vector2D}
     *
     * @param lastLocation l'objet à copié
     */
    public Vector2D(Vector2D lastLocation) {
        this.x = lastLocation.x;
        this.y = lastLocation.y;
    }

    /**
     * Ajoute 2 {@link Vector2D}
     *
     * @param other le {@link Vector2D} avec lequelle additionner
     * @return la somme des 2 {@link Vector2D}
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Soustrai 2 {@link Vector2D}
     *
     * @param other le {@link Vector2D} avec lequelle soustraire
     * @return la soustraction du {@link Vector2D} courrant par le {@link Vector2D} other
     */
    public Vector2D sub(Vector2D other) {
        return this.add(other.mul(-1.0));
    }

    /**
     * multiplie avec un scalaire
     *
     * @param a le scalaire par lequelle multiplié
     * @return la multiplication du {@link Vector2D} avec le scalaire
     */
    public Vector2D mul(double a) {
        return new Vector2D(this.x * a, this.y * a);
    }

    /**
     * diviser par un scalaire
     *
     * @param a le scalaire par lequelle diviser
     * @return la diviser du {@link Vector2D} par le scalaire
     */
    public Vector2D div(double a) {
        return new Vector2D(this.x / a, this.y / a);
    }

    /**
     *
     * @return La norm du {@link Vector2D}
     */
    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     *
     * @return Le {@link Vector2D} unitaire
     */
    public Vector2D unitaire() {
        return this.div(this.norm());
    }

    /**
     * l'angle de rotation 0 est un vecteur vers la "droite"
     *
     * @param r l'angle de rotation (en radian)
     * @return un nouveau vecteur qui à pour angle de rotation r
     */
    public Vector2D getVecWithOrientation(double r) {
        return new Vector2D(this.norm() * Math.cos(r), this.norm() * Math.sin(r));
    }

    /**
     *
     * @return l'orientation (en radian) par rapport au vecteur orienter vers la "droite"
     */
    public double getOrientation() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * 
     * @param r l'angle duquelle tourner(en radian)
     * @return un vecteur tourné de l'ange r
     */
    public Vector2D rotateOf(double r) {
        return this.getVecWithOrientation(r + this.getOrientation());
    }

    /**
     * déssiner un point à l'emplacement par rapport au point en Haut à Gauche du Panel
     * @param g 
     */
    public void draw(Graphics g) {
        g.fillOval((int) x - 5, (int) y - 5, 10, 10);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector2D other = (Vector2D) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        return Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
    }

}
