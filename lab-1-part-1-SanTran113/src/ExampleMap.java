
import java.util.*;

public class ExampleMap {

  /**
   * Return a list of "high scoring" students --- High scoring students are
   * students who have all grades strictly greater than the given threshold.
   *
   * @param scoresByApplicantName A map of applicant names to applicant scores
   * @param scoreThreshold        The score threshold
   * @return The list of high-scoring applicant names
   */

  public static List<String> highScoringStudents(Map<String, List<CourseGrade>> scoresByApplicantName, int scoreThreshold) {
    List<String> students = new LinkedList<String>();
    students.addAll(scoresByApplicantName.keySet()); // add all students to a list
//    System.out.println(students);

      for (String names : students) {
        for (CourseGrade courseGrade : scoresByApplicantName.get(names)) {
          // if one of the scores is lower than the threshhold, remove the student from the list then break from checking further course grades
          if (courseGrade.getScore() < scoreThreshold) {
//            System.out.println(names);
            students.remove(names);
            break;
          }
        }
    }
    return students;
  }
}