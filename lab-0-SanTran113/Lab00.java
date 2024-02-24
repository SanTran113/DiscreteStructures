public class Lab00
{
//   public static void main(String[] args)
//   {
//      System.out.println("Hello World");
//   }
public static void main(String[] args) {
   int x = 5;
   String y = "hello";
   double z = 9.8;

   printText(x, y, z);
   makeArray();
   callfunction(y);
   countingLoop();
   }
   public static void printText(int x, String y, double z) {
      System.out.println("x: " + x + " y: "  + y + " z: " + z);
   }
   public static void makeArray() {
      int[] intArray = new int[4];
      intArray[0] = 3;
      intArray[1] = 6;
      intArray[2] = -1;
      intArray[3] = 2;
      for (int i = 0; i < intArray.length; i++)
         System.out.println(intArray[i]);
   }
   public static void callfunction(String y) {
//      String numFound = char_count(y, "l");
      System.out.println("Found: " + char_count(y, 'l'));
   }
   public static int char_count(String s, Character c) {
      int count = 0;

      for (int i = 0; i < s.length(); i ++)
         if (s.charAt(i) == c) {
            count += 1;
         }
      return count;
   }
   public static void countingLoop() {
      for (int j = 1; j < 11; j++)
         System.out.print(j + " ");
   }
}
