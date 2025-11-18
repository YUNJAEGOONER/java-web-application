package controller;

import http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import domain.Car;

public class RaceController implements Controller{

    private List<Car> carList;

    private final static int threshold = 5;

    @Override
    public String doLogic(HttpRequest httpRequest) {

        String body = httpRequest.body();
        String[] players = body.split("racer=|&racer=");

        initGame(players);
        playGame();
        rankPlayer();

        return createResponse();
    }

    public void initGame(String [] players){
        carList = new ArrayList<>();
        for(int i = 1 ; i < players.length ; i ++ ){
            Car car = new Car(players[i]);
            carList.add(car);
        }
    }

    public void playGame(){
        for(int i = 0 ; i < 10 ; i ++ ) {
            for (Car car : carList) {
                int randomNum = generateRandomNumber();
                if(randomNum >= threshold){
                    car.drive();
                }
            }
        }
    }

    public List<String> rankPlayer(){
        Collections.sort(carList);
        int maxDistance = carList.get(0).getDistance();
        return carList.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(car -> car.getName())
                .toList();
    }

    public int generateRandomNumber(){
        return (int)(Math.random() * 10);
    }

    private String createResponse(){

        String responseBody = "<h1> 레이싱 결과 <h1>";

        for(Car car : carList){
            responseBody += "<h3> 레이서 : " + car.getName() + " 위치 : " +  car.getDistance() + "<h3>";
        }

        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: " + responseBody.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "\r\n" +
                responseBody;

        return httpResponse;
    }
}
