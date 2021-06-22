package cars;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String carBrand;

    private String type;

    private int age;

    private CarCondition carCondition;

    private List<KmState> kmStateList;

    public Car(String carBrand, String type, int age, CarCondition carCondition, List<KmState> kmStateList) {
        this.carBrand = carBrand;
        this.type = type;
        this.age = age;
        this.carCondition = carCondition;
        this.kmStateList = new ArrayList<>(kmStateList);
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public CarCondition getCarCondition() {
        return carCondition;
    }

    public List<KmState> getKmStateList() {
        return new ArrayList<>(kmStateList);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", carCondition=" + carCondition +
                ", kmStateList=" + kmStateList +
                '}';
    }
}
