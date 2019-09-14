package bases;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void scale(double rate) {
        this.x *= rate;
        this.y *= rate;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        this.set(other.x, other.y);
    }

    public Vector2D clone() {
//        Vector2D newVector = new Vector2D(this.x, this.y);
//        return newVector;
        return new Vector2D(this.x, this.y);
    }

    public double getlength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public void setlength(double length) {
        double currentlength = this.getlength();
        if (currentlength != 0 && length >= 0) {
            this.x = this.x * length / currentlength;
            this.y = this.y * length / currentlength;
        }
    }

    public double distanceTo(Vector2D other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getAngle() {
        return Math.atan(this.y / this.x);
    }

    public void setAngle(double angle) {
        double currentLength = this.getlength();
        if (currentLength != 0) {
            this.x = currentLength * Math.cos(angle);
            this.y = currentLength * Math.sin(angle);
        }
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(1,1);
    }
}
