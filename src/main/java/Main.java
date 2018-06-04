import com.google.gson.Gson;
import com.sun.scenario.Settings;
import datacore.DataWorkerCDN;
import datacore.XmlCDN;
import datacore.RequestStatus;
import datacore.xml.ElementCDN;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import route.SenderStaticFile;
import settings.PropertyReader;
import spark.template.freemarker.FreeMarkerEngine;
import view.AddContentView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.*;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_26);
        freemarkerConfiguration.setDefaultEncoding("UTF-8");
        freemarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(Main.class, "/templates/"));
        freeMarkerEngine.setConfiguration(freemarkerConfiguration);
        staticFiles.location("/public");
        port(8088);
        setting();
        DataWorkerCDN cdn = new XmlCDN();
        cdn.load();

        get("/", (req, res) -> AddContentView.getView(freeMarkerEngine, "login.ftl", null));
        get("/cdn", (req, res) -> AddContentView.getView(freeMarkerEngine, "addContent.ftl", null));
        get("/cdn/list", (req, res) -> AddContentView.getView(freeMarkerEngine, "listcdn.ftl", cdn.list()));

        get("/cdn/:resource", (req, res) -> {
            ElementCDN elementCDN = cdn.getCDN(req.params(":resource"));
            res.type(elementCDN.getType());
            return SenderStaticFile.getFile(elementCDN.getSourceUrl());

        });

        post("/api/save","application/json",(req, res)->{
            RequestStatus requestStatus;
            try{
                cdn.save(req.queryParams("github_name"),req.queryParams("github_url"));
                requestStatus = new RequestStatus(200,"Success save");
            }catch (Exception ex){
                requestStatus = new RequestStatus(500,"Invalid save");
            }
            return new Gson().toJson(requestStatus);
        });

        before((request, response) -> {
            String log = "connect ip:" + request.ip() + " >> " + request.requestMethod() + " - " + request.url();
            LOG.log(Level.INFO, log);
        });
    }

    private static void setting() throws IOException {

        PropertyReader propertyReader = null;
            propertyReader = new PropertyReader("../config/settings.properties");
            secure(propertyReader.getProperties("ssl.keyStoreLocation")
                    , propertyReader.getProperties("ssl.keyStorePassword")
                    , null
                    , null);
            LOG.log(Level.INFO, "SSL setting success");


    }
}
