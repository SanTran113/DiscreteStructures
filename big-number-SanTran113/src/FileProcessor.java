import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileProcessor {

    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     * @return
     */
    public static void processFile(String filePath) {
        List<String> equations = fileToListOfStrings(filePath);
        List<String> results = inputToResult(equations);

        for (int i = 0; i < equations.size(); i++) {
            if (i != equations.size() - 1) {
                System.out.println(equations.get(i) + " = " + results.get(i));
            } else {
                System.out.println(equations.get(i) + " = " + results.get(i));
            }
        }
    }

    public static List<String> fileToListOfStrings(String filePath) {
        File infile = new File(filePath);
        List<String> s = new ArrayList<String>();
        List<String> finalFinalStr = new ArrayList<String>();
        List<String[]> testList = new ArrayList<String[]>();


        String[] testStr = new String[0];
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                // TODO: Process each line of the input file here.
                String line = scan.nextLine();
                String trimLine = line.replaceAll("\\s+", " ").trim();
                if (!(trimLine.equals(""))) {
                    s.add(trimLine);
                }
            }

            for (String str : s) {
                testStr = str.split(" ");
                testList.add(testStr);

            }

            for (String[] l : testList) {
                List<String> finalStr = new ArrayList<String>();
                for (String c : l) {
                    String strPattern = "^0+(?!$)";
                    c = c.replaceAll(strPattern, "");
                    finalStr.add(c);
                }
                String temp = String.join(" ", finalStr);
                finalFinalStr.add(temp);
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + infile.getPath());
        }
        return finalFinalStr;
    }

    public static List<String> inputToResult(List<String> stringList) {
        LinkedList_ tempLinkedList = new LinkedList_();
        String[] tempStr;
        List<String> finalList = new ArrayList<>();

        for (String equation : stringList) {
            String operator = "";
            LinkedList_ num1 = new LinkedList_();
            LinkedList_ num2 = new LinkedList_();
            tempStr = equation.split(" ");
            String result = "";


            for (String c : tempStr) {      //finding num1 and num2
                tempLinkedList = new LinkedList_();
                if (Objects.equals(c, "*") || Objects.equals(c, "+")) {
                    operator = c;
                } else {
                    String[] character = c.split("");     // ["123"] -> ["1", "2", "3"]
                    for (String number : character) {
                        tempLinkedList.push(Integer.parseInt(number));
                    }
                }

                if (operator.equals("")) {
                    num1 = tempLinkedList;
                } else {
                    num2 = tempLinkedList;
                }
            }

            if (operator.equals("*")) {     //mult or adding linked lists
                result = linkedListToString(Calculator.multiply(num1, num2));
            } else {
                result = linkedListToString(Calculator.add(num1, num2));
            }

            String[] character = result.split("");     // ["123"] -> ["1", "2", "3"]
            int sum = 0;
            for (String number : character) {
                sum += Integer.parseInt(number);
            }
            if (sum == 0) {
                result = "0";
            }

            finalList.add(result);
        }
        return finalList;
    }

    public static String linkedListToString(LinkedList_ list) {
        Node node = list.getTop();
        String str = "";
        while (node != null) {
            str = str + node.getValue();
            node = node.getRest();
        }
        return str;
    }

    public static LinkedList_ stringToLinkedList(String str) {
        LinkedList_ final_ = new LinkedList_();
        for (int i = 0; i < str.length(); i++) {
            final_.push(Integer.parseInt(String.valueOf(str.charAt(i))));
        }
        return final_;
    }

}