package grama.calcule.vector;

import java.awt.Graphics;

/**
 *
 * @author wwwazz
 */
public class Vector2D {

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D sub(Vector2D other) {
        return this.add(other.mul(-1.0));
    }

    public Vector2D mul(double a) {
        return new Vector2D(this.x * a, this.y * a);
    }

    public Vector2D div(double a) {
        return new Vector2D(this.x / a, this.y / a);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D unitaire() {
        return this.div(this.norm());
    }

    public Vector2D abs() {
        return new Vector2D(Math.abs(this.x), Math.abs(this.y));
    }
    
    public void draw(Graphics g){
        g.fillOval((int)x-5, (int)y-5, 10, 10);
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
