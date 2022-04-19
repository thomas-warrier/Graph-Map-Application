package grama.calcule.num;

import java.util.Objects;
import grama.calcule.models.SimpleOperator;

/**
 *
 * @author wwwazz
 */
public class Num extends Number implements SimpleOperator<Num> {

    private Integer num;

    public Num() {
        this.num = 0;
    }

    public Num(int num) {
        this.num = num;
    }

    @Override
    public int intValue() {
        return num;
    }

    @Override
    public long longValue() {
        return num.longValue();
    }

    @Override
    public float floatValue() {
        return num.floatValue();
    }

    @Override
    public double doubleValue() {
        return num.doubleValue();
    }

    @Override
    public Num add(Num other) {
        return new Num(num + other.num);
    }
    @Override
    public Num sub(Num other) {
        return new Num(num - other.num);
    }

    @Override
    public Num mul(Num other) {
        return new Num(num * other.num);
    }

    @Override
    public Num div(Num other) {
        return new Num(num / other.num);
    }

    @Override
    public String toString() {
        return num.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.num);
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
        final Num other = (Num) obj;
        return Objects.equals(this.num, other.num);
    }
}
