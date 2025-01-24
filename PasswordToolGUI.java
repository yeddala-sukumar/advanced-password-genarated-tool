import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PasswordToolGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Password Tool");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Password Generator & Strength Checker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel for options and output
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        frame.add(panel, BorderLayout.CENTER);

        // Input fields and options
        JLabel lengthLabel = new JLabel("Password Length:");
        JTextField lengthField = new JTextField("12");
        JLabel includeNumbersLabel = new JLabel("Include Numbers:");
        JCheckBox includeNumbersCheck = new JCheckBox("", true);
        JLabel includeSymbolsLabel = new JLabel("Include Symbols:");
        JCheckBox includeSymbolsCheck = new JCheckBox("", true);
        JLabel passwordLabel = new JLabel("Generated Password:");
        JTextField passwordField = new JTextField();
        passwordField.setEditable(false);
        JLabel strengthLabel = new JLabel("Password Strength:");
        JTextField strengthField = new JTextField();
        strengthField.setEditable(false);

        // Buttons
        JButton generateButton = new JButton("Generate Password");
        JButton checkStrengthButton = new JButton("Check Strength");

        // Add components to the panel
        panel.add(lengthLabel);
        panel.add(lengthField);
        panel.add(includeNumbersLabel);
        panel.add(includeNumbersCheck);
        panel.add(includeSymbolsLabel);
        panel.add(includeSymbolsCheck);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(strengthLabel);
        panel.add(strengthField);
        panel.add(generateButton);
        panel.add(checkStrengthButton);

        // Action listeners
        generateButton.addActionListener(e -> {
            try {
                int length = Integer.parseInt(lengthField.getText());
                boolean includeNumbers = includeNumbersCheck.isSelected();
                boolean includeSymbols = includeSymbolsCheck.isSelected();
                String password = generatePassword(length, includeNumbers, includeSymbols);
                passwordField.setText(password);
                strengthField.setText(checkPasswordStrength(password));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for password length.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        checkStrengthButton.addActionListener(e -> {
            String password = passwordField.getText();
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please generate or enter a password first.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                strengthField.setText(checkPasswordStrength(password));
            }
        });

        // Add a footer label
        JLabel footerLabel = new JLabel("Developed by yeddala sukumar", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        frame.add(footerLabel, BorderLayout.SOUTH);

        // Set the frame visible
        frame.setVisible(true);
    }

    // Method to generate a random password
    private static String generatePassword(int length, boolean includeNumbers, boolean includeSymbols) {
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_+=<>?";

        String characterSet = lowerCaseLetters + upperCaseLetters;
        if (includeNumbers) characterSet += numbers;
        if (includeSymbols) characterSet += symbols;

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }

        return password.toString();
    }

    // Method to check password strength
    private static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSymbol = password.matches(".*[!@#$%^&*()-_=+<>?].*");

        int strengthScore = 0;
        if (length >= 8) strengthScore++;
        if (hasLower) strengthScore++;
        if (hasUpper) strengthScore++;
        if (hasNumber) strengthScore++;
        if (hasSymbol) strengthScore++;

        if (strengthScore == 5) return "Strong";
        if (strengthScore >= 3) return "Medium";
        return "Weak";
    }
}
