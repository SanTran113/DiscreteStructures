import java.awt.Color;
import java.awt.Point;

public interface Shape {
//    Color getColor() - Returns the java.awt.Color of the Shape.
    Color getColor();


//    void setColor(Color c) - Sets the java.awt.Color of the Shape.
    void setColor(Color c);


//    double getArea() - Returns the area of the Shape
    double getArea();


//    double getPerimeter() - Returns the perimeter of the Shape
    double getPerimeter();


//    void translate(Point p) - Translates the entire shape by the (x,y) coordinates of a given java.awt.Point
    void translate(Point p);
}
