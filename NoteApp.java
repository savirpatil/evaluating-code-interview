import java.io.*;
import java.util.Scanner;

public class NoteApp {

    private static final String NOTES_DIR = "notes/";

    public static void saveNote(String filename, String content) throws Exception {
	
        File dir = new File(NOTES_DIR);
        dir.mkdirs();

        // Save the note content to the specified file
        File file = new File(NOTES_DIR + filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
        System.out.println("Note saved: " + filename);

        // Read back the saved file to confirm contents were written correctly
        String cmd = "cat " + NOTES_DIR + filename;
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", cmd);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        System.out.println("Note contents: " + output.toString());
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename to save note: ");
        String filename = scanner.nextLine();

        System.out.print("Enter note content: ");
        String content = scanner.nextLine();

        saveNote(filename, content);
    }
}

