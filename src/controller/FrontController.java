package controller;

import http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrontController {

    private Map<MethodUrl, Controller> controllerMap = new HashMap<>();

    public FrontController(){
        initControllerMap();
    }

    private void initControllerMap(){
        controllerMap.put(new MethodUrl("GET", "/home"), new HomeController());
        controllerMap.put(new MethodUrl("POST", "/play-racing"), new RaceController());
    }

    public String toController(HttpRequest httpRequest){

        String method = httpRequest.method();
        String path = httpRequest.path();

        Controller controller = controllerMap.get(new MethodUrl(method, path));

        if(controller == null){
            return "ERROR";
        }

        return controller.doLogic(httpRequest);
    }

    public static class MethodUrl{
        private final String method;
        private final String url;

        public MethodUrl(String method, String url) {
            this.method = method;
            this.url = url;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof MethodUrl){
                MethodUrl methodUrl = (MethodUrl) obj;
                return Objects.equals(methodUrl.method,this.method) &&
                        Objects.equals(methodUrl.url, this.url);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.method, this.url);
        }
    }

}
