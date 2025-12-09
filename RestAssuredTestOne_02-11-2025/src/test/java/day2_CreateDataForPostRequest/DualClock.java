package day2_CreateDataForPostRequest;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DualClock {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private static LocalTime fastClockTime = LocalTime.now();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Dual Live Clock");
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel normalClockLabel = new JLabel("Normal Clock", SwingConstants.CENTER);
        JLabel fastClockLabel = new JLabel("1.25x Fast Clock", SwingConstants.CENTER);

        normalClockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        fastClockLabel.setFont(new Font("Arial", Font.BOLD, 24));

        frame.add(normalClockLabel);
        frame.add(fastClockLabel);

        frame.setVisible(true);

        // ✅ Normal Clock Timer (real-time)
        Timer normalTimer = new Timer(1000, e -> {
            LocalTime now = LocalTime.now();
            normalClockLabel.setText("Normal: " + now.format(formatter));
        });

        // ✅ 1.25x Speed Clock Timer
        Timer fastTimer = new Timer(1000, e -> {
            fastClockTime = fastClockTime.plusNanos(1_250_000_000L); // 1.25 seconds
            fastClockLabel.setText("Fast:     " + fastClockTime.format(formatter));
        });

        normalTimer.start();
        fastTimer.start();
    }
}
