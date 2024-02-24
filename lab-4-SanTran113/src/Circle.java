import java.awt.Color;
import java.awt.Point;
import java.util.Objects;

public class Circle implements Shape{
    private double radius;
    private Point center;
    private Color color;

    public Circle(double radius, Point center, Color color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    public double getRadius() {

        return radius;
    }

    public void setRadius(double newRadius) {
        this.radius = newRadius;
    }

    public Point getCenter() {

        return center;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2); // not right (implement A=πr2)
    }

    @Override
    public double getPerimeter() {
        return Math.PI * 2 * this.radius; // not right (implement C=2πr)
    }

    @Override
    public void translate(Point p) {
        this.center.x += p.x;
        this.center.y += p.y;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Circle c = (Circle) o;
        return Objects.equals(this.radius, c.radius) && Objects.equals(this.center, c.center)
                && Objects.equals(this.color, c.color);
    }



}
