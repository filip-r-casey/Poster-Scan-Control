package poster.scan;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ffmpegTest {
    public static void main(String[] args) {
        // Define the path to your input and output files
        String inputFilePath = "input.mp4";
        String outputFilePath = "output.mp4";

        // Construct the ffmpeg command with multiple arguments
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");                      // Command to execute
        command.add("-i");                          // Input flag
        command.add(inputFilePath);                 // Path to input file
        command.add("-vf");                         // Video filter flag
        command.add("scale=1280:720");              // Example filter: scale video to 720p
        command.add("-c:v");                        // Set codec for video stream
        command.add("libx264");                     // Codec
        command.add("-preset");                     // Preset flag
        command.add("fast");                        // Preset value
        command.add(outputFilePath);                // Output file path

        // Create the process builder with the command
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        // Redirect error stream to inherit output for better error visibility
        processBuilder.redirectErrorStream(true);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);  // Print each line of output
                }
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
