package poster.scan;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryDL extends CLI {
    public static class Builder {
        String url;
        Integer max_posts;
        Boolean exclude_videos = false;
        List<String> args;
        String exec_path;

        private static String IMAGE_FILTER_STRING = "\"url~r'.*\\.(jpg|jpeg|png)'\"";

        public Builder setExecPath(String exec_path) {
            this.exec_path = exec_path;
            return this;
        }

        public Builder addUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder limitMaxPosts(Integer max_posts) {
            this.max_posts = max_posts;
            return this;
        }

        public Builder excludeVideos() {
            this.exclude_videos = true;
            return this;
        }

        public GalleryDL build() {
            List<String> args = new ArrayList<>();

            if (exclude_videos) {
                args.addAll(Arrays.asList("--filter", IMAGE_FILTER_STRING));
            }

            if (max_posts != null) {
                args.addAll(Arrays.asList("--range", ":" + max_posts));
            }
            args.addAll(Arrays.asList("--dest", System.getProperty("java.io.tmpdir")));

            if (url != null) {
                args.add(url);
            }
            this.args = args;
            return new GalleryDL(exec_path, this.args);
        }
    }

    private GalleryDL(String exec_path, List<String> args) {
        this.exec_path = exec_path;
        this.args = args;
        this.workingDirectory = Path.of("").toAbsolutePath();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

}
