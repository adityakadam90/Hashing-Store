import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class hash {
    private static final String password = "@#aditya2006";
    private static final String username = "root";
    private static final String url = "jdbc:mysql://localhost:3306/mystore";

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("error at Drivers Loading........!");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println();
        try {
            Connection cn = DriverManager.getConnection(url, username, password);
            System.out.println();
            while (true) {
                System.out.print("enter your choice.\n1.add password.\n2.retrive password.\n3.exit\nenter : ");
                int ch = sc.nextInt();
                System.out.println();
                switch (ch) {
                    case 1:
                        addPassword(cn, sc);
                        break;
                    default:
                        return;
                }
            }
        } catch (SQLException e) {
            System.out.println("error at Connections........!");
        }
    }

    public static void addPassword(Connection cn, Scanner sc) {
        System.out.println();
        System.out.print("enter your username : ");
        String username = sc.next();
        System.out.print("enter your password : ");
        String password = sc.next();
        System.out.print("if you write username description (write info/n) : ");
        String des = sc.next();
        String hashedPassword = HashPassword(password);
        String query = "insert into users values(?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,hashedPassword);
            ps.setString(3,des);
            int afr = ps.executeUpdate();
            if(afr > 0) {
                System.out.println();
                System.out.println("Process succesfully Done..using Hashing Method.........");
                System.out.println();
            }else {
                System.out.println();
                System.out.println("some error occur......!");
                System.out.println();
            }
        }catch (SQLException e) {
            System.out.println("error at add password........!");
        }
    }

    public static String HashPassword(String password) {
        System.out.println();
        final HashMap<Character, Character> hashMapping = new HashMap<>();


        // Define the custom hashing mapping
        hashMapping.put('a', 'z');
        hashMapping.put('b', 'y');
        hashMapping.put('c', 'x');
        hashMapping.put('d', 'w');
        hashMapping.put('e', 'v');
        hashMapping.put('f', 'u');
        hashMapping.put('g', 't');
        hashMapping.put('h', 's');
        hashMapping.put('i', 'r');
        hashMapping.put('j', 'q');
        hashMapping.put('k', 'p');
        hashMapping.put('l', 'o');
        hashMapping.put('m', 'n');
        hashMapping.put('n', 'm');
        hashMapping.put('o', 'l');
        hashMapping.put('p', 'k');
        hashMapping.put('q', 'j');
        hashMapping.put('r', 'i');
        hashMapping.put('s', 'h');
        hashMapping.put('t', 'g');
        hashMapping.put('u', 'f');
        hashMapping.put('v', 'e');
        hashMapping.put('w', 'd');
        hashMapping.put('x', 'c');
        hashMapping.put('y', 'b');
        hashMapping.put('z', 'a');
        hashMapping.put('0', 'a');
        hashMapping.put('1', 'b');
        hashMapping.put('2', 'c');
        hashMapping.put('3', 'd');
        hashMapping.put('4', 'e');
        hashMapping.put('5', 'f');
        hashMapping.put('6', 'g');
        hashMapping.put('7', 'h');
        hashMapping.put('8', 'i');
        hashMapping.put('9', 'j');

        // Method to hash a pin using the custom hashing method

        StringBuilder hashedPin = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char digit = password.charAt(i);
            if (hashMapping.containsKey(digit)) {
                hashedPin.append(hashMapping.get(digit));
            } else {
                // If the digit is not in the mapping, keep it as is
                hashedPin.append(digit);
            }
        }
        return hashedPin.toString();
    }
}
