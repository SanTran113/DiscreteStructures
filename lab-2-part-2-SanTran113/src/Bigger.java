public class Bigger {
    public static double circle;
    public static double rectangle;
    public static double polygon;
    public static double whichIsBigger(Circle c, Rectangle r, Polygon p) {
//        double max = 0;
        circle = c.perimeter();
        rectangle = r.perimeter();
        polygon = p.perimeter();

        double max = circle;
        if (rectangle > max) {
           max = rectangle;
        }
        if (polygon > max) {
            max = polygon;
        }
        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(polygon);
        return max;

    }
}
