import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Point;

public class WorkSpace{
    private List<Shape> shapeList = new ArrayList<>();

    public WorkSpace() {

        this.shapeList = new ArrayList<>();
    }

    public void add(Shape shape) {
        this.shapeList.add(shape);
    }

    public Shape get(int index) {
            return this.shapeList.get(index);
    }

    public int size() {
        return this.shapeList.toArray().length;
    }

    public List<Circle> getCircles() {
        List<Circle> circleList = new ArrayList<>();

        for (int i = 0; i < this.shapeList.size(); i ++) {
            if (shapeList.get(i).getClass().equals(Circle.class)) {
                circleList.add((Circle) shapeList.get(i));
            }
        }
        return circleList;
    }

    public List<Rectangle> getRectangles() {
        List<Rectangle> rectangleList = new ArrayList<>();

        for (int i = 0; i < this.shapeList.size(); i ++) {
            if(shapeList.get(i).getClass().equals(Rectangle.class)) {
                rectangleList.add((Rectangle) shapeList.get(i));
            }
        }
        return rectangleList;
    }

    public List<Triangle> getTriangles() {
        List<Triangle> triangleList = new ArrayList<>();

        for (int i = 0; i < this.shapeList.size(); i ++) {
            if (shapeList.get(i).getClass().equals(Triangle.class)) {
                triangleList.add((Triangle) shapeList.get(i));
            }
        }
        return triangleList;
    }

    public List<Shape> getShapesByColor(Color color) {
        List<Shape> colorList = new ArrayList<>();

        for (Shape s : this.shapeList) {
            if (s.getColor().equals(color)) {
                colorList.add(s);
            }
        }
        return colorList;
    }

    public double getAreaOfAllShapes() {
        double sum = 0;
        for (int i = 0; i < this.shapeList.size(); i ++) {
            sum += this.shapeList.get(i).getArea();
        }
        return sum;
    }

    public double getPerimeterOfAllShapes() {
        double sum = 0;

        for (int i = 0; i < this.shapeList.size(); i ++) {
            sum += this.shapeList.get(i).getPerimeter();
        }
        return sum;
    }

}
