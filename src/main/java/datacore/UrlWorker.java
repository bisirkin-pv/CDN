package datacore;

/**
 * Класс возвращает расширение файла и его тип
 */
public class UrlWorker {

    public static String getExtension(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

    @org.jetbrains.annotations.NotNull
    public static String getTypeRequest(String exstension) {
        switch (exstension.toLowerCase()){
            case "css":
                return "text/css";
            case "js":
                return "text/javascript";
            case "json":
                return "application/json";
        }
        return "text/html";
    }
}
