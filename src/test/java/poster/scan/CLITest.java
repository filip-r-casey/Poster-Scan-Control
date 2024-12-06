package poster.scan;

import org.junit.Test;

public class CLITest {
    @Test
    public void testGalleryDLStandard() {
        GalleryDL gallerydl = GalleryDL.newBuilder()
                .setExecPath("/opt/homebrew/bin/gallery-dl")
                .excludeVideos()
                .limitMaxPosts(10)
                .addUrl("https://www.instagram.com/d3artswestwood/")
                .build();
        gallerydl.run();
    }
    @Test
    public void testMogrifyStandard() {
        Integer GPT_IMAGE_WIDTH = 150;
        Integer GPT_IMAGE_HEIGHT = 150;
        Mogrify mogrify = Mogrify.newBuilder()
                .setExecPath("/opt/homebrew/bin/mogrify")
                .setImageSize(GPT_IMAGE_WIDTH, GPT_IMAGE_HEIGHT)
                .setPhotoPath("/Users/filipcasey/School/FA2024/CSCI5448/PROJECT/poster-scan/app/gallery-dl/instagram/d3artswestwood")
                .setTargetExtension("jpg")
                .build();
        mogrify.run();
    }
}
