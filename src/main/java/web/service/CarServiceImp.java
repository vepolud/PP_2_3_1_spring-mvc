package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImp implements CarService{
    private static List<Car> carList = getNewCarList();

    public static List<Car> getCarList(int count) {
        if (carList != null && count > 0) {
            return count > 5 ? carList : carList.subList(0, count);
        }
        return carList;
    }

    public static List<Car> getNewCarList() {
        if (carList == null) {
            carList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                carList.add(new Car());
                carList.get(i).setModel(String.valueOf(i));
                carList.get(i).setColor(String.valueOf(i * 2));
                carList.get(i).setHorsePower(i * 10);
            }
        }
        return carList;
    }

}
