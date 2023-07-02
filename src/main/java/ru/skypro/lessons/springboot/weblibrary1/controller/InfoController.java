package ru.skypro.lessons.springboot.weblibrary1.controller;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${app.env}")
    String env;
    @GetMapping("/appInfo")
    public String appInfo(){
        return env;
    };
}
