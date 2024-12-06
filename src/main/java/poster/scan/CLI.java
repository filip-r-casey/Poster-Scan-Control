package poster.scan;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

abstract class CLI {
    List<String> args;
    String exec_path;
    Path workingDirectory;

    public void run() {
        args.add(0, exec_path);
        Preconditions.checkNotNull(args, "Arguments must not be null");
        Preconditions.checkArgument(!args.isEmpty(), "No arguments specified");

        ProcessBuilder builder = new ProcessBuilder(args);
        if (workingDirectory != null) {
            builder.directory(workingDirectory.toFile());
        }
//        builder.inheritIO();
        builder.redirectErrorStream(true);

        try {
            Process dlProcess = builder.start();

            System.out.println(builder.command());
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(dlProcess.getInputStream()));
            String s = null;

            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            int exitCode = dlProcess.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (Exception IOException) {
            System.out.println(IOException);
        }
    }
}
