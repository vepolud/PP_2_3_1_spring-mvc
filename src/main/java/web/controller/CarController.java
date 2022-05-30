package web.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.config.WebConfig;
import web.model.Car;
import web.service.CarServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
//    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//    CarServiceImp carServiceImp = context.getBean("CarServiceImp", CarServiceImp.class);
    @GetMapping(value = "/cars")
    public String printWelcome(ModelMap model) {
//        List<Car> messages = carServiceImp.getCarList(5);
        List<Car> messages = CarServiceImp.getCarList(5);
//        messages.add("Hello Cars!");
//        messages.add("I'm Spring MVC application");
//        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "index";
    }
}
