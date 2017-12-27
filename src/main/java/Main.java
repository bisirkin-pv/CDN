import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import view.AddContentView;
import view.InitView;

import java.io.InputStream;
import java.util.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_26);
        freemarkerConfiguration.setDefaultEncoding("UTF-8");
        freemarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Main.class, "/templates/"));
        freeMarkerEngine.setConfiguration(freemarkerConfiguration);
        staticFiles.location("/public");
        //port(80);

        get("/cdn", (req, res) -> AddContentView.getView(freeMarkerEngine));
    }
}
