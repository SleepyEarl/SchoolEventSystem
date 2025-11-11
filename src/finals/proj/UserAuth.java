package finals.proj;

import java.io.*;

public class UserAuth {

    private static final String FILE_NAME = "users.txt"; // simple text file for storing users

    // Register user (save email + password)
    public static boolean registerUser(String email, String studentId, String department, String password) {
        if (userExists(email)) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(email + "," + studentId + "," + department + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if user exists
    private static boolean userExists(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].trim().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // ignore if file not found
        }
        return false;
    }

    // Login check
    public static boolean loginUser(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String storedEmail = parts[0].trim();
                    String storedPassword = parts[3].trim();
                    if (storedEmail.equalsIgnoreCase(email) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            // ignore
        }
        return false;
    }

    static boolean registerUser(String email, String studentId, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
