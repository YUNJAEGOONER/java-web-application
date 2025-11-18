package domain;

public class Car implements Comparable<Car>{

    private final String name;

    private int distance = 0;

    public Car(String name){
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

    @Override
    public int compareTo(Car car) {
        return car.getDistance() - this.distance;
    }

}
