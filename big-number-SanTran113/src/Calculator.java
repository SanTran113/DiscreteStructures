
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Calculator {

    public static LinkedList_ add(LinkedList_ num1, LinkedList_ num2) {
        // NOTE: when i refer to "number" I will be talking about "123,"
        //       when I refer to "digit" I will be talking about the "1" from "123"
        // NOTE: I will be adding 123 + 456 for examples

        int gNum = 0;       // greater number of the two numbers we will be adding
        LinkedList_ finalNode = new LinkedList_();      // the final number from adding
        Node node1 = num1.getTop();     // get the last digit of the first number (get "3" from "123")
        Node node2 = num2.getTop();     // get the last digit of the second number (get "6" from "456")
        int remainder = 0; // if we need to carry something (i.e. 6 + 7 = 13 we will be carrying the "3" from 13)
        int temp = 0;

        // finding out which number is larger than the other
        if (node1.getSpot() > node2.getSpot()) {    // is the first number greater than the second number?
            gNum = num1.getCount();     // if it(the question) is true, gNum (greater number) will be the first number
        } else {
            gNum = num2.getCount();     // if it(the question) is false, gNum (greater number) will be the second number
        }

        for (int i = gNum; i > 0; i--) { // for loop until we get to the end of the largest number

            int add1 = 0; // first digit that we are adding
            int add2 = 0; // second digit that we are adding

            if (node1 != null) { // make sure that the node we are looking at from the first number exists
                // if it exists:
                add1 = node1.getValue();    // add1 = get the last digit from the first number (integer rather than a node) ("3" from "123")
                node1 = node1.getRest();    // node1 = get the rest of the number ("2 -> 1" from "123")
            }


            if (node2 != null) { // make sure that the node we are looking at from the second number exists
                // if it exists:
                add2 = node2.getValue(); // add2 = get the last digit from the second number (integer rather than a node) ("6" from "456")
                node2 = node2.getRest(); // node2 = get the rest of the number ("5 -> 4" from "456")
            }


            temp = add1 + add2 + remainder; // add together the digits we found with the remainder from previous calculations (if there was any)
            remainder = 0; // reset remainder
            if (temp >= 10 && i != 1) {     // if the numbers that we add is greater than 10 (i.e. 6 + 7 = 13) AND is not the last numbers we are adding THEN:
                remainder = Math.floorDiv(temp, 10); // our reaminder will get "3" from 13 but dividing it into 1.3
                temp = temp % 10; // our temp node will get the "1" from 13
            }
            finalNode.push(temp); // push the "1' into our final node or our solution
        }
        // NOTE: 123 + 456 = 579 so our final node will look like (5 -> 7 -> 9) because we are pushing each new final digit in front of the previously calculated digit
        // AFTER we are done adding all our digits making up our final number:

        // this was an edge case where i would get a zero in the front of my numbers so i decided to just check for it???
        if(Integer.toString(finalNode.getTop().getValue()).length() > 1) {  // if the length of the final number is greater than one then:
            String t = Integer.toString(finalNode.getTop().getValue()); //  t = the first digit of the final number (but as a string rather than an integer)
            finalNode.pop(); // get rid of head node, digit (t)
        for (int n = t.length() - 1; n >= 0; n--) {     // for the length of t:
            finalNode.push(Integer.parseInt(String.valueOf(t.charAt(n)))); // change the stringed digit back into an integer
        }
    }
        return finalNode; // Linked List (5 -> 7 -> 9)

}


    public static LinkedList_ multiply(LinkedList_ num1, LinkedList_ num2) {
        int gNum = 0;
        int remainder = 0;
        LinkedList_ maxList;
        LinkedList_ minList;
        Node node1 = num1.getTop();
        Node node2 = num2.getTop();
        LinkedList_ firstRow = new LinkedList_();

        int temp = 0;
        int count = 0;
        LinkedList_ secondRow = new LinkedList_();      //



        if (node1.getSpot() > node2.getSpot()) {
            gNum = num1.getCount();
            maxList = num1;
            minList = num2;
        } else {
            gNum = num2.getCount();
            maxList = num2;
            minList = num1;
        }

        Node minNode = minList.getTop();
        Node maxNode = maxList.getTop();



        while (minNode != null) {
            maxNode = maxList.getTop();      // every i is every node
            secondRow = new LinkedList_();


            for (int j = 0; j < count; j ++) {
                secondRow.push(0);
            }

            while (maxNode != null) {       //what we are multiplying against
                temp = (minNode.getValue() * maxNode.getValue()) + remainder;
                remainder = 0;
                if (temp >= 10 && maxNode.getSpot() != 1) { //carry
                    remainder = Math.floorDiv(temp, 10);
                    temp = temp % 10;
                }

                if (minNode.getSpot() == minList.getCount()) { // after first iteration
                    firstRow.push(temp);
                }
                else  {
                    secondRow.push(temp);
                }
//                final_.add(temp);
                    maxNode = maxNode.getRest();
            }

            count += 1;

            if (firstRow.getTop() != null && secondRow.getTop() != null) {
                firstRow = reverseLinkedList(firstRow);
                secondRow = reverseLinkedList(secondRow);
                firstRow = add(firstRow, secondRow);
            }
            minNode = minNode.getRest();
        }


//        List<String> testList = Arrays.asList(str);
//        for (String l : testList) {
//            List<String> finalStr = new ArrayList<String>();
//            for (String c : l) {
//                String strPattern = "^0+(?!$)";
//                c = c.replaceAll(strPattern, "");
//                finalStr.add(c);
//            }
//            String temp = String.join(" ", finalStr);
//            finalFinalStr.add(temp);
//        }

        return firstRow;
    }


    public static LinkedList_ reverseLinkedList(LinkedList_ list) {
        LinkedList_ newList = new LinkedList_();
        Node nodeList = list.getTop();
        List<Integer> testList = new ArrayList<>();

        while (nodeList != null) {
            testList.add(nodeList.getValue());
            nodeList = nodeList.getRest();
        }

        for (int k = 0; k < testList.size(); k++) {
            newList.push(testList.get(k));
        }

        return newList;
    }
}