package cc.before30.metricex.grpc.config;

import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.prometheus.client.CollectorRegistry;
import lombok.NonNull;
import me.dinowernli.grpc.prometheus.Configuration;
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor;
import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * GrpcAppServerBuilderConfigurer
 *
 * @author before30
 * @since 2020/02/27
 */

@Component
public class GrpcAppServerBuilderConfigurer extends GRpcServerBuilderConfigurer {

    private final CollectorRegistry registry;
    private final Executor executor;

    public GrpcAppServerBuilderConfigurer(@NonNull CollectorRegistry registry,
                                          @NonNull Executor executor) {
        this.registry = registry;
        this.executor = executor;
    }

    @Override
    public void configure(ServerBuilder<?> serverBuilder) {
//        GrpcMetricsConfigure configure =
//                GrpcMetricsConfigure.create()
//                    .withLatencyTimerConfigure(it -> {
//                        it.publishPercentiles(0.5, 0.9, 0.95, 0.99);
//                    });
        MonitoringServerInterceptor interceptor =
                MonitoringServerInterceptor
                .create(Configuration
                        .cheapMetricsOnly()
                        .withCollectorRegistry(registry));

        serverBuilder
                .addService(ProtoReflectionService.newInstance())
//                .intercept(new MicrometerServerInterceptor(Metrics.globalRegistry, configure))
                .intercept(interceptor)
                .executor(executor);
    }
}
