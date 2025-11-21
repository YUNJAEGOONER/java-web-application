package controller;

import http.HttpRequest;
import http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrontController {

    private final Map<MethodUrl, Controller> controllerMap = new HashMap<>();

    public FrontController(){
        initControllerMap();
    }

    private void initControllerMap(){
        controllerMap.put(new MethodUrl("GET", "/home"), new HomeController());
        controllerMap.put(new MethodUrl("POST", "/play-racing"), new RaceController());
    }

    public HttpResponse toController(HttpRequest httpRequest){

        String method = httpRequest.method();
        String path = httpRequest.path();

        Controller controller = controllerMap.get(new MethodUrl(method, path));

        if(controller == null){
            controller = controllerMap.get(new MethodUrl("GET" , "/home")); // 존재하지 않는 경로로 요청 시, home 화면으로 이동
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
                return methodUrl.method.equals(this.method) &&
                        methodUrl.url.equals(this.url);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.method, this.url);
        }
    }

}
