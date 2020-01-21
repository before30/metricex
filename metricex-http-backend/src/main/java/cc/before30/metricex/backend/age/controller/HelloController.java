package cc.before30.metricex.backend.age.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author before30
 * @since 2020/01/22
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }

}
