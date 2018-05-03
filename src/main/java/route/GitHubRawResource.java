package route;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

//Читает данные с гит хаба
public class GitHubRawResource {
    public String read(String url) throws URISyntaxException, IOException {
        StringBuilder stringBuilder;
        URI uri = new URI(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) uri.toURL().openConnection();
        stringBuilder = new StringBuilder();
        try(Scanner scanner = new Scanner(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8")))
        {
            while (scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        }
        httpURLConnection.disconnect();
        return stringBuilder.toString();
    }

}
