package training.busboard.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.Arrivals;

import java.util.ArrayList;

@Controller
@EnableAutoConfiguration
public class Website<arrivals> {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        BusInfo busInfo = new BusInfo(postcode);


        return new ModelAndView("info", "busInfo", busInfo) ;
    }



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}