public class Circle {
    private double radius;
    private Point center;
    public Circle (Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    public Point getCenter() {
        return center;
    }
    public double getRadius() {
        return radius;
    }
}
