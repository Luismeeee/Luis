package bases;

public class BoxCollider {
    public Vector2D position;
    public int width;
    public int height;
    public Vector2D anchor;

    public BoxCollider() {
        this(1, 1, 1, 1);
    }

    public BoxCollider(double x, double y, int width, int height) {
        position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        anchor = new Vector2D(0.5, 0.5);
    }

    public BoxCollider(GameObject object, int width, int height) {
        position = object.position;
        this.width = width;
        this.height = height;
        this.anchor = object.anchor;
    }

    public double top() {
        return position.y - anchor.y * height;
    }

    public double bot() {
        return top() + height;
    }

    public double left() {
        return position.x - anchor.x * width;
    }

    public double right() {
        return left() + width;
    }

    public boolean intersects(BoxCollider other) {
        return this.right() >= other.left()
                && this.left() <= other.right()
                && this.top() <= other.bot()
                && this.bot() >= other.top();
    }
}
