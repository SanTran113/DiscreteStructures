import java.util.List;

public class Polygon {
    private List<Point> points;
    public Polygon(List<Point> points) {
        this.points = points;
    }
    public List<Point> getPoints() {
        return points;
    }
//    public double perimeter() {
//        int count = 0;
//        double total = 0;
//        int i = 0;
//        int i2 = 1;
//        while (count != getPoints().size()) {
//            double x2 = getPoints().get(i2).getX();
//            double x1 = getPoints().get(i).getX();
//            double x = Math.pow((x2 - x1), 2);
//            double y2 = getPoints().get(i2).getY();
//            double y1 = getPoints().get(i).getY();
//            double y = Math.pow((y2 - y1), 2);
//            total += Math.sqrt(x + y);
//            count += 1;
//            i += 1;
//            i2 += 1;
//
//            if (i2 > (getPoints().size() - 1)) {
//                i2 = 0;
//            }
//
//        }
//        return total;
//    }
public double perimeter() {
    double distance = 0;
    double total = 0;
    int count = 0;
    int t = 0;
    int t2 = 1;

        while (count != getPoints().size()) {



        double x1 = getPoints().get(t).getX();
        double x2 = getPoints().get(t2).getX();

        double y1 = getPoints().get(t).getY();
        double y2 = getPoints().get(t2).getY();

        // distance calculations
        double bigX = (x2 - x1);
        double bigY = (y2 - y1);
        double bigBigX = Math.pow(bigX,2);
        double bigBigy = Math.pow(bigY,2);
        double XY = (bigBigX + bigBigy);
        distance = Math.sqrt(XY);
        total =total + distance ;
            t = t + 1;
            t2 = t2 + 1;
        count += 1;
        if (t2 > (getPoints().size() - 1)) {
                t2 = 0;
            }

    }

    return total;
}

}
