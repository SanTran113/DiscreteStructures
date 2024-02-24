import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This file contains JUnit tests for some sample arithmetic
 * expressions. This file tests your program "as a whole", by
 * checking its printed output.
 *
 * YOU SHOULD NOT WRITE YOUR OWN TESTS THIS WAY, OR IN THIS FILE.
 * You should test the individual UNITS in your program, i.e., call
 * your add, multiply, and exponentiate methods and test their return
 * values.
 */
public class SampleTest {
    /**
     * The actual standard output stream.
     */
    private PrintStream old;

    /**
     * The streams we're using to capture printed output.
     */
    private ByteArrayOutputStream baos;


    /**
     * Gets called before each test method. Need to do this so that we can
     * capture the printed output from each method.
     */
    @BeforeEach
    public void setUp() {
        this.old = System.out; // Save a reference to the original stdout stream.
        this.baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @Test
    public void testSampleFile() {
        BigNumArithmetic.main(new String[]{"SampleInput.txt"});
        String output = this.baos.toString().trim().replaceAll("\r\n", "\n");
        assertEquals("1 + 2 = 3\n" +
                "3 * 5 = 15\n" +
                "0 * 1 = 0", output);
    }
    @Test
    public void testSampleFile2() {
        BigNumArithmetic.main(new String[]{"SampleInput2.txt"});
        String output = this.baos.toString().trim().replaceAll("\r\n", "\n");
        assertEquals("329712937129 + 2 = 329712937131\n" +
                "102371208312031031 * 89217532146056971039 = 9133306568409323100893244370036311209", output);
    }

    @Test
    public void testSampleFile3() {
        BigNumArithmetic.main(new String[]{"SampleInput3.txt"});
        String output = this.baos.toString().trim().replaceAll("\r\n", "\n");
        assertEquals("500 * 8 = 4000\n" +
                "4050 * 890 = 3604500\n" +
                "5 * 8 = 40\n" +
                "25000000000000 * 6900000000000000 = 172500000000000000000000000000\n" +
                "0 * 0 = 0\n" +
                "532 * 897 = 477204\n" +
                "0 * 8999 = 0", output);
    }


    /**
     * Gets called after each test method. Need to do this so that we are
     * no longer capturing all printed output and printed stuff appears
     * like normal.
     */
    @AfterEach
    public void tearDown() {
        System.out.flush();
        System.setOut(this.old);
    }

    @Test
    public void teststringToLinkedList_01() {
        String str = "51";
        LinkedList_ expected = new LinkedList_(new Node(1, 2, new Node(5, 1, null)), 2);
        assertEquals(FileProcessor.stringToLinkedList(str), expected);
    }

    @Test
    public void testStringToLinkedList_02() {
        String str = "123456789";
        LinkedList_ expected = new LinkedList_(new Node(9, 9,
                new Node(8, 8, new Node(7, 7, new Node(6, 6,
                        new Node(5, 5, new Node(4, 4,
                                new Node(3, 3, new Node(2, 2,
                                        new Node(1, 1, null))))))))), 9);
        assertEquals(FileProcessor.stringToLinkedList(str), expected);
    }



    @Test
    public void testPushLinkedList_01() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 1, null), 1);
        linkedList.push(5);
        LinkedList_ linkedList2 = new LinkedList_(new Node(5, 1, new Node(5, 1, null)), 2);
        assertEquals(linkedList, linkedList2);
    }

    @Test
    public void testPushLinkedList_02() {
        LinkedList_ linkedList = new LinkedList_();
        linkedList.push(5);
        LinkedList_ linkedList2 = new LinkedList_(new Node(5, 1, null), 1);
        assertEquals(linkedList, linkedList2);
        assertEquals(linkedList.getCount(), 1);
        assertEquals(linkedList.getTop().getValue(), 5);
        assertEquals(linkedList.getTop().getRest(), null);
        assertEquals(linkedList.getTop().getSpot(), 1);
    }

    @Test
    public void testPushLinkedList_03() {
        LinkedList_ linkedList = new LinkedList_();
        linkedList.push(1);
        linkedList.push(2);
        linkedList.push(3);
        linkedList.push(4);
        linkedList.push(5);
        linkedList.push(6);
        linkedList.push(7);
        linkedList.push(8);
        linkedList.push(9);
        LinkedList_ linkedList2 = new LinkedList_(new Node(9, 9,
                new Node(8, 8, new Node(7, 7, new Node(6, 6,
                        new Node(5, 5, new Node(4, 4,
                                new Node(3, 3, new Node(2, 2,
                                        new Node(1, 1, null))))))))), 9);
        assertEquals(linkedList, linkedList2);
    }

    @Test
    public void fileToListOfStrings() {
        String[] list = {"1 + 2", "3 * 5", "0 * 1"};
        List<String> stringList = Arrays.asList(list);
        assertEquals(FileProcessor.fileToListOfStrings("SampleInput.txt"), stringList);
    }

    @Test
    public void linkedListToString() {
        LinkedList_ linkedList = new LinkedList_(new Node(9, 3, new Node(5, 2, new Node(1, 1, null))), 3);
        String output = "951";
        assertEquals(FileProcessor.linkedListToString(linkedList), output);
    }

    @Test
    public void inputToResult_01() {
        List<String> input = new ArrayList<>();
        input.add("15 + 3");
        List<String> output = new ArrayList<>();
        output.add("18");
        List<String> in = FileProcessor.inputToResult(input);
        assertEquals(in, output);
    }

    @Test
    public void inputToResult_02() {
        List<String> input = new ArrayList<>();
        input.add("15 * 3");
        List<String> output = new ArrayList<>();
        output.add("45");
        assertEquals(FileProcessor.inputToResult(input), output);
    }

    @Test
    public void inputToResult_03() {
        List<String> input = new ArrayList<>();
        input.add("5 * 3");
        input.add("1 * 0");
        List<String> output = new ArrayList<>();
        output.add("15");
        output.add("0");
        assertEquals(FileProcessor.inputToResult(input), output);
    }

    @Test
    public void inputToResult_04() {
        List<String> input = new ArrayList<>();
        input.add("1 * 0");
        List<String> output = new ArrayList<>();
        output.add("0");
        assertEquals(FileProcessor.inputToResult(input), output);
    }

    @Test
    public void testAddition_01() {
        LinkedList_ linkedList = new LinkedList_(new Node(0, 2, new Node(5, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(1, 1, null), 1);
        LinkedList_ expected = new LinkedList_(new Node(5, 2, new Node(1, 1, null)), 2);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_02() {
        LinkedList_ linkedList = new LinkedList_(new Node(1, 2, new Node(5, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(8, 2, new Node(4, 1, null)), 2);
        LinkedList_ expected = new LinkedList_(new Node(9, 2, new Node(9, 1, null)), 2);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_03() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 1, null), 1);
        LinkedList_ linkedList2 = new LinkedList_(new Node(4, 1, null), 1);
        LinkedList_ expected = new LinkedList_(new Node(9, 1, null), 1);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_04() {
        LinkedList_ linkedList = new LinkedList_(new Node(1, 2, new Node(5, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(7, 2, new Node(5, 1, null)), 2);
        LinkedList_ input = Calculator.add(linkedList, linkedList2);
        LinkedList_ expected = new LinkedList_(new Node( 1, 3, new Node(0, 2, new Node(8, 1, null))), 3);
        assertEquals(input, expected);
    }


    @Test
    public void testAddition_05() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 2, new Node(1, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(5, 2, new Node(7, 1, null)), 2);
        LinkedList_ expected = new LinkedList_(new Node(9, 2, new Node(0, 1, null)), 2);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_06() {
        LinkedList_ linkedList = new LinkedList_(new Node(9, 3, new Node(5, 2, new Node(1, 1, null))), 3);
        LinkedList_ linkedList2 = new LinkedList_(new Node(4, 3, new Node(5, 2, new Node(9, 1, null))), 3);
        LinkedList_ expected = new LinkedList_(new Node(1, 4, new Node(1, 3, new Node(1, 2, new Node(3, 1, null)))), 4);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_07() {
        LinkedList_ linkedList = new LinkedList_(new Node(0, 10, new Node(9, 9,
                new Node(8, 8, new Node(7, 7, new Node(6, 6,
                        new Node(5, 5, new Node(4, 4,
                                new Node(3, 3, new Node(2, 2,
                                        new Node(1, 1, null)))))))))), 10);
        LinkedList_ linkedList2 = new LinkedList_(new Node(1, 9,
                new Node(2, 8, new Node(3, 7, new Node(4, 6,
                        new Node(5, 5, new Node(6, 4,
                                new Node(7, 3, new Node(8, 2,
                                        new Node(9, 1, null))))))))), 9);
        LinkedList_ expected = new LinkedList_(new Node(2, 10, new Node(2, 9,
                new Node(2, 8, new Node(2, 7, new Node(2, 6,
                        new Node(2, 5, new Node(2, 4,
                                new Node(2, 3, new Node(1, 2,
                                        new Node(1, 1, null)))))))))), 10);
        assertEquals(Calculator.add(linkedList, linkedList2), expected); //2222222211, not reversed
    }

    @Test
    public void testAddition_08() {
        LinkedList_ linkedList = new LinkedList_(new Node(1, 1, null), 1);
        LinkedList_ linkedList2 = new LinkedList_(new Node(1, 3, new Node(2, 2,
                new Node(3, 1, null))), 3);
        LinkedList_ expected = new LinkedList_(new Node(3, 3, new Node(2, 2,
                new Node(2, 1, null))), 3);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_09() {
        LinkedList_ linkedList = new LinkedList_(new Node(1, 1, null), 1);
        LinkedList_ linkedList2 = new LinkedList_(new Node(1, 9,
                new Node(2, 8, new Node(3, 7, new Node(4, 6,
                        new Node(5, 5, new Node(6, 4,
                                new Node(7, 3, new Node(8, 2,
                                        new Node(9, 1, null))))))))), 9);
        LinkedList_ expected = new LinkedList_(new Node(9, 9,
                new Node(8, 8, new Node(7, 7, new Node(6, 6,
                        new Node(5, 5, new Node(4, 4,
                                new Node(3, 3, new Node(2, 2,
                                        new Node(2, 1, null))))))))), 9);
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_10() {
        LinkedList_ linkedList = FileProcessor.stringToLinkedList("1234567890");
        LinkedList_ linkedList2 = FileProcessor.stringToLinkedList("987654321");
        LinkedList_ expected = Calculator.reverseLinkedList( FileProcessor.stringToLinkedList("2222222211"));
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_11() {
        LinkedList_ linkedList = FileProcessor.stringToLinkedList("329712937129");
        LinkedList_ linkedList2 = FileProcessor.stringToLinkedList("244495961769");
        LinkedList_ expected = Calculator.reverseLinkedList( FileProcessor.stringToLinkedList("574208898898"));
        assertEquals(Calculator.add(linkedList, linkedList2), expected);
    }

    @Test
    public void testAddition_12() {
        LinkedList_ linkedList = FileProcessor.stringToLinkedList("102371208312031031");
        LinkedList_ linkedList2 = FileProcessor.stringToLinkedList("89217532146056971039");
        LinkedList_ expected = Calculator.reverseLinkedList(FileProcessor.stringToLinkedList("89319903354369002070"));
        LinkedList_ input = Calculator.add(linkedList, linkedList2);
        assertEquals(input, expected);
    }

    // single digit 5 * 3
    @Test
    public void testMult_01() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 1, null), 1);
        LinkedList_ linkedList2 = new LinkedList_(new Node(3, 1, null), 1);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
        LinkedList_ expected = new LinkedList_(new Node(15, 1, null), 1);
        assertEquals(input, expected);
    }

//     single * double (15 * 3)
    @Test
    public void testMult_02() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 2, new Node(1, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(3, 1, null), 1);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
        LinkedList_ output = new LinkedList_(new Node(4, 2, new Node(5, 1, null)), 2);
        assertEquals(input, output);
    }

    // double * double (13 * 12) no carry
    @Test
    public void testMult_03() {
        LinkedList_ linkedList = new LinkedList_(new Node(3, 2, new Node(1, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(2, 2, new Node(1, 1, null)), 2);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
//        ArrayList<Integer> intList = new ArrayList<Integer>();
        LinkedList_ expected = new LinkedList_(new Node(1, 3, new Node(5, 2, new Node(6, 1, null))), 3);
        assertEquals(input, expected);
    }

    // double * double (15 * 15) w/ carry in first digit
    @Test
    public void testMult_04() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 2, new Node(1, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(5, 2, new Node(1, 1, null)), 2);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
        LinkedList_ expected = new LinkedList_(new Node(2, 3, new Node(2, 2, new Node(5, 1, null))), 3);
        assertEquals(input, expected);
    }

    // double * double (15 * 55) w/ carry in first and second digit
    @Test
    public void testMult_05() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 2, new Node(1, 1, null)), 2);
        LinkedList_ linkedList2 = new LinkedList_(new Node(5, 2, new Node(5, 1, null)), 2);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
//        List<LinkedList_> expected = new ArrayList<>();
//        expected.add(new LinkedList_(new Node(8, 3, new Node(2, 2, new Node(5, 1, null))), 3));
        LinkedList_ expected = new LinkedList_(new Node(8, 3, new Node(2, 2, new Node(5, 1, null))), 3);
        assertEquals(input, expected);
    }

    // more than two digits multiplication (255 * 315)
    @Test
    public void testMult_06() {
        LinkedList_ linkedList = new LinkedList_(new Node(5, 3, new Node(5, 2, new Node(2, 1, null))), 3);
        LinkedList_ linkedList2 = new LinkedList_(new Node(5, 3, new Node(1, 2, new Node(3, 1, null))), 3);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
        LinkedList_ expected = new LinkedList_(new Node(8, 5, new Node(0, 4, new  Node(3, 3, new Node(2, 2, new Node(5, 1, null))))), 5);
        assertEquals(input, expected);
    }

    @Test
    public void testMult_07() {
        LinkedList_ linkedList = new LinkedList_(new Node(1, 9,
                new Node(1, 8, new Node(1, 7, new Node(1, 6,
                        new Node(1, 5, new Node(1, 4,
                                new Node(1, 3, new Node(1, 2,
                                        new Node(1, 1, null))))))))), 9);
        LinkedList_ linkedList2 = new LinkedList_( new Node (5, 15, new Node (5, 14,
                new Node ( 5, 13, new Node(5, 12, new Node(5, 11, new Node (4, 10,
                        new Node(4, 9, new Node(4, 8, new Node(4, 7, new Node(3, 6,
                        new Node(3, 5, new Node(3, 4,
                                new Node(2, 3, new Node(2, 2,
                                        new Node(1, 1, null))))))))))))))), 15);
        LinkedList_ input = Calculator.multiply(linkedList, linkedList2);
        LinkedList_ expected = new LinkedList_( new Node (1, 23, new Node (3, 22, new Node( 5, 21,
                new Node(9, 20, new Node( 2, 19, new Node(6, 18, new Node(0, 17,
                        new Node ( 4, 16, new Node (9, 15, new Node (2, 14,
                new Node ( 5, 13, new Node(9, 12, new Node(1, 11, new Node (3, 10,
                        new Node(5, 9, new Node(0, 8, new Node(6, 7, new Node(1, 6,
                                new Node(7, 5, new Node(1, 4,
                                        new Node(6, 3, new Node(0, 2,
                                                new Node(5, 1, null))))))))))))))))))))))), 23);
        assertEquals(input, expected);
    }

    @Test
    public void testMult_08() {
        LinkedList_ linkedList = FileProcessor.stringToLinkedList("115167610");
        LinkedList_ linkedList2 = FileProcessor.stringToLinkedList("888888884");
        LinkedList_ expected = Calculator.reverseLinkedList(FileProcessor.stringToLinkedList("102371208325847240"));
        LinkedList_ input = (Calculator.multiply(linkedList, linkedList2));
        assertEquals(input, expected);
    }

    @Test
    public void testMult_09() {
        LinkedList_ linkedList = FileProcessor.stringToLinkedList("102371208312031031");
        LinkedList_ linkedList2 = FileProcessor.stringToLinkedList("89217532146056971039");
        LinkedList_ expected = Calculator.reverseLinkedList(FileProcessor.stringToLinkedList("9133306568409323100893244370036311209"));
        assertEquals(Calculator.multiply(linkedList, linkedList2), expected);
    }

    @Test
    public void testMult_10() {
        LinkedList_ linkedList = FileProcessor.stringToLinkedList("89217532146056971039");
        LinkedList_ linkedList2 = FileProcessor.stringToLinkedList("102371208312031031");
        LinkedList_ expected = Calculator.reverseLinkedList(FileProcessor.stringToLinkedList("9133306568409323100893244370036311209"));
        assertEquals(Calculator.multiply(linkedList, linkedList2), expected);
    }

    @Test
    public void testFlip_01() {
        LinkedList_ input = new LinkedList_(new Node(5, 3, new Node(3, 2, new Node(1, 1, null))), 3);
        LinkedList_ expected = new LinkedList_(new Node(1, 3, new Node(3, 2, new Node(5, 1, null))), 3);
        assertEquals(Calculator.reverseLinkedList(input), expected);
    }

    @Test
    public void testFlip_02() {
        LinkedList_ input = new LinkedList_(new Node(3, 2, new Node(1, 1, null)), 2);
        LinkedList_ expected = new LinkedList_(new Node(1, 2, new Node(3, 1, null)), 2);
        assertEquals(Calculator.reverseLinkedList(input), expected);

    }
    @Test
    public void testFlip_03() {
        LinkedList_ input = new LinkedList_(new Node(1, 1, null), 1);
        LinkedList_ expected = new LinkedList_(new Node(1, 1, null), 1);
        assertEquals(Calculator.reverseLinkedList(input), expected);

    }

}