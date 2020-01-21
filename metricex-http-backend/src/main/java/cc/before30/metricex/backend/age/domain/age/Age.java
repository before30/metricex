package cc.before30.metricex.backend.age.domain.age;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Age
 *
 * @author before30
 * @since 2020/01/22
 */

@Entity(name = "ages")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Age implements Serializable {

    private static final long serialVersionUID = 7665549076213461120L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer age;

    @Builder
    public Age(Long id, Integer age) {
        this.id = id;
        this.age = age;
    }

}
