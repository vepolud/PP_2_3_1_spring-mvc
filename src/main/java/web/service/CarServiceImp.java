package web.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import web.config.WebConfig;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImp {
//    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
private static  List<Car> carList = null;

    public static List<Car> getCarList(int count) {
        if (carList != null) {
            return carList.subList(0, count);
        }
        carList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            carList.add(new Car());
            carList.get(i).setModel(String.valueOf(i));
            carList.get(i).setColor(String.valueOf(i * 2));
            carList.get(i).setHorsePower(i * 10);
        }
        return carList;
    }

}
