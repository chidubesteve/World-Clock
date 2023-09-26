/**
This Java class represents the main class of the application.
It imports the necessary classes from the javax.swing package for GUI components. */
import javax.swing.*;
public class Main {
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
DigitalClock clock = new DigitalClock(); // Create an instance of the DigitalClock class
clock.setVisible(true); // Set the clock window to be visible
});
}
}