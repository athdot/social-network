import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Summer 2021</p>
 */
public class RunLocalTest {
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful())
        {
            System.out.println("Excellent - Test ran successfully");
        }
        else
        {
            for (Failure failure : result.getFailures())
            {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        @Test(timeout = 1000)
        public void testLogin() {
            try {
                // Set the input
                //this would never be a username or password
                String input = "1\nwrongUsername!@#!@#\nwrongPassword!@#!@#\n";

                // Pair the input with the expected result
                String expected = "Username/Password is Wrong\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| LOGIN PAGE                                       |\n" +
                        "| 1. Sign In                                       |\n" +
                        "| 2. Create New Account                            |\n" +
                        "+--------------------------------------------------+";

                // Runs the program with the input values
                receiveInput(input);
                Application.main(new String[0]);

                // Retrieves the output from the program
                String output = getOutput();

                // Trims the output and verifies it is correct.
                output = output.replace("\r\n", "\n");
                assertEquals("Make sure your program handles incorrect logins!",
                        expected.trim(), output.trim());

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }


    }
}
