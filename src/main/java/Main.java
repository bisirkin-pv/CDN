import com.google.gson.Gson;
import datacore.XmlCDN;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import route.SenderStaticFile;
import spark.template.freemarker.FreeMarkerEngine;
import view.AddContentView;

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
        port(8080);

        get("/cdn", (req, res) -> AddContentView.getView(freeMarkerEngine));
        get("/DigitalKeyboard", (req, res) -> {
            System.out.println("Send css");
            res.type("text/css");
            return SenderStaticFile.getCSS("https://raw.githubusercontent.com/bisirkin-pv/DigitalKeyboard/master/DigitalKeyboard.css");
        });

        post("/api/save","application/json",(req, res)->{
            XmlCDN xmlCDN = new XmlCDN();
            String result = "200";

            try{
                xmlCDN.save(req.queryParams("github_name"),req.queryParams("github_url"));
            }catch (Exception ex){
                result = "500";
            }
            return new Gson().toJson(result);
        });


    }
}
