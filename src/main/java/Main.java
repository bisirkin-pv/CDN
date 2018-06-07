import auth.Auth;
import auth.AuthService;
import auth.User;
import com.google.gson.Gson;
import datacore.DataWorkerCDN;
import datacore.XmlCDN;
import datacore.RequestStatus;
import datacore.xml.ElementCDN;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import route.SenderStaticFile;
import settings.PropertyReader;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import view.AddContentView;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.*;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    private static AuthService auth = null;

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
        DataWorkerCDN cdn = new XmlCDN("cdn.xml");
        cdn.load();

        get("/", (req, res) -> AddContentView.getView(freeMarkerEngine, "login.ftl", null, false));

        path("/api", () -> {
            before("/*", (request, response) -> {
                if (!checkAuth(request)) {
                    response.redirect("/");
                }
            });
            post("/save","application/json",(req, res)->{
                RequestStatus requestStatus;
                try{
                    cdn.save(req.queryParams("github_name"),req.queryParams("github_url"));
                    requestStatus = new RequestStatus(200,"Success save");
                }catch (Exception ex){
                    requestStatus = new RequestStatus(500,"Invalid save");
                }
                return new Gson().toJson(requestStatus);
            });

            post("/delete","application/json", (req, res)->{
                RequestStatus requestStatus = new RequestStatus(500,"Invalid delete");;
                try{
                    String link = req.queryParams("link");
                    if(link != null) {
                        cdn.delete(req.queryParams("link"));
                        requestStatus = new RequestStatus(200, "Success delete");
                    }
                }catch (Exception ex){
                    requestStatus = new RequestStatus(500,"Invalid delete");
                }
                return new Gson().toJson(requestStatus);
            });
        });

        post("/login","application/json", (req, res)->{
            RequestStatus requestStatus;
            String login = req.queryParams("inputLogin");
            String pass = req.queryParams("inputPassword");
            if(login == null || pass == null) {
                return new Gson().toJson(new RequestStatus(500,"Invalid login or password"));
            }
            if(auth.check(login.trim(), pass)) {
                User.setSessionId(req.session().id());
                req.session().attribute("user", req.session().id());
                requestStatus = new RequestStatus(200, "Success");

            }else{
                requestStatus = new RequestStatus(500,"Invalid login or password");
            }
            return new Gson().toJson(requestStatus);
        });

        get("/logout", "application/json", (req, res)->{
            RequestStatus requestStatus;
            try{
                req.session().removeAttribute("user");
                User.setSessionId("");
                requestStatus = new RequestStatus(200,"Success");;
            }catch(Exception ex){
                LOG.log(Level.SEVERE, ex.getMessage());
                requestStatus = new RequestStatus(500,"Error");
            }
            return new Gson().toJson(requestStatus);
        });

        path("/cdn", () -> {
            before("/*", (request, response) -> {
                if (!checkAuth(request)) {
                    response.redirect("/");
                }
            });
            get("/add", (req, res) -> AddContentView.getView(freeMarkerEngine, "addContent.ftl", null));
            get("/list", (req, res) -> AddContentView.getView(freeMarkerEngine, "listcdn.ftl", cdn.list()));

            get("/:resource", (req, res) -> {
                ElementCDN elementCDN = cdn.getCDN(req.params(":resource"));
                res.type(elementCDN.getType());
                return SenderStaticFile.getFile(elementCDN.getSourceUrl());

            });
        });

        before((request, response) -> {
            String log = "connect ip:" + request.ip() + " >> " + request.requestMethod() + " - " + request.url();
            LOG.log(Level.INFO, log);
        });

        after("/logout",(request, response) -> {
            response.redirect("/");
        });
    }

    private static boolean checkAuth(Request request) {
        boolean authenticated = false;
        String sessionId = request.session().attribute("user");
        if (User.getSessionId().equals(sessionId) || request.pathInfo().equals("/")){
            authenticated = true;
        }
        return authenticated;
    }

    private static void setting() throws IOException {

        PropertyReader propertyReader;
        propertyReader = new PropertyReader("../config/settings.properties");
        secure(propertyReader.getProperties("ssl.keyStoreLocation")
                , propertyReader.getProperties("ssl.keyStorePassword")
                , null
                , null);
        LOG.log(Level.INFO, "SSL setting success");
        auth = new Auth(propertyReader.getProperties("user.name")
                        ,propertyReader.getProperties("user.password"));
    }
}
