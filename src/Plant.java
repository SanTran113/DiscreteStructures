import processing.core.PImage;

import java.util.List;

public abstract class Plant {
    String id;
    Point position;
    List<PImage> images;
    int imageIndex;
    double actionPeriod;
    double animationPeriod;
    int health;

    public Plant(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
        this.health = health;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health2) {
        this.health = health2;
    }


}
