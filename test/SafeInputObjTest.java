import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SafeInputObjTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    InputStream sysInBackup;

    void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void setUp() {
        sysInBackup = System.in; // backup System.in to restore it later
    }

    @Test
    void getNonZeroLenString() {
        provideInput("\nTest\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        String r = safeInputObj.getNonZeroLenString("Test Prompt");
        assertEquals("Test", r);
        assertEquals("\nTest Prompt: \nTest Prompt: ", outContent.toString());
    }

    @Test
    void getRangedInt() {
        provideInput("\nTest\n11\n4.5\n5\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        int r = safeInputObj.getRangedInt("Number 1-10", 1, 10);
        assertEquals(5, r);
        String expected = """
                
                Number 1-10[1-10]: You must enter an int:\s
                
                Number 1-10[1-10]: You must enter an int: Test
                
                Number 1-10[1-10]:\s
                Number is out of range [1-10]: 11
                
                Number 1-10[1-10]: You must enter an int: 4.5
                
                Number 1-10[1-10]:\s""";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void getInt() {
        provideInput("\nTest\n4.5\n5\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        int r = safeInputObj.getInt("Number");
        assertEquals(5, r);
        String expected = """
                
                Number: You must enter an int:\s
                
                Number: You must enter an int: Test
                
                Number: You must enter an int: 4.5
                
                Number:\s""";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void getRangedDouble() {
        provideInput("\nTest\n11\n4.5\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        double r = safeInputObj.getRangedDouble("Number 1-10", 1, 10);
        assertEquals(4.5, r);
        String expected = """
                
                Number 1-10[1-10]: You must enter a double:\s
                
                Number 1-10[1-10]: You must enter a double: Test
                
                Number 1-10[1-10]:\s
                Number is out of range [1-10]: 11.0
                
                Number 1-10[1-10]:\s""";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void getDouble() {
        provideInput("\nTest\n4.5\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        double r = safeInputObj.getDouble("Number");
        assertEquals(4.5, r);
        String expected = """
                
                Number: You must enter a double:\s
                
                Number: You must enter a double: Test
                
                Number:\s""";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void getYNConfirm() {
        provideInput("\nTest\ny\nn\nY\nN\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        boolean r = safeInputObj.getYNConfirm("Yes or No");
        assertTrue(r);
        String expected = """
                
                Yes or No [Y/N] You must answere [Y/N]!\s
                
                Yes or No [Y/N] You must answere [Y/N]! Test
                
                Yes or No [Y/N]\s""";
        assertEquals(expected, outContent.toString());
        r = safeInputObj.getYNConfirm("Yes or No");
        assertFalse(r);
        r = safeInputObj.getYNConfirm("Yes or No");
        assertTrue(r);
        r = safeInputObj.getYNConfirm("Yes or No");
        assertFalse(r);
    }

    @Test
    void getRegExString() {
        provideInput("\n12345\nabcde\n");
        SafeInputObj safeInputObj = new SafeInputObj();
        String r = safeInputObj.getRegExString("Enter a 5-digit number", "\\d{5}");
        assertEquals("12345", r);
        String expected = """
                
                Enter a 5-digit number:\s
                 must match the pattern \\d{5}
                Try again!
                
                Enter a 5-digit number:\s""";
        assertEquals(expected, outContent.toString());

    }

    @AfterEach
    void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(originalOut);
    }
}