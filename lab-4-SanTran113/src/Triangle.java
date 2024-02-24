import java.awt.*;
import java.util.Objects;

public class Triangle implements Shape{
    private Point a;
    private Point b;
    private Point c;
    private Color color;
    public Triangle(Point a, Point b, Point c, Color color) {
        this. a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Point getVertexA() {
        return a;
    }

    public Point getVertexB() {
        return b;
    }
    public Point getVertexC() {
        return c;
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
        double AToB = distance(this.a, this.b);
        double BToC = distance(this.b, this.c);
        double CToB = distance(this.c, this.a);
        double s = (AToB + BToC + CToB) / 2;

        return Math.sqrt(s * (s - AToB) * (s - BToC) * (s - CToB));
    }

    private double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    @Override
    public double getPerimeter() {
        double AToB = distance(this.a, this.b);
        double BToC = distance(this.b, this.c);
        double CToB = distance(this.c, this.a);
        double s = (AToB + BToC + CToB);
        return s;
    }

    @Override
    public void translate(Point p) {
        this.a.x += p.x;
        this.a.y += p.y;
        this.b.x += p.x;
        this.b.y += p.y;
        this.c.x += p.x;
        this.c.y += p.y;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Triangle t = (Triangle) o;
        return Objects.equals(this.a, t.a) && Objects.equals(this.color, t.color)
                && Objects.equals(this.b, t.b) && Objects.equals(this.c, t.c);
    }

}
