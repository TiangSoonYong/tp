package functions.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import game.Game;

/**
 * A class contains Junit test cases for UI class
 */
public class UITest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void readInput_validInput_returnsLowercase() {
        String simulatedInput = "HELLO\n";
        testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        UI.resetScanner(System.in);
        String result = UI.readInput();
        assertEquals("hello", result);
    }

    @Test
    public void readInput_emptyInput_returnsEmptyString() {
        testIn = new ByteArrayInputStream("\n".getBytes());
        System.setIn(testIn);

        UI.resetScanner(System.in);
        String result = UI.readInput();
        assertEquals("", result);
    }

    @Test
    public void printErrorMessage_printsCorrectly() {
        UI.printErrorMessage("Error!");
        assertTrue(testOut.toString().contains("Error!"));
    }

    @Test
    public void printErrorMessage_printsNothingOnNull() {
        UI.printErrorMessage(null);
        assertTrue(testOut.toString().contains("null"));
    }

    @Test
    public void printMessage_printsCorrectly() {
        UI.printMessage("Hello world");
        assertTrue(testOut.toString().contains("Hello world"));
    }

    @Test
    public void printMessage_emptyMessage_printsNewLine() {
        UI.printMessage("");
        assertTrue(testOut.toString().contains("\n"));
    }

    @Test
    public void printWelcomeMessage_outputsLogoAndIntro() {
        UI.printWelcomeMessage();
        String output = testOut.toString();
        assertTrue(output.contains("Welcome to"));
        assertTrue(output.contains("RPG"));
    }

    @Test
    public void printExitMessage_displaysExitLine() {
        UI.printExitMessage();
        assertTrue(testOut.toString().contains("Leaving so soon"));
    }

    @Test
    public void printDeathMessage_displaysDeathMessage() {
        UI.printDeathMessage();
        assertTrue(testOut.toString().contains("you've died"));
    }

    @Test
    public void promptSaveFile_validInput_returnsInput() {
        String simulatedInput = "2\n\n";
        testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);
        UI.resetScanner(System.in);
        int result = UI.promptSaveFile();
        assertEquals(2, result);
    }

    @Test
    public void promptSaveFile_emptyInput_returnsEmpty() {
        String simulatedInput = "\n\n\n\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        UI.resetScanner(System.in);

        assertThrows(NumberFormatException.class, () -> {
            UI.promptSaveFile();
        });
    }

    @Test
    public void printOptions_displaysAllOptions() {
        UI.printOptions();
        String output = testOut.toString();
        assertTrue(output.contains("1. Start New Game"));
        assertTrue(output.contains("2. Load Game"));
        assertTrue(output.contains("3. Exit"));
    }

    /*@Test
    public void showContinueScreen_displaysPlayerAndWave() {
        String simulatedInput = "TestHero\ncontinue\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);
        UI.resetScanner(testIn); // reset Scanner BEFORE using input

        Game game = new Game(); // now createNewPlayer() can read from input

        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

        UI.showContinueScreen(game);

        String output = testOut.toString();
        assertTrue(output.contains("🌊 Current Wave: 1"), "Output: \n" + output);
    }*/

}
