package cc.before30.metricex.backend.age.controller;

import cc.before30.metricex.backend.age.domain.age.Age;
import cc.before30.metricex.backend.age.domain.age.AgeService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AgeController
 *
 * @author before30
 * @since 2020/01/22
 */
@RestController
@RequestMapping("/api/v1.0/age")
public class AgeController {

    private final AgeService ageService;

    public AgeController(@NonNull AgeService ageService) {
        this.ageService = ageService;
    }

    @GetMapping("/{id}")
    public Age findAgeById(@PathVariable(name = "id") Long id) {
        return ageService.findOne(id);
    }

}
