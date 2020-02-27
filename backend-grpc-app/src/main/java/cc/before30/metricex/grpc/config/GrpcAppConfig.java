package cc.before30.metricex.grpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * GrpcAppConfig
 *
 * @author before30
 * @since 2020/02/27
 */
@Configuration
public class GrpcAppConfig {

    @Bean
    public Executor executor() {
        return ForkJoinPool.commonPool();
    }

}
