import org.junit.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// right-click on @Test and select:
//    -> "show context actions" -> "add junit ___ to classpath"
// if junit 5.8 is already added to the classpath (i.e. doesn't show as an option),
// you can instead right-click and select:
//    -> "show context actions" -> "import class"
//    e.g. import org.junit.jupiter.api.Test;
// Then import the assert statements as needed.

public class TestCases
{
   @Test
   public void testExercise1()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void testExercise2()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(1, 10), LocalTime.of(2, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void testExercise3()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testExercise4()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 34,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }
//   @Test
//   public void studentTestExercise1()
//   {
//      List<CourseSection> courseSectionList;
//
//      courseSectionList.put(new CourseSection("CSC", "203", 35,
//           LocalTime.of(9, 40), LocalTime.of(11, 0)));
//      final CourseSection two = new CourseSection("CSC", "203", 35,
//              LocalTime.of(9, 40), LocalTime.of(11, 0));
//      final Student s = new Student("Chara", "Mel", 21,
//              (one, two);
//
//      assertEquals(one.hashCode(), two.hashCode());
//   }
//
//   @Test
//   public void studentTestExercise2()
//   {
//      final CourseSection one = new CourseSection("CSC", "203", 35,
//              LocalTime.of(9, 10), LocalTime.of(10, 0));
//      final CourseSection two = new CourseSection("CSC", "203", 34,
//              LocalTime.of(9, 10), LocalTime.of(10, 0));
//
//      assertNotEquals(one.hashCode(), two.hashCode());
//   }

}
