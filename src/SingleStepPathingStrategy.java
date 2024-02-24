import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SingleStepPathingStrategy
   implements PathingStrategy
{

   WorldModel world;

   public List<Point> computePath(Point start, Point end,
      Predicate<Point> canPassThrough,
      BiPredicate<Point, Point> withinReach,
      Function<Point, Stream<Point>> potentialNeighbors)
   {
//        if (horiz == 0 || world.isOccupied(newPos)  && world.getOccupancyCell(newPos).getClass() != House.class) {
//            int vert = Integer.signum(destPos.y - this.position.y);
      int horiz = Integer.signum(end.x - start.x);
      Point newPos = new Point(start.x + horiz, start.y);
//

      /* Does not check withinReach.  Since only a single step is taken
       * on each call, the caller will need to check if the destination
       * has been reached.
       */
      return potentialNeighbors.apply(start)
         .filter(canPassThrough)
         .filter(pt ->
            !pt.equals(start)
            && !pt.equals(end)
            && Math.abs(end.x - pt.x) <= Math.abs(end.x - start.x)
            && Math.abs(end.y - pt.y) <= Math.abs(end.y - start.y))
         .limit(1)
         .collect(Collectors.toList());
   }
}