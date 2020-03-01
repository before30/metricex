package cc.before30.metricex.webflux.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * WebFluxHandler
 *
 * @author before30
 * @since 2020/03/01
 */

@Component
@Slf4j
public class WebFluxHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        log.info("hello request {}", request);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("Hello from backend-webflux-app");
    }

    public Mono<ServerResponse> eachByQueryParam(ServerRequest request) {
        log.info("each by query parm request {}", request);

        Long query = Long.parseLong(request.queryParam("query").orElse("0"));

        if (query % 100 != 3) {
            return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                    .bodyValue("Hello from backend-webflux-app");
        } else {
            return ServerResponse.status(500).contentType(MediaType.TEXT_PLAIN)
                    .bodyValue("Error");
        }

    }

    public Mono<ServerResponse> eachByPath(ServerRequest request) {
        log.info("each by path request {}", request);
        Long query = Long.parseLong(request.pathVariable("query"));
        if (query % 100 != 3) {
            return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                    .bodyValue("Hello from backend-webflux-app");
        } else {
            return ServerResponse.status(500).contentType(MediaType.TEXT_PLAIN)
                    .bodyValue("Error");
        }
    }
}
