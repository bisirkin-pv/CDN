import com.google.gson.Gson;
import datacore.DataWorkerCDN;
import datacore.XmlCDN;
import datacore.xml.CDN;
import datacore.xml.ElementCDN;
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
        DataWorkerCDN cdn = new XmlCDN();
        cdn.load();

        get("/cdn", (req, res) -> AddContentView.getView(freeMarkerEngine));
        get("/cdn/:resource", (req, res) -> {
            System.out.println(req.params(":resource"));
            ElementCDN elementCDN = cdn.getCDN(req.params(":resource"));
            res.type(elementCDN.getType());
            //return SenderStaticFile.getCSS("https://raw.githubusercontent.com/bisirkin-pv/DigitalKeyboard/master/DigitalKeyboard.css");
            return SenderStaticFile.getFile(elementCDN.getSourceUrl());

        });

        post("/api/save","application/json",(req, res)->{
            String result = "200";

            try{
                cdn.save(req.queryParams("github_name"),req.queryParams("github_url"));
                System.out.println("save success");
            }catch (Exception ex){
                result = "500";
            }
            return new Gson().toJson(result);
        });


    }
}
