package view;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.util.Map;

public class AddContentView {
    public static String getView(FreeMarkerEngine freeMarkerEngine){
        Map<String, Object> model = null;
        try {
            model = InitView.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freeMarkerEngine.render(new ModelAndView(model,"addContent.ftl"));
    }
}
