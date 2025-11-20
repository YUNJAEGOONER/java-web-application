package controller;

import http.HttpRequest;
import http.HttpResponse;

public interface Controller {

    public HttpResponse doLogic(HttpRequest httpRequest);

}
