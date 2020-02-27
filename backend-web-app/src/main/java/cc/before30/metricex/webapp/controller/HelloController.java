package cc.before30.metricex.webapp.controller;

import cc.before30.metricex.webapp.domain.AgeService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import lombok.NonNull;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HelloController
 *
 * @author before30
 * @since 2020/02/27
 */
@RestController
public class HelloController {
    private Counter counter;
    private Timer timer;
    private Timer timer2;
    private AtomicInteger gauge;
    private AgeService ageService;

    public HelloController(@NonNull AgeService ageService) {
        this.counter = Metrics.counter("test.hello.manual.counter");
        this.timer = Metrics.timer("test.hello.manual.timer");
        this.timer2 = Timer.builder("test.hello.percentiles.timer")
                .publishPercentiles(0.5, 0.9, 0.99)
                .sla()
                .register(Metrics.globalRegistry);
        this.gauge = Metrics.gauge("test_hello_manual_gauge", new AtomicInteger(0));
        this.ageService = ageService;
    }

    @GetMapping("/hello")
    public String hello() {
        counter.increment();
        timer.record(RandomUtils.nextInt(10, 100), TimeUnit.MILLISECONDS);
        timer2.record(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(10, 100));
            } catch (InterruptedException ex) {}
        });
        gauge.addAndGet(RandomUtils.nextInt(0, 100));

        return "Hello from backend-web-app";
    }

    @GetMapping("/hello2")
    public String hello2() {
        ageService.testCounted();
        ageService.testTimed();

        return "hello2";
    }
}
