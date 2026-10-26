import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IO;

class GUIB5 {

    JFrame frame;

    JTextField rollNumberField;
    JTextField nameField;

    JRadioButton maleButton;
    JRadioButton femaleButton;
    ButtonGroup genderGroup;

    JCheckBox javaCheckBox;
    JCheckBox pythonCheckBox;
    JCheckBox dbmsCheckBox;

    JComboBox<String> branchBox;
    JComboBox<String> cityBox;

    JTextArea addressArea;
    JTextArea outputArea;

    JButton submitButton;
    JButton resetButton;
    JButton exitButton;

    DefaultListModel<String> recordListModel;
    JList<String> recordList;

    /*
     * Problem Statement:
     * Design a Swing based Student Registration Form.
     * The form accepts roll number, name, gender, branch,
     * subjects, city and address. The entered details are
     * displayed when the Submit button is clicked.
     *
     * Swing components are called lightweight components because
     * most of their appearance and behavior is implemented in Java
     * rather than depending directly on the operating system's
     * native GUI components.
     */

    void main() {
        SwingUtilities.invokeLater(() -> createForm());
    }

    void createForm() {

        frame = new JFrame("B5 - Student Registration Form");
        frame.setSize(850, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel registrationPanel = createRegistrationPanel();

        JPanel recordsPanel = createRecordsPanel();

        tabbedPane.addTab("Registration", registrationPanel);
        tabbedPane.addTab("Submitted Records", recordsPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    JPanel createRegistrationPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        /*
         * BorderLayout divides the container into five regions:
         * NORTH, SOUTH, EAST, WEST and CENTER.
         * Here it is used to place the logo at the top and
         * the registration form in the center.
         */

        JPanel logoPanel = new JPanel();

        JLabel logoLabel;

        try {
            ImageIcon logo = new ImageIcon("logo.png");

            logoLabel = new JLabel(logo);
            logoLabel.setText("Student Registration");

        } catch (Exception exception) {

            logoLabel = new JLabel("STUDENT REGISTRATION");
        }

        logoPanel.add(logoLabel);

        mainPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());

        /*
         * GridBagLayout allows components to be arranged in rows
         * and columns while giving control over their position.
         */

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel rollLabel = new JLabel("Roll No");
        JLabel nameLabel = new JLabel("Name");
        JLabel genderLabel = new JLabel("Gender");
        JLabel branchLabel = new JLabel("Branch");
        JLabel subjectLabel = new JLabel("Subjects");
        JLabel cityLabel = new JLabel("City");
        JLabel addressLabel = new JLabel("Address");

        rollNumberField = new JTextField(20);
        nameField = new JTextField(20);

        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");

        genderGroup = new ButtonGroup();

        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        javaCheckBox = new JCheckBox("Java");
        pythonCheckBox = new JCheckBox("Python");
        dbmsCheckBox = new JCheckBox("DBMS");

        JPanel subjectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        subjectPanel.add(javaCheckBox);
        subjectPanel.add(pythonCheckBox);
        subjectPanel.add(dbmsCheckBox);

        branchBox = new JComboBox<>();

        branchBox.addItem("Select Branch");
        branchBox.addItem("CSE");
        branchBox.addItem("ECE");
        branchBox.addItem("EEE");
        branchBox.addItem("MECH");
        branchBox.addItem("CIVIL");

        cityBox = new JComboBox<>();

        cityBox.addItem("Select City");
        cityBox.addItem("Hyderabad");
        cityBox.addItem("Bangalore");
        cityBox.addItem("Chennai");
        cityBox.addItem("Mumbai");
        cityBox.addItem("Delhi");

        addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);

        JScrollPane addressScrollPane =
                new JScrollPane(addressArea);

        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JScrollPane outputScrollPane =
                new JScrollPane(outputArea);

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        addComponent(formPanel, rollLabel, gbc, 0, 0);
        addComponent(formPanel, rollNumberField, gbc, 1, 0);

        addComponent(formPanel, nameLabel, gbc, 0, 1);
        addComponent(formPanel, nameField, gbc, 1, 1);

        addComponent(formPanel, genderLabel, gbc, 0, 2);
        addComponent(formPanel, genderPanel, gbc, 1, 2);

        addComponent(formPanel, branchLabel, gbc, 0, 3);
        addComponent(formPanel, branchBox, gbc, 1, 3);

        addComponent(formPanel, subjectLabel, gbc, 0, 4);
        addComponent(formPanel, subjectPanel, gbc, 1, 4);

        addComponent(formPanel, cityLabel, gbc, 0, 5);
        addComponent(formPanel, cityBox, gbc, 1, 5);

        addComponent(formPanel, addressLabel, gbc, 0, 6);
        addComponent(formPanel, addressScrollPane, gbc, 1, 6);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;

        formPanel.add(buttonPanel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());

        outputPanel.add(
                new JLabel("Submitted Details"),
                BorderLayout.NORTH);

        outputPanel.add(outputScrollPane, BorderLayout.CENTER);

        mainPanel.add(outputPanel, BorderLayout.SOUTH);

        addButtonEvents();

        return mainPanel;
    }

    void addComponent(
            JPanel panel,
            Component component,
            GridBagConstraints gbc,
            int x,
            int y) {

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;

        panel.add(component, gbc);
    }

    JPanel createRecordsPanel() {

        JPanel recordsPanel = new JPanel(new BorderLayout());

        /*
         * JTable is used to display previously submitted records
         * in rows and columns.
         */

        String[] columns = {
                "Roll No",
                "Name",
                "Gender",
                "Branch",
                "Subjects",
                "City"
        };

        recordListModel = new DefaultListModel<>();

        recordList = new JList<>(recordListModel);

        /*
         * A JList is used here to keep a simple record history.
         * Each successful registration is added as one item.
         */

        JScrollPane recordScrollPane =
                new JScrollPane(recordList);

        recordsPanel.add(
                new JLabel("Submitted Student Records"),
                BorderLayout.NORTH);

        recordsPanel.add(
                recordScrollPane,
                BorderLayout.CENTER);

        return recordsPanel;
    }

    void addButtonEvents() {

        submitButton.addActionListener(event -> {

            try {
                submitDetails();

            } catch (Exception exception) {

                IO.println(
                        "Submit error: "
                                + exception.getMessage());

                JOptionPane.showMessageDialog(
                        frame,
                        "Unable to submit the details.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        resetButton.addActionListener(event -> {

            try {
                resetForm();

            } catch (Exception exception) {

                IO.println(
                        "Reset error: "
                                + exception.getMessage());
            }
        });

        exitButton.addActionListener(event -> {

            try {

                frame.dispose();

            } catch (Exception exception) {

                IO.println(
                        "Exit error: "
                                + exception.getMessage());
            }
        });
    }

    void submitDetails() {

        String rollNumber =
                rollNumberField.getText().trim();

        String studentName =
                nameField.getText().trim();

        String address =
                addressArea.getText().trim();

        /*
         * Validation 1:
         * Roll number must not be empty.
         */

        if (rollNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter Roll Number");

            rollNumberField.requestFocus();

            return;
        }

        /*
         * Validation 2:
         * Roll number must contain only numbers.
         */

        if (!rollNumber.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Roll Number must be numeric");

            rollNumberField.requestFocus();

            return;
        }

        /*
         * Validation 3:
         * Name must not be empty.
         */

        if (studentName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter Name");

            nameField.requestFocus();

            return;
        }

        /*
         * Validation 4:
         * One gender must be selected.
         */

        if (!maleButton.isSelected()
                && !femaleButton.isSelected()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please select Gender");

            return;
        }

        /*
         * Validation 5:
         * At least one subject must be selected.
         */

        if (!javaCheckBox.isSelected()
                && !pythonCheckBox.isSelected()
                && !dbmsCheckBox.isSelected()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please select at least one Subject");

            return;
        }

        /*
         * Validation 6:
         * Branch must be selected.
         */

        if (branchBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please select Branch");

            return;
        }

        /*
         * Validation 7:
         * City must be selected.
         */

        if (cityBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please select City");

            return;
        }

        /*
         * Validation 8:
         * Address must not be empty.
         */

        if (address.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter Address");

            addressArea.requestFocus();

            return;
        }

        String gender;

        if (maleButton.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }

        String subjects = "";

        if (javaCheckBox.isSelected()) {
            subjects += "Java ";
        }

        if (pythonCheckBox.isSelected()) {
            subjects += "Python ";
        }

        if (dbmsCheckBox.isSelected()) {
            subjects += "DBMS ";
        }

        String branch =
                branchBox.getSelectedItem().toString();

        String city =
                cityBox.getSelectedItem().toString();

        /*
         * Formatting the details before displaying them.
         */

        String details =
                "ROLL NO  : " + rollNumber + "\n"
                        + "NAME     : " + studentName + "\n"
                        + "GENDER   : " + gender + "\n"
                        + "BRANCH   : " + branch + "\n"
                        + "SUBJECTS : " + subjects.trim() + "\n"
                        + "CITY     : " + city + "\n"
                        + "ADDRESS  : " + address;

        outputArea.setText(details);

        /*
         * Add the submitted student to the record list.
         */

        String record =
                rollNumber + " | "
                        + studentName + " | "
                        + gender + " | "
                        + branch + " | "
                        + subjects.trim() + " | "
                        + city;

        recordListModel.addElement(record);

        JOptionPane.showMessageDialog(
                frame,
                "Student registered successfully!");

        IO.println("Student registration successful.");
    }

    void resetForm() {

        rollNumberField.setText("");
        nameField.setText("");

        genderGroup.clearSelection();

        javaCheckBox.setSelected(false);
        pythonCheckBox.setSelected(false);
        dbmsCheckBox.setSelected(false);

        branchBox.setSelectedIndex(0);
        cityBox.setSelectedIndex(0);

        addressArea.setText("");
        outputArea.setText("");

        rollNumberField.requestFocus();

        JOptionPane.showMessageDialog(
                frame,
                "Form has been reset.");

        IO.println("Form reset successfully.");
    }

    /*
     * Sample Input:
     *
     * Roll No  : 101
     * Name     : Ravi
     * Gender   : Male
     * Branch   : CSE
     * Subjects : Java, DBMS
     * City     : Hyderabad
     * Address  : Hyderabad
     *
     * Sample Output:
     *
     * ROLL NO  : 101
     * NAME     : Ravi
     * GENDER   : Male
     * BRANCH   : CSE
     * SUBJECTS : Java DBMS
     * CITY     : Hyderabad
     * ADDRESS  : Hyderabad
     *
     * API / Components Used:
     *
     * JFrame is the main application window.
     * JPanel groups related components.
     * GridBagLayout arranges the registration fields.
     * BorderLayout divides the main panel into regions.
     * JLabel displays labels and the logo.
     * JTextField accepts single-line input.
     * JRadioButton allows one gender to be selected.
     * ButtonGroup makes the gender options mutually exclusive.
     * JCheckBox allows multiple subjects to be selected.
     * JComboBox provides selectable branch and city values.
     * JTextArea accepts and displays multi-line text.
     * JScrollPane provides scrolling for text areas.
     * JButton performs Submit, Reset and Exit actions.
     * JTabbedPane separates registration and submitted records.
     * JList displays the submitted record history.
     * JOptionPane displays validation and success messages.
     *
     * Swing components are called lightweight components because
     * they are mainly implemented in Java and do not depend on
     * separate native operating-system components for their
     * appearance and behavior.
     */
}