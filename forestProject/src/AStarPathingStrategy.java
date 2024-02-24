import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


class AStarPathingStrategy implements PathingStrategy {

    private static class Node implements Comparable<Node> {
        private final Point point;
        private final double g;
        private final double h;
        private final Node previous;

        private Node(Point point, double g, double h, Node previous) {
            this.point = point;
            this.g = g;
            this.h = h;
            this.previous = previous;
        }

        private double getF() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(getF(), other.getF());
        }
    }


    @Override
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {


        List<Point> path = new LinkedList<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();
        Map<Point, Node> closedList = new HashMap<>();

        // add start node to open list
        int startF = Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
        Node startNode = new Node(start, 0, startF, null);
        openList.add(startNode);

        while (!openList.isEmpty()) {

            // choosing a node from openlist, with smallest f value
            Node current = openList.poll();
            Point currentPoint = current.point;     // bc intelJ said to do this

            // if I am within reach of my goal start adding everthing to the path
            if (withinReach.test(currentPoint, end)) {
                // Reconstruct the path
                while (current.previous != null) {
                    path.add(0, current.point);
                    current = current.previous;
                }
                return path;
            }

            // move current node into closed list
            closedList.put(currentPoint, current);


            Node finalCurrent = current;
            potentialNeighbors.apply(currentPoint)
                    .filter(canPassThrough)
                    .forEach(validNeighbor -> {

                        // distance from curr to start
                        double g = finalCurrent.g + 1;
                        // distance from adj node to end
                        double h = Math.abs(end.x - validNeighbor.x) + Math.abs(end.y - validNeighbor.y);
                        //total distance
                        double f = g + h;
                        
                        Node existingNode = closedList.get(validNeighbor);
                        // if node's neighbor exists && g value is less than the prev g
                        if (existingNode != null && g >= existingNode.g) {
                            return; // skip
                        }

                        // is the node already in the open list
                        existingNode = openList.stream()
                                .filter(node -> node.point.equals(validNeighbor))
                                .findFirst()
                                .orElse(null);
                        // if node's neighbor exists && g value is less than the prev g
                        if (existingNode != null && g >= existingNode.g) {
                            return; //skip
                        }

                        // replace old g value with new calculated g
                        // i.e. make new node if it passes all the checks above
                        Node neighbor = new Node(validNeighbor, g, h, finalCurrent);
                        if (existingNode != null) { // cautionary check bc i cant remove a node that does not exist
                            openList.remove(existingNode); // remove from open list because we have looked at all tbe neighbors
                        }

                        // add node to open list (replacing the node if there already was one)
                        openList.add(neighbor);
                    });
        }

        // No path found
        return path;
    }
}