import java.io.*;

public class FileOperations {
    
    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Content written to " + filename);
        } catch (IOException e) { 
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Content read from " + filename + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + filename + " not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void appendToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.newLine();
            writer.write(content);
            System.out.println("Content appended to " + filename);
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println(filename + " has been deleted.");
            } else {
                System.out.println("Error deleting " + filename);
            }
        } else {
            System.out.println("Error: " + filename + " not found.");
        }
    }

    public static void main(String[] args) {
        String filename = "example.txt";
        writeToFile(filename, "Hello, this is a test file.");
        readFromFile(filename);
        appendToFile(filename, "Appending more data to the file.");
        readFromFile(filename);
        deleteFile(filename);
    }
}
