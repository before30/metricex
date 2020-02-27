package cc.before30.metricex.webapp.controller;

import cc.before30.metricex.webapp.domain.Age;
import cc.before30.metricex.webapp.domain.AgeService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 *
 * @author before30
 * @since 2020/02/27
 */
@RestController
@Slf4j
public class ApiController {

    private final AgeService ageService;

    public ApiController(@NonNull AgeService ageService) {
        this.ageService = ageService;
    }

    @GetMapping("/each")
    public String eachByQueryParam(@RequestParam("query") Long query) {
        Age age = ageService.findOne(query);
        log.info("query is {}", query);

        return age.toString() + " from backend-web-app";
    }

    @GetMapping("/each/{query}")
    public String eachByPathParam(@PathVariable("query") Long query) {
        Age age = ageService.findOne(query);
        log.info("query is {}", query);

        return age.toString() + " from backend-web-app";
    }
}
