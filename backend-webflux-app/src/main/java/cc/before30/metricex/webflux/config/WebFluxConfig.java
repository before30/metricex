package cc.before30.metricex.webflux.config;

import cc.before30.metricex.webflux.domain.WebFluxHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.*;

/**
 * WebFluxConfig
 *
 * @author before30
 * @since 2020/03/01
 */

@Configuration
@EnableWebFlux
public class WebFluxConfig {

    @Bean
    public RouterFunction<ServerResponse> route(WebFluxHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello"), handler::hello)
                .andRoute(RequestPredicates.GET("/each"), handler::eachByQueryParam)
                .andRoute(RequestPredicates.GET("/each/{query}"), handler::eachByPath);
    }
}
