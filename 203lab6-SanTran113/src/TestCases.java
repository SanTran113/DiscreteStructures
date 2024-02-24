


import java.util.*;
import java.util.function.Function;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   // neg if o1 < o2
   // pos if o1 > o2
   // 0 if o1 == o2

   @Test
   public void testArtistComparator_01()
   {
      ArtistComparator comparator = new ArtistComparator();

      assertTrue(comparator.compare(songs[0], songs[1]) < 0);
   }

   @Test
   public void testArtistComparator_02()
   {
      ArtistComparator comparator = new ArtistComparator();

      assertTrue(comparator.compare(songs[6], songs[2]) > 0);
   }

   @Test
   public void testArtistComparator_03()
   {
      ArtistComparator comparator = new ArtistComparator();

      assertTrue(comparator.compare(songs[3], songs[7]) == 0);
   }

   @Test
   public void testLambdaTitleComparator_01()
   {
      Comparator<Song> titleComparator = (Song o1, Song o2) ->
              o1.getTitle().compareTo(o2.getTitle());

      assertTrue(titleComparator.compare(songs[7], songs[2]) < 0);

   }

   @Test
   public void testLambdaTitleComparator_02()
   {
      Comparator<Song> titleComparator = (Song o1, Song o2) ->
              o1.getTitle().compareTo(o2.getTitle());

      assertTrue(titleComparator.compare(songs[0], songs[1]) > 0);

   }

   @Test
   public void testLambdaTitleComparator_03()
   {
      Comparator<Song> titleComparator = (Song o1, Song o2) ->
              o1.getTitle().compareTo(o2.getTitle());

      assertTrue(titleComparator.compare(songs[3], songs[5]) == 0);

   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> yearComparator = Comparator.comparing(Song::getYear).reversed();

      assertTrue(yearComparator.compare(songs[4], songs[2]) < 0);
      assertTrue(yearComparator.compare(songs[2], songs[1]) < 0);
      assertTrue(yearComparator.compare(songs[1], songs[0]) == 0);
      assertTrue(yearComparator.compare(songs[0], songs[3]) < 0);
      assertTrue(yearComparator.compare(songs[3], songs[5]) < 0);
      assertTrue(yearComparator.compare(songs[5], songs[7]) < 0);
      assertTrue(yearComparator.compare(songs[7], songs[6]) < 0);
      assertTrue(yearComparator.compare(songs[6], songs[4]) > 0);

   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> comp = (Song o1, Song o2) -> o1.getTitle().compareTo(o2.getTitle());
      Comparator<Song> comp2 = (Song o1, Song o2) -> o1.getArtist().compareTo(o2.getArtist());
      Comparator<Song> comp3 = comp.thenComparing(comp2);

      assertTrue(comp3.compare(songs[3], songs[5]) > 0);
      assertTrue(comp3.compare(songs[3], songs[7]) == 0);
      assertTrue(comp3.compare(songs[5], songs[7]) < 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      Comparator<Song> comp = (Song o1, Song o2) -> o1.getArtist().compareTo(o2.getArtist());
      Comparator<Song> comp2 = (Song o1, Song o2) -> o1.getTitle().compareTo(o2.getTitle());
      Function<Song, Integer> yearExtractor = Song::getYear;
      Comparator<Song> comp3 = Comparator.comparing(yearExtractor);
      Comparator<Song> finalComp = comp.thenComparing(comp2.thenComparing(comp3));

      Collections.sort(songList, finalComp);

      assertEquals(songList, expectedList);
   }
}
