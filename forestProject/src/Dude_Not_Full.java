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
public final class Dude_Not_Full extends Dude implements ExecuteActivity, AnimatingEntity{

    int resourceCount;
    public Dude_Not_Full(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod) {
        super(id, position, images, resourceLimit, actionPeriod, animationPeriod);
        this.resourceCount = resourceCount;
    }


    //changed target,health into if statements
    private boolean moveToNotFull(WorldModel world, Plant target, EventScheduler scheduler) {
        if (Functions.adjacent(getPosition(), target.getPosition())) {
            this.resourceCount += 1;
            target.health--;
            if (target instanceof Tree) {
                ((Tree) target).setHealth(((Tree) target).getHealth() - 1);
            } else if (target instanceof Sapling) {
                ((Sapling) target).setHealth(((Sapling) target).getHealth() - 1);
            }
            return true;
        } else {
            Point nextPos = nextPositionDude(world, target.getPosition());

            if (!getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Functions.createActivityAction(this, world, imageStore), getActionPeriod());
        scheduler.scheduleEvent(this, Functions.createAnimationAction(this, 0), getAnimationPeriod());
    }


    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveToNotFull(world, (Plant) target.get(), scheduler) || !transformNotFull(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, Functions.createActivityAction(this, world, imageStore), getActionPeriod());
        }
    }


    private Point nextPositionDude(WorldModel world, Point destPos) {
        // want to use teh single step pathing stategy to take one step
        // want to keep world.isOccupied(newPos)  && world.getOccupancyCell(newPos).getClass() != House.class) {
        //            int vert = Integer.signum(destPos.y - this.position.y)
//        int horiz = Integer.signum(destPos.x - this.getPosition().x);
//        Point newPos = new Point(this.getPosition().x + horiz, this.getPosition().y);

        Predicate<Point> canPassThrough = (Point p1) -> {
            return (!world.isOccupied(p1) || world.getOccupancyCell(p1).getClass() == Stump.class) && world.withinBounds(p1);
//                    world.getOccupancyCell(newPos).getClass() == Stump.class;
        };

        BiPredicate<Point, Point> withinReach = Functions::adjacent;

        PathingStrategy strat = new AStarPathingStrategy();
        List<Point> path = strat.computePath(this.getPosition(), destPos, canPassThrough, withinReach, PathingStrategy.CARDINAL_NEIGHBORS);

        // path should be in acending order, ecxulginh teh start / end point
        // should be able to get the 0th element from the list (if it exists)
        // return current point if no where to go
        if (path.isEmpty()) {
            return this.getPosition();
        } else {
            return path.get(0);
        }
    }

//        int horiz = Integer.signum(destPos.x - getPosition().x);
//        Point newPos = new Point(getPosition().x + horiz, getPosition().y);
//
//        if (horiz == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).getClass() != Stump.class) {
//            int vert = Integer.signum(destPos.y - getPosition().y);
//            newPos = new Point(getPosition().x, getPosition().y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos) && world.getOccupancyCell(newPos).getClass() != Stump.class) {
//                newPos = getPosition();
//            }
//        }
//
//        return newPos;
//    }

    // what i changed
    private boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.resourceCount >= getResourceLimit()) {
            Entity dude = Functions.createDudeFull(getId(), getPosition(), getActionPeriod(), getAnimationPeriod(), getResourceLimit(), getImages());

            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }


}
