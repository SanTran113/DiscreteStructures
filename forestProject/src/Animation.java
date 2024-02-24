//import javax.swing.Action;

/**
 * An action that can be taken by an entity
 */
public final class Animation implements Action {
    private AnimatingEntity entity;
    private int repeatCount;

    public Animation(AnimatingEntity entity, int repeatCount) {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }


    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity, Functions.createAnimationAction(entity, Math.max(this.repeatCount - 1, 0)), this.entity.getAnimationPeriod());
        }
    }
}
