package cc.before30.metricex.grpc.config;

import com.be_hase.grpc.micrometer.GrpcMetricsConfigure;
import com.be_hase.grpc.micrometer.MicrometerServerInterceptor;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.micrometer.core.instrument.Metrics;
import lombok.NonNull;
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

    private final Executor executor;

    public GrpcAppServerBuilderConfigurer(@NonNull Executor executor) {
        this.executor = executor;
    }

    @Override
    public void configure(ServerBuilder<?> serverBuilder) {
        GrpcMetricsConfigure configure =
                GrpcMetricsConfigure.create()
                    .withLatencyTimerConfigure(it -> {
                        it.publishPercentiles(0.5, 0.9, 0.95, 0.99);
                    });

        serverBuilder
                .addService(ProtoReflectionService.newInstance())
                .intercept(new MicrometerServerInterceptor(Metrics.globalRegistry, configure))
                .executor(executor);
    }
}
