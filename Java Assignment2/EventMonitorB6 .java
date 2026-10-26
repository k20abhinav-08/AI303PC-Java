import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.IO;

class EventMonitorB6 {
class EventMonitorB6 {

    JFrame frame;
    JTextArea eventLog;
    JLabel wordCountLabel;

    JButton startButton;
    JButton pauseButton;
    JButton stopButton;

    int startClicks = 0;
    int pauseClicks = 0;
    int stopClicks = 0;

    int wordCount = 0;

    JCheckBoxMenuItem editableItem;
    JRadioButtonMenuItem smallFontItem;
    JRadioButtonMenuItem largeFontItem;

    void main() {
        SwingUtilities.invokeLater(() -> createWindow());
    }

    void createWindow() {

        frame = new JFrame("Event Monitor");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Problem Statement:
        // Create a Swing application which demonstrates ActionEvent,
        // MouseEvent, KeyEvent and ItemEvent along with menus.
        // Every received event is displayed inside a JTextArea.

        eventLog = new JTextArea();
        eventLog.setLineWrap(true);
        eventLog.setWrapStyleWord(true);
        eventLog.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(eventLog);

        JPanel buttonPanel = new JPanel();

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);

        startButton.addActionListener(new ButtonHandler());
        pauseButton.addActionListener(new ButtonHandler());
        stopButton.addActionListener(new ButtonHandler());

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        wordCountLabel = new JLabel("Typed words: 0");
        frame.add(wordCountLabel, BorderLayout.SOUTH);

        addMouseEvents();
        addKeyEvents();
        createMenuBar();
        createPopupMenu();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void addMouseEvents() {

        eventLog.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent event) {

                try {
                    String message = "MouseEvent: Clicked at ("
                            + event.getX() + ", " + event.getY()
                            + ") with button" + event.getButton()
                            + ", clicks = " + event.getClickCount();

                    log(message);

                    if (event.getButton() == MouseEvent.BUTTON3) {
                        // Popup menu can also be displayed using
                        // setComponentPopupMenu().
                        eventLog.getComponentPopupMenu()
                                .show(eventLog, event.getX(), event.getY());
                    }

                } catch (Exception exception) {
                    IO.println("Mouse event error: "
                            + exception.getMessage());
                }
            }

            public void mousePressed(MouseEvent event) {

                try {
                    log("MouseEvent: Pressed at ("
                            + event.getX() + ", " + event.getY() + ")");

                } catch (Exception exception) {
                    IO.println("Mouse press error: "
                            + exception.getMessage());
                }
            }

            public void mouseReleased(MouseEvent event) {

                try {
                    log("MouseEvent: Released at ("
                            + event.getX() + ", " + event.getY() + ")");

                } catch (Exception exception) {
                    IO.println("Mouse release error: "
                            + exception.getMessage());
                }
            }
        });

        eventLog.addMouseMotionListener(new MouseAdapter() {

            public void mouseDragged(MouseEvent event) {

                try {
                    log("MouseEvent: Dragging at ("
                            + event.getX() + ", " + event.getY() + ")");

                } catch (Exception exception) {
                    IO.println("Mouse dragging error: "
                            + exception.getMessage());
                }
            }

            public void mouseMoved(MouseEvent event) {

                // Mouse movement is detected but not continuously logged
                // because it would fill the event log very quickly.
            }
        });
    }

    void addKeyEvents() {

        eventLog.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent event) {

                try {
                    char character = event.getKeyChar();

                    log("KeyEvent: Typed '" + character
                            + "' (code = " + event.getKeyCode() + ")");

                    updateWordCount();

                } catch (Exception exception) {
                    IO.println("Key typed error: "
                            + exception.getMessage());
                }
            }

            public void keyPressed(KeyEvent event) {

                try {
                    log("KeyEvent: Pressed '" + event.getKeyChar()
                            + "' (code = " + event.getKeyCode()
                            + ", text = "
                            + KeyEvent.getKeyText(event.getKeyCode()) + ")");

                } catch (Exception exception) {
                    IO.println("Key pressed error: "
                            + exception.getMessage());
                }
            }

            public void keyReleased(KeyEvent event) {

                try {
                    log("KeyEvent: Released '" + event.getKeyChar()
                            + "' (code = " + event.getKeyCode()
                            + ", text = "
                            + KeyEvent.getKeyText(event.getKeyCode()) + ")");

                } catch (Exception exception) {
                    IO.println("Key released error: "
                            + exception.getMessage());
                }
            }
        });
    }

    void updateWordCount() {

        try {
            String text = eventLog.getText().trim();

            if (text.isEmpty()) {
                wordCount = 0;
            } else {
                wordCount = text.split("\\s+").length;
            }

            wordCountLabel.setText("Typed words: " + wordCount);

        } catch (Exception exception) {
            IO.println("Word count error: " + exception.getMessage());
        }
    }

    void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.addActionListener(event -> {

            try {
                eventLog.setText("");
                wordCount = 0;
                wordCountLabel.setText("Typed words: 0");
                log("ActionEvent: New selected");

            } catch (Exception exception) {
                IO.println("New operation error: "
                        + exception.getMessage());
            }
        });

        exitItem.addActionListener(event -> {

            try {
                log("ActionEvent: Exit selected");
                frame.dispose();

            } catch (Exception exception) {
                IO.println("Exit operation error: "
                        + exception.getMessage());
            }
        });

        fileMenu.add(newItem);
        fileMenu.add(exitItem);

        editableItem = new JCheckBoxMenuItem("Editable");
        editableItem.setSelected(true);

        editableItem.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent event) {

                try {
                    boolean editable =
                            event.getStateChange() == ItemEvent.SELECTED;

                    eventLog.setEditable(editable);

                    String state = editable ? "checked" : "unchecked";

                    log("ItemEvent: Editable " + state);

                } catch (Exception exception) {
                    IO.println("Editable item error: "
                            + exception.getMessage());
                }
            }
        });

        smallFontItem = new JRadioButtonMenuItem("Small");
        largeFontItem = new JRadioButtonMenuItem("Large");

        ButtonGroup fontGroup = new ButtonGroup();
        fontGroup.add(smallFontItem);
        fontGroup.add(largeFontItem);

        smallFontItem.setSelected(true);

        ChangeListener fontListener = new ChangeListener() {

            public void stateChanged(ChangeEvent event) {

                try {
                    if (smallFontItem.isSelected()) {
                        eventLog.setFont(
                                new Font("Arial", Font.PLAIN, 14));

                        log("ChangeEvent: Small font selected");
                    }

                    if (largeFontItem.isSelected()) {
                        eventLog.setFont(
                                new Font("Arial", Font.PLAIN, 20));

                        log("ChangeEvent: Large font selected");
                    }

                } catch (Exception exception) {
                    IO.println("Font change error: "
                            + exception.getMessage());
                }
            }
        };

        smallFontItem.addChangeListener(fontListener);
        largeFontItem.addChangeListener(fontListener);

        editMenu.add(editableItem);
        editMenu.add(smallFontItem);
        editMenu.add(largeFontItem);
        editMenu.addSeparator();

        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(event -> {

            try {
                JOptionPane.showMessageDialog(
                        frame,
                        "Event Monitor\nSwing Event Handling Mini Application",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception exception) {
                IO.println("Help menu error: "
                        + exception.getMessage());
            }
        });

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
    }

    void createPopupMenu() {

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem clearItem = new JMenuItem("Clear Log");
        JMenuItem copyItem = new JMenuItem("Copy");

        clearItem.addActionListener(event -> {

            try {
                eventLog.setText("");
                wordCount = 0;
                wordCountLabel.setText("Typed words: 0");

                log("ActionEvent: Log cleared");

            } catch (Exception exception) {
                IO.println("Clear operation error: "
                        + exception.getMessage());
            }
        });

        copyItem.addActionListener(event -> {

            try {
                String selectedText = eventLog.getSelectedText();

                if (selectedText != null) {
                    StringSelection selection =
                            new StringSelection(selectedText);

                    Toolkit.getDefaultToolkit()
                            .getSystemClipboard()
                            .setContents(selection, null);

                    log("ActionEvent: Selected text copied");
                } else {
                    log("ActionEvent: Copy requested but no text selected");
                }

            } catch (Exception exception) {
                IO.println("Copy operation error: "
                        + exception.getMessage());
            }
        });

        popupMenu.add(clearItem);
        popupMenu.add(copyItem);

        eventLog.setComponentPopupMenu(popupMenu);
    }

    void log(String message) {

        eventLog.append(message + "\n");
        eventLog.setCaretPosition(eventLog.getDocument().getLength());

        IO.println(message);
    }

    class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            try {
                String command = event.getActionCommand();

                if (command.equals("Start")) {
                    startClicks++;

                    log("ActionEvent: Start clicked (Count = "
                            + startClicks + ", Command = "
                            + command + ")");
                }

                else if (command.equals("Pause")) {
                    pauseClicks++;

                    log("ActionEvent: Pause clicked (Count = "
                            + pauseClicks + ", Command = "
                            + command + ")");
                }

                else if (command.equals("Stop")) {
                    stopClicks++;

                    log("ActionEvent: Stop clicked (Count = "
                            + stopClicks + ", Command = "
                            + command + ")");
                }

            } catch (Exception exception) {
                IO.println("Button action error: "
                        + exception.getMessage());
            }
        }
    }

    /*
     * ActionEvent vs ItemEvent:
     *
     * ActionEvent occurs when a command-type action is performed,
     * such as clicking a JButton or selecting a JMenuItem.
     *
     * ItemEvent occurs when the state of an item changes,
     * such as checking or unchecking a JCheckBoxMenuItem.
     *
     * To detect a double click, MouseListener can be used.
     * The getClickCount() method of MouseEvent tells us
     * how many times the mouse was clicked.
     *
     * Collection/API used:
     * Swing components such as JFrame, JTextArea, JButton,
     * JMenuBar, JPopupMenu and event listener APIs are used.
     * ButtonGroup is used to make the font-size menu items
     * mutually exclusive.
     */
}