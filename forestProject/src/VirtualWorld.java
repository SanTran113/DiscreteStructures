import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

import processing.core.*;

public final class VirtualWorld extends PApplet {
    private static String[] ARGS;

    private static final int VIEW_WIDTH = 640;
    private static final int VIEW_HEIGHT = 480;
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;

    private static final int VIEW_COLS = VIEW_WIDTH / TILE_WIDTH;
    private static final int VIEW_ROWS = VIEW_HEIGHT / TILE_HEIGHT;

    private static final String IMAGE_LIST_FILE_NAME = "imagelist";
    private static final String DEFAULT_IMAGE_NAME = "background_default";
    private static final int DEFAULT_IMAGE_COLOR = 0x808080;

    private static final String FAST_FLAG = "-fast";
    private static final String FASTER_FLAG = "-faster";
    private static final String FASTEST_FLAG = "-fastest";
    private static final double FAST_SCALE = 0.5;
    private static final double FASTER_SCALE = 0.25;
    private static final double FASTEST_SCALE = 0.10;

    private String loadFile = "world.sav";
    private long startTimeMillis = 0;
    private double timeScale = 1.0;

    private ImageStore imageStore;
    private WorldModel world;
    private WorldView view;
    private EventScheduler scheduler;

    public void settings() {
        size(VIEW_WIDTH, VIEW_HEIGHT);
    }

    /*
       Processing entry point for "sketch" setup.
    */
    public void setup() {
        parseCommandLine(ARGS);
        loadImages(IMAGE_LIST_FILE_NAME);
        loadWorld(loadFile, this.imageStore);

        this.view = new WorldView(VIEW_ROWS, VIEW_COLS, this, world, TILE_WIDTH, TILE_HEIGHT);
        this.scheduler = new EventScheduler();
        this.startTimeMillis = System.currentTimeMillis();
        this.scheduleActions(world, scheduler, imageStore);
    }

    public void draw() {
        double appTime = (System.currentTimeMillis() - startTimeMillis) * 0.001;
        double frameTime = (appTime - scheduler.getCurrentTime())/timeScale;
        this.update(frameTime);
        view.drawViewport();
    }

    public void update(double frameTime){
        scheduler.updateOnTime(frameTime);
    }

    // Just for debugging and for P5
    // Be sure to refactor this method as appropriate
    public void mousePressed() {
        Point pressed = mouseToPoint();
        System.out.println("CLICK! " + pressed.x + ", " + pressed.y);

        // Visulization (Affects 7 tiles of the world)
        for (Point p : around(pressed)) {
            world.setBackgroundCell(p, new Background(Functions.NEWBACKGROUND_KEY, imageStore.getImageList(Functions.NEWBACKGROUND_KEY)));
        }


        // Effect (Affect an Entity, change in appearance and behavior)
        // if the entity fairy is withinReach() of pressed then transform
        if (world.getOccupancyCell(pressed) != null) {
            System.out.println(world.getOccupancyCell(pressed).getId());

            if (world.getOccupancyCell(pressed).getClass() == Dude_Not_Full.class || world.getOccupancyCell(pressed).getClass() == Dude_Full.class) {
                // whoever's ID it is transform that into the tranformed version
//                if (key ) {
                String key = String.valueOf(world.getOccupancyCell(pressed).getClass());
                    Entity transformedEntity = Functions.createTransformedEntity(key + "_transformed", pressed,
                            .2, .10,
                            imageStore.getImageList( "zuko"));
//            }
                world.removeEntityAt(pressed);
                world.addEntity(transformedEntity);
                transformedEntity.scheduleActions(scheduler, world, imageStore);
            } else if (world.getOccupancyCell(pressed).getClass() == House.class) {
//                Entity stump = Functions.createStump(Functions.STUMP_KEY + "_" + this.id, this.position, imageStore.getImageList(Functions.STUMP_KEY));

                String key = String.valueOf(world.getOccupancyCell(pressed).getClass());
                Entity transformedHouse = Functions.createTransformedHouse(key + "_transformed", pressed, imageStore.getImageList( "house_transformed"));
//            }
                world.removeEntityAt(pressed);
                world.addEntity(transformedHouse);
            } else if (world.getOccupancyCell(pressed).getClass() == Obstacle.class) {
                String key = String.valueOf(world.getOccupancyCell(pressed).getClass());
                for (Point p : around(pressed)) {
                    if (world.getOccupancyCell(p)!= null) {
                        if (world.getOccupancyCell(p).getClass().equals(Obstacle.class)) {
                                Entity transformedWater = Functions.createWater(key + "_transformed", p, 0.5, imageStore.getImageList("waterTransformed"));
                                world.removeEntityAt((p));
                                world.addEntity(transformedWater);
                                transformedWater.scheduleActions(scheduler, world, imageStore);
                        }
                    }
                }
            }
        }


        // New Entity (New Type of Entity to Spawn)
        if (!((world.getOccupancyCell(pressed) != null) && (world.getOccupancyCell(pressed).getClass() != TransformedEntity.class))) {
            NewEntity newEntity = Functions.createNewEntity(Functions.NEWENTITY_KEY, pressed,
                    0.1, 0.1, imageStore.getImageList(Functions.NEWENTITY_KEY));
            world.addEntity(newEntity);
            newEntity.scheduleActions(scheduler, world, imageStore);

        }

            // gets information that is stored in .isPresent
//            Optional<Entity> entityOptional = world.getOccupant(pressed);
//            if (entityOptional.isPresent()) {
//                Entity entity = entityOptional.get();
//            }

        }

    // finds the points around the point
    public List<Point> around(Point point) {
        List<Point> pointList = new ArrayList<>();
        Point pointRight = new Point (point.x + 1, point.y);
        Point pointLeft = new Point (point.x - 1, point.y);
        Point pointDown = new Point (point.x, point.y + 1);
        Point pointTop = new Point (point.x, point.y - 1);
        Point pointTopLeft = new Point (point.x - 1, point.y - 1);
        Point pointTopRight = new Point (point.x + 1, point.y - 1);
        Point pointDownRight = new Point (point.x + 1, point.y + 1);
        Point pointDownLeft = new Point (point.x - 1, point.y + 1);

        Point pointRight2 = new Point (point.x + 2, point.y);
        Point pointLeft2 = new Point (point.x - 2, point.y);
        Point pointDown2 = new Point (point.x, point.y + 2);
        Point pointTop2 = new Point (point.x, point.y - 2);

        Point pointTopLeft2 = new Point (point.x - 1, point.y - 2);
        Point pointTopRight2 = new Point (point.x + 1, point.y - 2);
        Point pointDownRight2 = new Point (point.x + 2, point.y + 1);
        Point pointDownLeftLeft = new Point (point.x - 2, point.y - 1);
        Point pointDownRightRight = new Point (point.x + 2, point.y - 1);
        Point pointDownLeft2 = new Point (point.x - 2, point.y + 1);
        Point pointTopTopLeft = new Point (point.x - 1, point.y + 2);
        Point pointTopTopRight = new Point (point.x + 1, point.y + 2);

        pointList.add(point);
        pointList.add(pointRight);
        pointList.add(pointLeft);
        pointList.add(pointDown);
        pointList.add(pointTop);
        pointList.add(pointTopLeft);
        pointList.add(pointTopRight);
        pointList.add(pointDownRight);
        pointList.add(pointDownLeft);
        pointList.add(pointRight2);
        pointList.add(pointLeft2);
        pointList.add(pointDown2);
        pointList.add(pointTop2);
        pointList.add(pointTopLeft2);
        pointList.add(pointTopRight2);
        pointList.add(pointDownRight2);
        pointList.add(pointDownLeft2);
        pointList.add(pointDownLeftLeft);
        pointList.add(pointTopTopLeft);
        pointList.add(pointDownRightRight);
        pointList.add(pointTopTopRight);

        List<Point> pointsToRemove = new ArrayList<>();
        for (Point p : pointList) {
            if (!(world.withinBounds(p))) {
                pointsToRemove.add(p);
            }
        }
        pointList.removeAll(pointsToRemove);

        System.out.println(pointList);
        return pointList;
    }


    public void scheduleActions(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        for (Entity entity : world.getEntities()) {
            entity.scheduleActions(scheduler, world, imageStore);
        }
    }

    private Point mouseToPoint() {
        return view.getViewport().viewportToWorld(mouseX / TILE_WIDTH, mouseY / TILE_HEIGHT);
    }

    public void keyPressed() {
        if (key == CODED) {
            int dx = 0;
            int dy = 0;

            switch (keyCode) {
                case UP -> dy -= 1;
                case DOWN -> dy += 1;
                case LEFT -> dx -= 1;
                case RIGHT -> dx += 1;
            }
            view.shiftView(dx, dy);
        }
    }

    public static Background createDefaultBackground(ImageStore imageStore) {
        return new Background(DEFAULT_IMAGE_NAME, imageStore.getImageList(DEFAULT_IMAGE_NAME));
    }

    public static PImage createImageColored(int width, int height, int color) {
        PImage img = new PImage(width, height, RGB);
        img.loadPixels();
        Arrays.fill(img.pixels, color);
        img.updatePixels();
        return img;
    }

    public void loadImages(String filename) {
        this.imageStore = new ImageStore(createImageColored(TILE_WIDTH, TILE_HEIGHT, DEFAULT_IMAGE_COLOR));
        try {
            Scanner in = new Scanner(new File(filename));
            imageStore.loadImages(in, this);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void loadWorld(String file, ImageStore imageStore) {
        this.world = new WorldModel();
        try {
            Scanner in = new Scanner(new File(file));
            Functions.load(world, in, imageStore, createDefaultBackground(imageStore));
        } catch (FileNotFoundException e) {
            Scanner in = new Scanner(file);
            Functions.load(world, in, imageStore, createDefaultBackground(imageStore));
        }
    }

    public void parseCommandLine(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case FAST_FLAG -> timeScale = Math.min(FAST_SCALE, timeScale);
                case FASTER_FLAG -> timeScale = Math.min(FASTER_SCALE, timeScale);
                case FASTEST_FLAG -> timeScale = Math.min(FASTEST_SCALE, timeScale);
                default -> loadFile = arg;
            }
        }
    }

    public static void main(String[] args) {
        VirtualWorld.ARGS = args;
        PApplet.main(VirtualWorld.class);
    }

    public static List<String> headlessMain(String[] args, double lifetime){
        VirtualWorld.ARGS = args;

        VirtualWorld virtualWorld = new VirtualWorld();
        virtualWorld.setup();
        virtualWorld.update(lifetime);

        return virtualWorld.world.log();
    }
}
