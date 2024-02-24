public class Rectangle {
    private Point topLeft;
    private Point bottomRight;
    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    public Point getTopLeft() {
        return topLeft;
    }
    public Point getBottomRight() {
        return bottomRight;
    }
    public double perimeter() {
        double w = getBottomRight().getX() - getTopLeft().getX();
        double l = getTopLeft().getY() - getBottomRight().getY();
//        System.out.println(w);
//        System.out.println(l);
        return (2 * l) + (2 * w);
    }
}
