import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;


public class TestCases
{
   public static final double DELTA = 0.00001;

   /* some sample tests but you must write more! see lab write up */

   @Test
   public void testWorkSpaceAreaOfAllShapes()
   {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), 
                 Color.BLACK));

      assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
      assertEquals(61.09516775478293, ws.getPerimeterOfAllShapes(), DELTA);
      assertEquals(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK), ws.get(0));
      assertEquals(new Circle(5.678, new Point(2, 3), Color.BLACK), ws.get(1));
      assertEquals(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK), ws.get(2));
      assertEquals(3, ws.size());
   }

   @Test
   public void testWorkSpace_01()
   {
      WorkSpace ws = new WorkSpace();
      List<Circle> expectedCircles = new LinkedList<>();
      List<Rectangle> expectedRectangles = new LinkedList<>();
      List<Triangle> expectedTriangles = new LinkedList<>();
      List<Shape> expectedColors = new LinkedList<>();


      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(35.6759261, new Point(0, 0), Color.RED);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Rectangle r1 = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);
      Rectangle r2 = new Rectangle(11.234, 15.678, new Point(12, 13), Color.LIGHT_GRAY);
      Triangle t1 = new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK);
      Triangle t2 = new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.GREEN);

      ws.add(c1);
      ws.add(c2);
      ws.add(r1);
      ws.add(r2);
      ws.add(t1);
      ws.add(t2);

      expectedCircles.add(c1);
      expectedCircles.add(c2);
      expectedRectangles.add(r1);
      expectedRectangles.add(r2);
      expectedTriangles.add(t1);
      expectedTriangles.add(t2);
      expectedColors.add(r1);
      expectedColors.add(t1);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expectedCircles, ws.getCircles());
      assertEquals(expectedRectangles, ws.getRectangles());
      assertEquals(expectedTriangles, ws.getTriangles());
      assertEquals(expectedColors, ws.getShapesByColor(Color.BLACK));
   }

   @Test
   public void testWorkSpaceGetFunctions_02()
   {
      WorkSpace ws = new WorkSpace();
      List<Circle> expectedCircles = new LinkedList<>();
      List<Rectangle> expectedRectangles = new LinkedList<>();
      List<Triangle> expectedTriangles = new LinkedList<>();
      List<Shape> expectedColors = new LinkedList<>();


      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.DARK_GRAY);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.CYAN);
      Rectangle r1 = new Rectangle(89.23158, 6.93185, new Point(8, 9), Color.CYAN);
      Rectangle r2 = new Rectangle(30.7856, 20.27138, new Point(1, 1), Color.CYAN);
      Triangle t1 = new Triangle(new Point(16,89), new Point(5,6), new Point(100,29), Color.PINK);
      Triangle t2 = new Triangle(new Point(9,9), new Point(2,3), new Point(12,-8), Color.YELLOW);

      ws.add(c1);
      ws.add(c2);
      ws.add(t1);
      ws.add(t2);
      ws.add(r1);
      ws.add(r2);

      expectedCircles.add(c1);
      expectedCircles.add(c2);
      expectedRectangles.add(r1);
      expectedRectangles.add(r2);
      expectedTriangles.add(t1);
      expectedTriangles.add(t2);
      expectedColors.add(c2);
      expectedColors.add(r1);
      expectedColors.add(r2);


      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expectedCircles, ws.getCircles());
      assertEquals(expectedRectangles, ws.getRectangles());
      assertEquals(expectedTriangles, ws.getTriangles());
      assertEquals(expectedColors, ws.getShapesByColor(Color.CYAN));
   }

   /* HINT - comment out implementation tests for the classes that you have not 
    * yet implemented */
   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, 
         new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals(0, clazz.getFields().length, "Unexpected number of public fields");

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals(expectedMethodNames.size(), publicMethods.size(),
              "Unexpected number of public methods");

      assertTrue(expectedMethodNames.size() == expectedMethodReturns.size(),
              "Invalid test configuration");
      assertTrue(expectedMethodNames.size() == expectedMethodParameters.size(),
              "Invalid test configuration");

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }

   @Test
   public void testCircle_01()
   {
      Circle c = new Circle(35.6759261, new Point(0, 0), Color.RED);
      Circle c2 = new Circle(35.6759261, new Point(0, 0), Color.RED);
      Circle c3 = new Circle(35.6759261, new Point(0, 3), Color.BLACK);

      assertEquals(c,  c2);
      assertNotEquals(c, c3);
      assertEquals(224.158455, c.getPerimeter(), DELTA);
      assertEquals(3998.5302321329, c.getArea(), DELTA);
      assertEquals(35.6759261, c.getRadius());
      assertEquals(new Point(0, 0), c.getCenter());
      assertEquals(Color.RED, c.getColor());
      c.setRadius(79.6);
      assertEquals(79.6, c.getRadius());
      c.setColor(Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());
      c.translate(new Point(-1, -5));
      assertEquals(new Point(-1, -5), c.getCenter());
   }

   @Test
   public void testCircle_02()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(c,  new Circle(5.678, new Point(2, 3), Color.BLACK));
      assertNotEquals(c,  new Circle(5.678, new Point(2, 3), Color.CYAN));
      assertEquals(35.6759261, c.getPerimeter(), DELTA);
      assertEquals(101.28395440846, c.getArea(), DELTA);
      assertEquals(5.678, c.getRadius());
      assertEquals(new Point(2, 3), c.getCenter());
      assertEquals(Color.BLACK, c.getColor());
      c.setRadius(89.567);
      assertEquals(89.567, c.getRadius());
      c.setColor(Color.CYAN);
      assertEquals(Color.CYAN, c.getColor());
      c.translate(new Point(10, 12));
      assertEquals(new Point(12, 15), c.getCenter());
   }

   @Test
   public void testRectangle_01()
   {
      Rectangle rectangle = new Rectangle(30.7856, 20.27138, new Point(1, 1), Color.CYAN);
      Rectangle rectangle2 = new Rectangle(30.7856, 20.27138, new Point(1, 1), Color.CYAN);
      Rectangle rectangle3 = new Rectangle(30.785, 20.27138, new Point(1, 1), Color.CYAN);

      assertEquals(rectangle, rectangle2);
      assertNotEquals(rectangle, rectangle3);
      assertEquals(102.113960, rectangle.getPerimeter(), DELTA);
      assertEquals(624.066596128, rectangle.getArea(), DELTA);
      assertEquals(30.7856, rectangle.getWidth());
      assertEquals(20.27138, rectangle.getHeight());
      assertEquals(Color.CYAN, rectangle.getColor());
      assertEquals(new Point(1,1), rectangle.getTopLeft());
      rectangle.setHeight(89.567);
      assertEquals(89.567, rectangle.getHeight());
      rectangle.setWidth(89.567);
      assertEquals(89.567, rectangle.getWidth());
      rectangle.setColor(Color.RED);
      assertEquals(Color.RED, rectangle.getColor());
      rectangle.translate(new Point(0, 0));
      assertEquals(new Point(1, 1), rectangle.getTopLeft());
   }

   @Test
   public void testRectangle_02()
   {
      Rectangle rectangle = new Rectangle(89.23158, 6.93185, new Point(8, 9), Color.MAGENTA);

      assertEquals(rectangle, new Rectangle(89.23158, 6.93185, new Point(8, 9), Color.MAGENTA));
      assertNotEquals(rectangle, new Rectangle(30.785, 20.27138, new Point(1, 1), Color.CYAN));
      assertEquals(192.326860, rectangle.getPerimeter(), DELTA);
      assertEquals(618.539927823, rectangle.getArea(), DELTA);
      assertEquals(89.23158, rectangle.getWidth());
      assertEquals(6.93185, rectangle.getHeight());
      assertEquals(Color.MAGENTA, rectangle.getColor());
      assertEquals(new Point(8,9), rectangle.getTopLeft());
      rectangle.setHeight(56.31);
      assertEquals(56.31, rectangle.getHeight());
      rectangle.setWidth(0);
      assertEquals(0, rectangle.getWidth());
      rectangle.setColor(Color.BLUE);
      assertEquals(Color.BLUE, rectangle.getColor());
      rectangle.translate(new Point(1, 1));
      assertEquals(new Point(9, 10), rectangle.getTopLeft());
   }

   @Test
   public void testTriangle_01()
   {
      Triangle tri = new Triangle(new Point(9,9), new Point(2,3), new Point(12,-8), Color.YELLOW);
      Triangle tri2 = new Triangle(new Point(9,9), new Point(2,3), new Point(12,-8), Color.YELLOW);
      Triangle tri3 = new Triangle(new Point(9,9), new Point(1,3), new Point(12,-8), Color.YELLOW);

      assertEquals(tri, tri2);
      assertNotEquals(tri, tri3);
      assertEquals(41.34828970624346, tri.getPerimeter(), DELTA);
      assertEquals(68.5, tri.getArea(), DELTA);
      assertEquals(new Point(9,9), tri.getVertexA());
      assertEquals(new Point(2,3), tri.getVertexB());
      assertEquals(new Point(12,-8), tri.getVertexC());
      assertEquals(Color.YELLOW, tri.getColor());
      tri.setColor(Color.WHITE);
      assertEquals(Color.WHITE, tri.getColor());
      tri.translate(new Point(5, 5));
      assertEquals(new Point(14,14), tri.getVertexA());
      assertEquals(new Point(7,8),  tri.getVertexB());
      assertEquals(new Point(17,-3), tri.getVertexC());
   }

   @Test
   public void testTriangle_02()
   {
      Triangle tri = new Triangle(new Point(16,89), new Point(5,6), new Point(100,29), Color.PINK);

      assertEquals(tri, new Triangle(new Point(16,89), new Point(5,6), new Point(100,29), Color.PINK));
      assertNotEquals(tri, new Triangle(new Point(9,9), new Point(1,3), new Point(12,-8), Color.YELLOW));
      assertEquals(284.6982110265486, tri.getPerimeter(), DELTA);
      assertEquals(3816, tri.getArea(), DELTA);
      assertEquals(new Point(16,89), tri.getVertexA());
      assertEquals(new Point(5,6), tri.getVertexB());
      assertEquals(new Point(100,29), tri.getVertexC());
      assertEquals(Color.PINK, tri.getColor());
      tri.setColor(Color.ORANGE);
      assertEquals(Color.ORANGE, tri.getColor());
      tri.translate(new Point(10, 10));
      assertEquals(new Point(26,99), tri.getVertexA());
      assertEquals(new Point(15,16),  tri.getVertexB());
      assertEquals(new Point(110,39), tri.getVertexC());
   }

}
