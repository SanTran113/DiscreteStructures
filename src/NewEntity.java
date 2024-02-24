import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import processing.core.PImage;
/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public final class NewEntity implements ExecuteActivity, AnimatingEntity{

        private String id;
        private Point position;
        private List<PImage> images;
        private int imageIndex;
        private double actionPeriod;
        private double animationPeriod;


        public NewEntity(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
            this.id = id;
            this.position = position;
            this.images = images;
            this.imageIndex = 0;
            this.actionPeriod = actionPeriod;
            this.animationPeriod = animationPeriod;
        }

        public String getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }

        public void setPosition(Point newPosition) {
            this.position = newPosition;
        }

        private boolean moveToNewEntity(WorldModel world, Entity target, EventScheduler scheduler) {
            if (Functions.adjacent(this.position, target.getPosition())) {
                world.removeEntity(scheduler, target);
                return true;
            } else {
                Point nextPos = nextPositionNewEntity(world, target.getPosition());

                if (!this.position.equals(nextPos)) {
                    world.moveEntity(scheduler, this, nextPos);
                }
                return false;
            }
        }

        public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
            scheduler.scheduleEvent(this, Functions.createActivityAction(this, world, imageStore), this.actionPeriod);
            scheduler.scheduleEvent(this, Functions.createAnimationAction(this, 0), getAnimationPeriod());
        }

        public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
            Optional<Entity> newEntityTarget = world.findNearest(this.position, new ArrayList<>(List.of(Dude_Not_Full.class, Dude_Full.class)));

            if (newEntityTarget.isPresent()) {
                Point tgtPos = newEntityTarget.get().getPosition();

                if (this.moveToNewEntity(world, newEntityTarget.get(), scheduler)) {
                     world.removeEntityAt(tgtPos);
                    setActionPeriod(getActionPeriod() + 0.2);
                }
            }

            scheduler.scheduleEvent(this, Functions.createActivityAction(this, world, imageStore), this.actionPeriod);
        }

        private Point nextPositionNewEntity(WorldModel world, Point destPos) {
            // want to use teh single step pathing stategy to take one step
            // want to keep world.isOccupied(newPos)  && world.getOccupancyCell(newPos).getClass() != House.class) {
            //            int vert = Integer.signum(destPos.y - this.position.y)
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);

            Predicate<Point> canPassThrough = (Point p1) -> {
                return  (!world.isOccupied(p1) || world.getOccupancyCell(p1).getClass() == House.class)&& world.withinBounds(p1);
                //world.getOccupancyCell(p1).getClass() == House.class;
            };

            BiPredicate<Point, Point> withinReach = Functions::adjacent;

            PathingStrategy strat = new AStarPathingStrategy();
            List<Point> path = strat.computePath(this.position, destPos, canPassThrough, withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

            // path should be in acending order, ecxulginh teh start / end point
            // should be able to get the 0th element from the list (if it exists)
            // return current point if no where to go
            if (path.isEmpty()) {
                return this.position;
            } else {
                return path.get(0);
            }

        }

        public void nextImage() {
            this.imageIndex = this.imageIndex + 1;
        }

        public double getAnimationPeriod() {
            return this.animationPeriod;
        }
        public double getActionPeriod() {
            return this.actionPeriod;
        }
        public void setActionPeriod(double actionPeriod) {
            this.actionPeriod = actionPeriod;
        }

        public PImage getCurrentImage() {
            return this.images.get(this.imageIndex % this.images.size());
        }

        /**
         * Helper method for testing. Preserve this functionality while refactoring.
         */
        public String log(){
            return this.id.isEmpty() ? null :
                    String.format("%s %d %d %d", this.id, this.position.x, this.position.y, this.imageIndex);
        }
    }


