package game.functionalities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import functionalities.ui.UI;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UITest {

    @Test
    void messagePrintSuccess() {
        // Arrange: Set up a stream to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Act: Call the printErrorMessage method with a sample message
        String testMessage = "An error occurred!";
        UI.printErrorMessage(testMessage); // Assuming UI is your class containing the method

        // Assert: Verify that the message printed matches the expected output
        assertEquals(testMessage + "\n", outputStream.toString()); // Includes newline character
    }
}

