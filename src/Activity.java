/**
 * An action that can be taken by an entity
 */
public final class Activity implements Action{
    private ExecuteActivity entity;
    private WorldModel world;
    private ImageStore imageStore;

    public Activity(ExecuteActivity entity, WorldModel world, ImageStore imageStore) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }


    public void executeAction(EventScheduler scheduler) {
        this.entity.executeActivity(this.world, this.imageStore, scheduler);
    }
}
