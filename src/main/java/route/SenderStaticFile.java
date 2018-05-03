package route;

import java.io.IOException;
import java.net.URISyntaxException;

public class SenderStaticFile {
    public static String getCSS(String resourceUrlRaw){
        GitHubRawResource gitHubRawResource = new GitHubRawResource();
        String raw = "";
        try {
            raw = gitHubRawResource.read(resourceUrlRaw);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return raw;
    }
}
