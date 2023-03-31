import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerApp extends JFrame implements ActionListener {
    private JButton startButton, stopButton, restartButton, chooseColorButton;
    private JLabel timerLabel;
    private Timer timer;
    private Color color;

    public TimerApp() {
        // init
        super("Timer App");

        timerLabel = new JLabel();
        timerLabel.setFont(new Font("Arial", Font.BOLD, 60));

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        chooseColorButton = new JButton("Choose Color");
        chooseColorButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(chooseColorButton);
        add(timerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        color = Color.BLACK;
        timerLabel.setForeground(color);

        // update
        timer = new Timer(1000, this);

        setSize(600, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer.start();
        } else if (e.getSource() == stopButton) {
            timer.stop();
        } else if (e.getSource() == restartButton) {
            timer.stop();
            timerLabel.setText(getCurrentTime());
            timer.start();
        } else if (e.getSource() == chooseColorButton) {
            color = JColorChooser.showDialog(null, "Choose Color", color);
            if (color != null) {
                timerLabel.setForeground(color);
            }
        } else {
            timerLabel.setText(getCurrentTime());
            if (timer.isRunning()) {
                if (timerLabel.getForeground() == color) {
                    timerLabel.setForeground(Color.BLACK);
                } else {
                    timerLabel.setForeground(color);
                }
            }
        }
    }

    private String getCurrentTime() {
        return String.format("%tT", System.currentTimeMillis());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerApp timerApp = new TimerApp();
                timerApp.setVisible(true);
                frame.dispose();
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(settingsButton);
        buttonPanel.add(closeButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
