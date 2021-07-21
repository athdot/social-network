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
 * A framework to run basic tests on Group 8's Project 4
 *
 * NOTE: Delete profile.csv and post.csv before running this test. This program tests the functionality of the expected
 * app according to the provided requirements on the handout. Persistence of data is tested through login function, and
 * attempts to override existing data. Creating, editing, and deleting accounts, posts, and comments is also tested.
 * Other functions, including searching for user or view all posts, is touched upon.
 *
 * @author Group 8
 * @version 7/21/2021
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
        public void testApp() {
            try {
                // Set the input
                // this is a wrong username and password (assuming it is not stored)
                String input = "1\nwrongUser\nwrongPass\n" + //test invalid login
                        "2\nUser\nPass\n" +
                        "1\n1\nThis is my new bio\n" +
                        "";

                // Pair the input with the expected result
                String expected = "";

                // Runs the program with the input values
                receiveInput(input);
                Application.main(new String[0]);

                // Retrieves the output from the program
                String output = getOutput();

                // Trims the output and verifies it is correct.
                output = output.replace("\r\n", "\n");
                assertEquals("Program does not meet all core",
                        expected.trim(), output.trim());

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}
