package controller;

import http.HttpRequest;

public interface Controller {

    public String doLogic(HttpRequest httpRequest);

}
