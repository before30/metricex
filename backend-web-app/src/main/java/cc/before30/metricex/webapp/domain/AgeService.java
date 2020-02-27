package cc.before30.metricex.webapp.domain;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * AgeService
 *
 * @author before30
 * @since 2020/02/27
 */

@Service
@Slf4j
public class AgeService {

    private final AgeRepository repository;

    public AgeService(@NonNull AgeRepository repository) {
        this.repository = repository;
    }

    public Age findOne(Long id) {
        Optional<Age> ageOptional = repository.findById(id % 100);

        if (!ageOptional.isPresent()) {
            log.error("Id {} not found.", id);
            throw new RuntimeException("ID not found.");
        }

        return ageOptional.get();
    }

    @Counted("test_hello2_counter")
    public void testCounted() {}

    @Timed(value = "test_hello2_timer", percentiles = {0.5, 0.9, 0.99}, histogram = true)
    public void testTimed() {
        try {
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(10, 500));
        } catch (InterruptedException ex) {}
    }
}
