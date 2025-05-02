package org.kaliLearn.springboot_rest_api.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody

/**
 * We don't need to use the ```@Controller ``` and the ```@ResponseBody``` since in spring 4.3 they
 * include the ```@RestController``` which combines both the @Controller and the @ResponceBody```
 */

@RestController
public class HelloWorldController {

    // Http GEt Request
    //Client shall use http://localhost:8080/hello-world

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
}
