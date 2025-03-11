import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Choose an option:\n1. Write to file\n2. Read from file\n3. Modify file\n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("Enter text to write to file:");
                    String content = scanner.nextLine();
                    writeFile(content);
                    break;
                case 2:
                    System.out.println("File Content:");
                    readFile();
                    break;
                case 3:
                    System.out.println("Enter the word to replace:");
                    String oldWord = scanner.nextLine();
                    System.out.println("Enter the new word:");
                    String newWord = scanner.nextLine();
                    modifyFile(oldWord, newWord);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    public static void writeFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(content);
            writer.newLine();
            writer.flush();
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void modifyFile(String oldWord, String newWord) {
        try {
            Path path = Paths.get(FILE_PATH);
            List<String> lines = Files.readAllLines(path);
            List<String> modifiedLines = new ArrayList<>();

            for (String line : lines) {
                modifiedLines.add(line.replace(oldWord, newWord));
            }

            Files.write(path, modifiedLines);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
