package view;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.util.Map;

public class AddContentView {

    public static String getView(FreeMarkerEngine freeMarkerEngine, String template, Map<String, Object> param){
        Map<String, Object> model = null;
        try {
            model = InitView.init();
            if (param != null){
                model.putAll(param);
            }
            model.put("auth", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freeMarkerEngine.render(new ModelAndView(model,template));
    }

    public static String getView(FreeMarkerEngine freeMarkerEngine, String template, Map<String, Object> param, Boolean auth){
        Map<String, Object> model = null;
        try {
            model = InitView.init();
            if (param != null){
                model.putAll(param);
            }
            model.put("auth", auth);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freeMarkerEngine.render(new ModelAndView(model,template));
    }

}
