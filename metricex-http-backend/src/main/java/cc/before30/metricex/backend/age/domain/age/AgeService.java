package cc.before30.metricex.backend.age.domain.age;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AgeService
 *
 * @author before30
 * @since 2020/01/22
 */
@Service
@Slf4j
public class AgeService {

    private final AgeRepository repository;

    public AgeService(@NonNull AgeRepository repository) {
        this.repository = repository;
    }

    public Age findOne(Long id) {
        Optional<Age> ageOptional = repository.findById(id);
        if (!ageOptional.isPresent()) {
            log.info("ID {} doesn't exist.", id);
            throw new RuntimeException("Id doesn't exist");
        }

        return ageOptional.get();
    }
}
