import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashMap;

public class hash {
    private static final String username = "root";
    private static final String password = "@#aditya2006";
    private static final String url = "jdbc:mysql://localhost:3306/store";

    private static final HashMap<Character, Character> hashMapping = new HashMap<>();

    static {
        // Define the custom hashing mapping
        hashMapping.put('1', 'z');
        hashMapping.put('2', 'y');
        hashMapping.put('3', 'x');
        hashMapping.put('4', 'w');
        hashMapping.put('5', 'v');
        hashMapping.put('6', 'u');
        hashMapping.put('7', 't');
        hashMapping.put('8', 's');
        hashMapping.put('9', 'r');
    }

    // Method to hash a pin using the custom hashing method
    public static String hashPin(String pin) {
        StringBuilder hashedPin = new StringBuilder();
        for (int i = 0; i < pin.length(); i++) {
            char digit = pin.charAt(i);
            if (hashMapping.containsKey(digit)) {
                hashedPin.append(hashMapping.get(digit));
            } else {
                // If the digit is not in the mapping, keep it as is
                hashedPin.append(digit);
            }
        }
        return hashedPin.toString();
    }

    // Method to store a user's hashed pin in the database
    public static void storeHashedPin(String username, String pin) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String hashedPin = hashPin(pin);

            String query = "INSERT INTO users (username, hashed_pin) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, hashedPin);
                pstmt.executeUpdate();
                System.out.println("Hashed pin stored successfully for user: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        String username = "exampleUser";
        String pin = "123456789";

        // Store hashed pin
        storeHashedPin(username, pin);
    }
}
