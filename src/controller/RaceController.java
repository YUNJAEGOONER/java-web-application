package controller;

import domain.Car;
import exception.ExceptionCode;
import exception.RacingGameException;
import http.HttpRequest;
import http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RaceController implements Controller {

    private List<Car> carList;

    private final static int threshold = 5;

    @Override
    public HttpResponse doLogic(HttpRequest httpRequest) {
        String body = httpRequest.body();
        String[] players = body.split("racer=|&racer=");
        try{
            checkDuplicateName(players);
            initGame(players);
            playGame();
            return createResponse();
        } catch (RacingGameException e) {
            return createException(e.getExceptionCode().getErrorMsg());
        }

    }

    public void initGame(String[] players) {
        carList = new ArrayList<>();
        for (int i = 1; i < players.length; i++) {
            Car car = new Car(players[i]);
            carList.add(car);
        }
    }

    public void playGame() {
        for (int i = 0; i < 10; i++) {
            for (Car car : carList) {
                int randomNum = generateRandomNumber();
                if (randomNum >= threshold) {
                    car.drive();
                }
            }
        }
    }

    public int generateRandomNumber() {
        return (int) (Math.random() * 10);
    }

    public void checkDuplicateName(String [] players){
        Set<String> racers = new HashSet<>();

        for(int i = 0 ; i < players.length ; i ++ ){
            racers.add(players[i]);
        }

        if(racers.size() != players.length){
            throw new RacingGameException(ExceptionCode.RACER_NAME_DUPLICATE);
        }
    }

    private HttpResponse createResponse() {

        String version = "HTTP/1.1";
        String status = "200 OK";

        String responseBody = createRacingResult();

        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Length", Integer.toString(responseBody.getBytes(StandardCharsets.UTF_8).length));
        headers.put("Content-Type", "text/html; charset=UTF-8");

        return new HttpResponse(version, status, headers, responseBody);

    }

    private String createRacingResult() {

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang='ko'>");

        sb.append("<head>");
        sb.append("<meta charset='UTF-8'>");
        sb.append("<title>레이싱 결과</title>");

        sb.append("<style>");
        sb.append("body { font-family: Arial, sans-serif; margin: 40px; background-color:#ffffff; }");
        sb.append("h1 { text-align:center; margin-bottom:30px; }");
        sb.append(".result-card { max-width: 600px; margin:auto; padding:20px; ");
        sb.append("background-color:#f9f9f9; border:2px solid #ccc; border-radius:10px; text-align:center; }");
        sb.append("table { width:100%; border-collapse: collapse; table-layout: fixed; margin-top:10px; }");
        sb.append("th { background:#007bff; color:white; padding:12px; font-size:16px; text-align:center; }");
        sb.append("td { padding:10px; border-bottom:1px solid #e0e0e0; font-size:15px; }");
        sb.append("tr:last-child td { border-bottom:none; }");
        sb.append("td.name { text-align:center; font-weight:bold; width: 30%; }");
        sb.append("td.track { text-align:left; font-family:monospace; font-size:1em; color:#007bff; width: 70%; white-space: pre; overflow-x: auto; }");
        sb.append(".back-button { display: inline-block; margin-top:20px; padding:10px 20px; ");
        sb.append("font-size:16px; background-color:#007bff; color:white; border:none; border-radius:6px; ");
        sb.append("text-decoration:none; cursor:pointer; }");
        sb.append(".back-button:hover { background-color:#0056b3; }");
        sb.append("</style>");

        sb.append("</head>");

        sb.append("<body>");
        sb.append("<h1>\uD83C\uDFCE\uFE0F \uD83C\uDFC1 레이싱 게임</h1>");
        sb.append("<div class='result-card'>");
        sb.append("<table>");
        sb.append("<tr><th>레이서</th><th>경주 결과</th></tr>");

        for (Car car : carList) {
            sb.append("<tr>");
            sb.append("<td class='name'>").append(car.getName()).append("</td>");
            sb.append("<td class='track'>");

            // 트랙 초기화
            int trackLength = 15;
            for (int i = 0; i < trackLength; i++) {
                if (i == car.getDistance()) {
                    sb.append("\uD83D\uDE98");
                } else {
                    sb.append("   "); // 공백
                }
            }

            sb.append("</td>");
            sb.append("</tr>");
        }

        sb.append("</table>");

        sb.append("<a href='/home' class='back-button'>\uD83D\uDD19 돌아가기</a>");

        sb.append("</div>");
        sb.append("</body></html>");

        return sb.toString();
    }

    private HttpResponse createException(String message) {

        String version = "HTTP/1.1";
        String status = "400 BAD REQUEST";

        String responseBody = "<script> "
                + "alert('" + message + "'); "
                + "history.back();"
                + "</script>";

        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Length", Integer.toString(responseBody.getBytes(StandardCharsets.UTF_8).length));
        headers.put("Content-Type", "text/html; charset=UTF-8");

        return new HttpResponse(version, status, headers, responseBody);
    }


}
