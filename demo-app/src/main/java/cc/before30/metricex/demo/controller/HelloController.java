package cc.before30.metricex.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author before30
 * @since 2020/03/04
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }
}
