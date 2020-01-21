package cc.before30.metricex.backend.age.domain.age;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * AgeRepository
 *
 * @author before30
 * @since 2020/01/22
 */

@Repository
public interface AgeRepository extends CrudRepository<Age, Long> {
}
