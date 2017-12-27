package view;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class InitView {
    public static Map<String, Object> init() throws IOException {
        Map<String, Object> model = new HashMap<>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("localization/messages"
                , new Locale("en"));
        model.put("project_name", resourceBundle.getString("project_name"));
        model.put("page_main_header", resourceBundle.getString("page_main_header"));
        return model;
    }
}
