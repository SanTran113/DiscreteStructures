import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public interface Entity {

    String getId();

    Point getPosition();

    void setPosition(Point newPosition);
    PImage getCurrentImage();

    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     */
    String log();
}
