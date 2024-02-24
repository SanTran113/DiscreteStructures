import java.time.LocalTime;
import java.util.Objects;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   public boolean equals(Object o) {
      if (o == null || this.getClass() != o.getClass()) {
         return false;
      }

      CourseSection c = (CourseSection) o;
      return Objects.equals(this.prefix, c.prefix) && Objects.equals(this.number, c.number) && this.enrollment == c.enrollment &&
              Objects.equals(this.startTime, c.startTime) && Objects.equals(this.endTime, c.endTime);
   }
      public int hashCode() {
         int hash = 1;
         hash = hash * 31 + ((prefix == null) ? 0: prefix.hashCode());
         hash = hash * 31 + ((number == null) ? 0: number.hashCode());
         hash = hash * 31 + enrollment;
         hash = hash * 31 + ((startTime == null) ? 0: startTime.hashCode());
         hash = hash * 31 + ((endTime == null) ? 0: endTime.hashCode());

         return hash;
      }

   }


   // additional likely methods not defined since they are not needed for testing

