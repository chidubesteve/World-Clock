import javax.swing.*; // Importing the necessary classes from the javax.swing package for GUI components
import java.awt.*; // Importing the necessary classes from the java.awt package for GUI layout
import java.awt.event.ActionEvent; // Importing the necessary class from the java.awt.event package for handling events
import java.awt.event.ActionListener;// Importing the necessary class from the java.awt.event package for implementing event listeners
import java.text.SimpleDateFormat; // Importing the necessary class from the java.text package for formatting dates
import java.util.Date; // Importing the necessary class from the java.util package for representing dates
import java.util.TimeZone;; // Importing the necessary class from the java.util package for representing time zones

public class DigitalClock extends JFrame {
    private JLabel localClockLabel;// Label to display the local time
    private JLabel generalLabel;// Label to display the selected time zone
    private JButton[] timeZoneButtons;// Array of buttons for selecting different time zones
    private String[] internationalLocations;// Array of international locations
    private String[] timeZoneIDs; // Array of time zone IDs

public DigitalClock() {
    setTitle("World Clock"); // Set the title of the window
    setSize(400, 400); // Set the size of the window
    setDefaultCloseOperation(EXIT_ON_CLOSE); // Set the default close operation

    localClockLabel = new JLabel(); // Create a new label for the local time
    localClockLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Set the font of the label
    localClockLabel.setHorizontalAlignment(JLabel.CENTER); // Set the horizontal alignment of the label

    add(localClockLabel, BorderLayout.NORTH); // Add the label to the north region of the window

    generalLabel = new JLabel("Selected Timezone: "); // Create a new label for the selected time zone
    generalLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Set the font of the label
    generalLabel.setHorizontalAlignment(JLabel.CENTER); // Set the horizontal alignment of the label
    add(generalLabel, BorderLayout.CENTER); // Add the label to the center region of the window

    internationalLocations = new String[]{"New York", "London", "Tokyo", "Sydney"}; // Initialize the array of international locations
    timeZoneButtons = new JButton[internationalLocations.length]; // Create an array of buttons for selecting time zones
    timeZoneIDs = new String[]{"America/New_York", "Europe/London", "Asia/Tokyo", "Australia/Sydney"}; // Initialize the array of time zone IDs

    JPanel buttonPanel = new JPanel(new GridLayout(internationalLocations.length, 1)); // Create a panel for the buttons with a grid layout
    add(buttonPanel, BorderLayout.SOUTH); // Add the panel to the south region of the window

    for (int i = 0; i < internationalLocations.length; i++) {
        final int index = i; // To capture the current index in the action listener

        timeZoneButtons[i] = new JButton(internationalLocations[i]); // Create a button for each international location
        timeZoneButtons[i].setFont(new Font("Arial", Font.PLAIN, 16)); // Set the font of the button
        timeZoneButtons[i].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateGeneralLabel(internationalLocations[index], timeZoneIDs[index]); // Update the general label when a button is clicked
            }
        });

        buttonPanel.add(timeZoneButtons[i]); // Add the button to the button panel
    }

    Timer timer = new Timer(1000, e -> updateLocalClock()); // Create a timer to update the local clock every second
    timer.start(); // Start the timer
}

private void updateLocalClock() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Create a date format for displaying the time
    TimeZone localTimeZone = TimeZone.getDefault(); // Get the default time zone of the system
    dateFormat.setTimeZone(localTimeZone); // Set the time zone of the date format

    String localTime = dateFormat.format(new Date()); // Format the current date and time
    localClockLabel.setText("Local Time (" + localTimeZone.getID() + "): " + localTime); // Update the local clock label
}

private void updateGeneralLabel(String selectedLocation, String timeZoneID) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Create a date format for displaying the time
    TimeZone selectedTimeZone = TimeZone.getTimeZone(timeZoneID); // Get the selected time zone
    dateFormat.setTimeZone(selectedTimeZone); // Set the time zone of the date format

    String selectedTime = dateFormat.format(new Date()); // Format the current date and time in the selected time zone
    generalLabel.setText("Selected Timezone (" + selectedLocation + "): " + selectedTime); // Update the general label with the selected time
}
}