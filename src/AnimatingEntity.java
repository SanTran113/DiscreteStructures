import processing.core.PImage;

public interface AnimatingEntity extends Entity {

//    String getId();
//
//     Point getPosition();
//
//     void setPosition(Point newPosition);


    void nextImage();

    double getAnimationPeriod();



}
