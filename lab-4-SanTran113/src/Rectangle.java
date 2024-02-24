import java.awt.*;
import java.util.Objects;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private Point topLeft;
    private Color color;

    public Rectangle(double width, double height, Point topLeft, Color color) {
        this.width = width;
        this.height = height;
        this.topLeft = topLeft;
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    // can i enter a height even when the doc didnt say that?
    public void setHeight(double height) {
        this.height = height;
    }

    public Point getTopLeft() {
        return topLeft;
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
        return this.width * this.height;
    }

    @Override
    public double getPerimeter() {
        return (2 * this.height) + (2 * this.width);
    }

    @Override
    public void translate(Point p) {
        this.topLeft.x += p.x;
        this.topLeft.y += p.y;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Rectangle r = (Rectangle) o;
        return Objects.equals(this.width, r.width) && Objects.equals(this.color, r.color)
                && Objects.equals(this.height, r.height) && Objects.equals(this.topLeft, r.topLeft);
    }
}

