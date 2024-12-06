package poster.scan;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mogrify extends CLI {
    public static class Builder {
        List<String> args;
        String size_string;
        String target;
        String exec_path;
        Path photo_path;

        public Builder setExecPath(String exec_path) {
            this.exec_path = exec_path;
            return this;
        }

        public Builder setImageSize(Integer width, Integer height) {
            this.size_string = width + "x" + height + "!";
            return this;
        }

        public Builder setPhotoPath(String path) {
            this.photo_path = Paths.get(path);
            return this;
        }

        public Builder setTargetExtension(String extension) {
            this.target = "*." + extension;
            return this;
        }

        public Mogrify build() {
            List<String> args = new ArrayList<>();
            if (size_string != null) {
                args.addAll(Arrays.asList("-resize", size_string));
            }
            // Last step
            if (target != null && photo_path != null) {
                args.add(photo_path + "/" + target);
            }
            this.args = args;
            return new Mogrify(exec_path, this.args);
        }
    }

    private Mogrify(String exec_path, List<String> args) {
        this.exec_path = exec_path;
        this.args = args;
        this.workingDirectory = Path.of("").toAbsolutePath();
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
