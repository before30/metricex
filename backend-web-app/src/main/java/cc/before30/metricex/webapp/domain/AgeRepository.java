package cc.before30.metricex.webapp.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * AgeRepository
 *
 * @author before30
 * @since 2020/02/27
 */

@Repository
public interface AgeRepository extends CrudRepository<Age, Long> {
}
