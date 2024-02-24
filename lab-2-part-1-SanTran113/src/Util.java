
public class Util {
    public static double perimeter(Circle c) {
        return 2 * Math.PI * c.getRadius();
    }
    public static double perimeter(Polygon p) {
        int count = 0;
        double total = 0;
        int i = 0;
        int i2 = 1;
//        System.out.println(p.getPoints().get(-1));
        while (count != p.getPoints().size()) {
//            int i = 0;
//            for (int i = 0; i < p.getPoints().size(); i++) { //making sure it only runs for the length of the list
//            if (i2 == (p.getPoints().size() - 1)) {
//                i2 = 0;
//            }

            double x2 = p.getPoints().get(i2).getX();
            double x1 = p.getPoints().get(i).getX();
            double x = Math.pow((x2 - x1), 2);
            double y2 = p.getPoints().get(i2).getY();
            double y1 = p.getPoints().get(i).getY();
            double y = Math.pow((y2 - y1), 2);
//            System.out.println(x);
//            System.out.println(y);
//            System.out.println(x2);
//            System.out.println(x1);
//            System.out.println(y2);
//            System.out.println(y1);
//            System.out.println(i2);
//            System.out.println(i);
//            System.out.println(Math.pow(x2, 2) + Math.pow(y2, 2));
            total += Math.sqrt(x + y);
            count += 1;
            i += 1;
            i2 += 1;

            if (i2 > (p.getPoints().size() - 1)) {
                i2 = 0;
            }

            }

        return total;
    }
    public static double perimeter(Rectangle r) {
        double w = r.getBottomRight().getX() - r.getTopLeft().getX();
        double l = r.getTopLeft().getY() - r.getBottomRight().getY();
        System.out.println(w);
        System.out.println(l);
        return (2 * l) + (2 * w);
    }

}