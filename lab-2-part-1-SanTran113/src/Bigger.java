public class Bigger {
    public static double circle;
    public static double rectangle;
    public static double polygon;
    public static double whichIsBigger(Circle c, Rectangle r, Polygon p) {
//        double max = 0;
        circle = Util.perimeter(c);
        rectangle = Util.perimeter(r);
        polygon = Util.perimeter(p);

        double max = circle;
        if (rectangle > max) {
           max = rectangle;
        }
        if (polygon > max) {
            max = polygon;
        }
        return max;

    }
}
