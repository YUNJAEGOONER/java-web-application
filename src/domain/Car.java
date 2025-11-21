package domain;

import exception.ExceptionCode;
import exception.RacingGameException;

public class Car{

    private final String name;

    private int distance = 0;

    public Car(String name){
        validatePlayerName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void drive(){
        this.distance ++;
    }

    private void validatePlayerName(String name){
        if(name.isBlank() || name.length() > 10){
            throw new RacingGameException(ExceptionCode.RACER_NAME_LENGTH_LIMIT);
        }
    }

}
